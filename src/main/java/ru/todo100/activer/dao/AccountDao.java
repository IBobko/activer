package ru.todo100.activer.dao;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.todo100.activer.form.RegisterForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.DreamItem;
import ru.todo100.activer.model.PromoCodeItem;
import ru.todo100.activer.model.TripItem;
import ru.todo100.activer.service.PromoService;
import ru.todo100.activer.service.ReferService;
import ru.todo100.activer.util.InputError;
import ru.todo100.activer.util.MailService;

@SuppressWarnings(value = {"unchecked"})
@Transactional
public class AccountDao extends AbstractDao
{

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
		Session session = getSession();
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

	public AccountItem getCurrentAccount()
	{
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated())
		{
			return get(auth.getName());
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



	/**
	 * TODO переттащить это все в фасад
	 **/

	@Autowired
	ReferService referService;

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

	@Autowired
	PromoService promoService;

	public void deleteTrip(Integer id) {
		final AccountItem account = getCurrentAccount();
		if (account.getTripItems() != null) {
			for (TripItem trip : account.getTripItems()) {
				if (Objects.equals(trip.getId(), id)) {
					account.getTripItems().remove(trip);
					break;
				}
			}
		}
	}

	public void deleteDream(Integer id) {
		final AccountItem account = getCurrentAccount();
		if (account.getDreamItems() != null) {
			for (DreamItem dream : account.getDreamItems()) {
				if (Objects.equals(dream.getId(), id)) {
					account.getDreamItems().remove(dream);
					break;
				}
			}
		}
	}
}
