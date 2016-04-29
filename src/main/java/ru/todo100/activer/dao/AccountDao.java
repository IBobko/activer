package ru.todo100.activer.dao;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.todo100.activer.data.Qualifier;
import ru.todo100.activer.form.RegisterForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.DreamItem;
import ru.todo100.activer.model.PromoCodeItem;
import ru.todo100.activer.model.TripItem;
import ru.todo100.activer.service.PromoService;
import ru.todo100.activer.service.ReferService;
import ru.todo100.activer.util.InputError;
import ru.todo100.activer.util.MailService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings(value = {"unused", "SqlResolve", "unchecked"})
@Transactional
public class AccountDao extends AbstractDao
{
	@Autowired
	private ReferService referService;
	@Autowired
	private PromoService promoService;

	public List<AccountItem> getAll()
	{
		return getCriteria().setMaxResults(10).list();
	}

	@Override
	public Class<AccountItem> getItemClass()
	{
		return AccountItem.class;
	}

	public AccountItem get(Integer id)
	{
		return getSession().get(this.getItemClass(), id);
	}

	public void save(AccountItem account)
	{
		final Session session = getSession();
		session.saveOrUpdate(account);
	}

	public AccountItem get(String login)
	{
		return (AccountItem) getCriteria().add(Restrictions.eq("username", login)).uniqueResult();
	}

	public AccountItem getByEmail(String email)
	{
		return (AccountItem) getCriteria().add(Restrictions.eq("email", email)).uniqueResult();
	}

	public AccountItem getCurrentAccountForProfile() {
		getSession().enableFetchProfile("account-for-profile");
		AccountItem accountItem = getCurrentAccount();
		getSession().disableFetchProfile("account-for-profile");
		return accountItem;
	}

	public AccountItem getCurrentAccount()
	{
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated()) {

			for (final GrantedAuthority authority: auth.getAuthorities()) {
				if (authority.getAuthority().equals("ROLE_ANONYMOUS")) {
					return null;
				}
			}

			final AccountItem accountItem = get(auth.getName());
			accountItem.setLastActivity(new GregorianCalendar());
			save(accountItem);
			return accountItem;
		}
		return null;
	}

	public void addFriend(AccountItem account, Integer friendId)
	{
		account.getFriends().add(get(friendId));
		save(account);
	}

	public void deleteOldInterests() {
		getSession().createSQLQuery("DELETE FROM INTEREST WHERE account_id is null").executeUpdate();
	}

	public AccountItem saveForm(RegisterForm registerForm) throws InputError {
		String password = RandomStringUtils.randomAlphanumeric(6);
		InputError ie = new InputError();

		if (registerForm.getEmail().equals(""))
		{
			ie.addError("E-mail is empty");
		}

		if (!MailService.isValidEmailAddress(registerForm.getEmail()))
		{
			ie.addError("E-mail is invalid");
		}

		AccountItem exists = this.get(registerForm.getEmail());
		if (exists != null)
		{
			ie.addError("Login is busy");
		}

		if (registerForm.getFirstName().trim().equals(""))
		{
			ie.addError("First name is empty");
		}

		if (registerForm.getLastName().trim().equals(""))
		{
			ie.addError("Last name is empty");
		}

		PromoCodeItem promoCode = null;
		if (StringUtils.isNotEmpty(registerForm.getPromo())){
			promoCode = promoService.getPromo(registerForm.getPromo());
			if (promoCode != null) {
				if (promoCode.getUsed() != null) {
					ie.addError("Promo is used");
				}
			}
			else
			{
				ie.addError("Promo is invalid");
			}
		}

		if (!ie.getErrors().isEmpty())
		{
			throw ie;
		}

		final AccountItem account = new AccountItem();
		account.setEmail(registerForm.getEmail());
		account.setUsername(registerForm.getEmail());
		account.setPassword(password);
		account.setFirstName(registerForm.getFirstName());
		account.setLastName(registerForm.getLastName());
		account.addRole("ROLE_USER");
		account.setCreatedDate(new GregorianCalendar());
		account.setReferCode(RandomStringUtils.randomAlphanumeric(6));

		final AccountItem referAccount = referService.getUserByRefer(registerForm.getRefer());
		if (referAccount != null) {
			account.setUsedReferCode(referAccount.getReferCode());
		}
		save(account);
		if (promoCode != null) {
			promoCode.setUsed(account);
			getSession().save(promoCode);
		}
		return account;
	}

	public void deleteTrip(Integer id) {
		final AccountItem account = getCurrentAccount();
		for (TripItem trip : account.getTripItems()) {
			if (Objects.equals(trip.getId(), id)) {
				account.getTripItems().remove(trip);
				break;
			}
		}
	}

	public AccountItem getRandomOnlineAccount() {
		final List<BigDecimal> result = getSession().createSQLQuery(
				"select id from (select id,extract( day from diff )*24*60*60*1000 + " +
						" extract( hour from diff )*60*60*1000 + " +
						" extract( minute from diff )*60*1000 + " +
						" round(extract( second from diff )*1000) total_milliseconds " +
						"from (select id,(systimestamp - ACCOUNT_LAST_ACTIVITY) diff from ACCOUNT)) where total_milliseconds < 10906720").list();

		final Integer index = ThreadLocalRandom.current().nextInt(0, result.size());
		return getSession().load(AccountItem.class, result.get(index).intValue());
	}

	public void deleteDream(Integer id) {
		final AccountItem account = getCurrentAccount();
		for (DreamItem dream : account.getDreamItems()) {
			if (Objects.equals(dream.getId(), id)) {
				account.getDreamItems().remove(dream);
				break;
			}
		}
	}

	public List<AccountItem> getByQualifier(Qualifier qualifier) {
		Criteria criteria = getCriteria();
		if (qualifier.getStart() != null) {
			criteria.setFirstResult(qualifier.getStart());
		}

		if (qualifier.getCount() != null) {
			criteria.setMaxResults(qualifier.getCount());
		}
		return criteria.list();
	}

	public Long getCountByQualifier(Qualifier qualifier) {
		final Criteria criteria = getCriteria();
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
