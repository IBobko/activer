package ru.todo100.activer.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.facade.ProfileFacade;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.populators.ProfilePopulator;
import ru.todo100.activer.service.AccountService;

/**
 *
 * @author igor

 */
public class ProfileFacadeImpl implements ProfileFacade
{
	@Autowired
	private AccountService accountService;

	@Autowired
	private ProfilePopulator profilePopulator;

	public ProfileData getCurrentProfile()
	{
		AccountItem currentAccount = accountService.getCurrentAccount();
		if (currentAccount == null) {
			return null;
		}
		return profilePopulator.populate(currentAccount);
	}
}
