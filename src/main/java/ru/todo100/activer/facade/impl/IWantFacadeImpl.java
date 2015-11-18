package ru.todo100.activer.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.IWantData;
import ru.todo100.activer.facade.IWantFacade;
import ru.todo100.activer.model.IWantItem;
import ru.todo100.activer.populators.IWantPopulator;
import ru.todo100.activer.dao.IWantDao;

/**
 * @author Igor Bobko
 */
public class IWantFacadeImpl implements IWantFacade
{
	@Autowired
	private IWantPopulator iWantPopulator;
	@Autowired
	private IWantDao       iWantService;

	@Override
	public List<IWantData> getByAccount(final Integer id)
	{
		final List<IWantItem> iWantItems = iWantService.getByAccount(id);
		final List<IWantData> iWantData = new ArrayList<>();

		for (IWantItem item: iWantItems) {
			IWantData data = iWantPopulator.populate(item);
			iWantData.add(data);
		}
		return iWantData;
	}
}
