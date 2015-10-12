package ru.todo100.activer.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import ru.todo100.activer.model.AccountFriendRelationItem;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.util.InputError;
import ru.todo100.activer.util.MailBean;

@SuppressWarnings(value = {"unchecked"})
public class AccountService extends ServiceAbstract
{
	@Autowired
	private AccountFriendRelationService accountFriendRelationService;

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
		Session session = getSession();
		return session.get(this.getItemClass(), id);
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
  /** TODO переттащить это все в фасад **/
	public AccountItem saveByRequest(HttpServletRequest request) throws InputError
	{
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");

		InputError ie = new InputError();

		if (email.trim().equals(""))
		{
			ie.addError("E-mail is empty");
		}

		if (!MailBean.isValidEmailAddress(email))
		{
			ie.addError("E-mail is invalid");
		}

		AccountItem exists = this.get(email);
		if (exists != null)
		{
			ie.addError("Login is busy");
		}

		if (firstName.trim().equals(""))
		{
			ie.addError("First name is empty");
		}

		if (lastName.trim().equals(""))
		{
			ie.addError("Last name is empty");
		}

		if (!ie.getErrors().isEmpty())
		{
			throw ie;
		}

		final AccountItem account = new AccountItem();
		account.setEmail(email);
		account.setUsername(email);
		account.setPassword(password);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.addRole("ROLE_USER");
		save(account);
		return account;
	}

	public void addFriend(AccountItem account, Integer friendId)
	{
		final AccountFriendRelationItem accountFriendRelationItem = new AccountFriendRelationItem();
		accountFriendRelationItem.setAccountId(account.getId().intValue());
		accountFriendRelationItem.setFriendAccountId(friendId);
		accountFriendRelationService.save(accountFriendRelationItem);
	}

	public List<AccountItem> getFriends(Integer accountId)
	{
		final List<AccountFriendRelationItem> friendsRelation = accountFriendRelationService.getFriends(accountId);
		final List<AccountItem> result = new ArrayList<>();
		for (AccountFriendRelationItem friend : friendsRelation)
		{
			final AccountItem friendAccount = get(friend.getFriendAccountId());
			result.add(friendAccount);
		}
		return result;
	}
}
