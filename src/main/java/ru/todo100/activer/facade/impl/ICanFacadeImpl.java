package ru.todo100.activer.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.ICanData;
import ru.todo100.activer.facade.ICanFacade;
import ru.todo100.activer.model.ICanItem;
import ru.todo100.activer.populators.ICanPopulator;
import ru.todo100.activer.service.ICanService;

/**
 * @author Igor Bobko
 */
public class ICanFacadeImpl implements ICanFacade
{
	@Autowired
	private ICanPopulator iCanPopulator;
	@Autowired
	private ICanService   iCanService;

	public List<ICanData> getByAccount(Integer id){
		final List<ICanItem> iCanItems = iCanService.getByAccount(id);
		final List<ICanData> iCanData = new ArrayList<>();

		for (ICanItem item: iCanItems) {
			ICanData data = iCanPopulator.populate(item);
			iCanData.add(data);
		}
		return iCanData;
	}
}
