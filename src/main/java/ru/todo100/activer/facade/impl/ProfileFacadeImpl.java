package ru.todo100.activer.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.facade.ProfileFacade;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.populators.ProfilePopulator;

/**
 *
 * @author Igor Bobko <limit-speed@yandex.ru>.

 */
public class ProfileFacadeImpl implements ProfileFacade
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private ProfilePopulator profilePopulator;

	public ProfileData getCurrentProfile()
	{
		AccountItem currentAccount = accountService.getCurrentAccountForProfile();
		if (currentAccount == null) {
			return null;
		}
		return profilePopulator.populate(currentAccount);
	}
}
