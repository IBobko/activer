package ru.todo100.activer.populators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.IWantData;
import ru.todo100.activer.data.MarkData;
import ru.todo100.activer.model.IWantItem;
import ru.todo100.activer.model.MarkItem;
import ru.todo100.activer.model.MarkRelationItem;
import ru.todo100.activer.service.MarkRelationService;
import ru.todo100.activer.service.MarkService;

/**
 * @author Igor Bobko
 */
public class IWantPopulator implements Populator<IWantItem, IWantData>
{
	@Autowired
	private MarkRelationService markRelationService;

	@Autowired
	private MarkService markService;

	@Override
	public IWantData populate(final IWantItem iWantItem)
	{
		IWantData data = new IWantData();
		data.setId(iWantItem.getId());
		data.setDescription(iWantItem.getDescription());
		data.setTitle(iWantItem.getTitle());

		List<MarkData> markData = new ArrayList<>();
		List<MarkRelationItem> marks = markRelationService.findByRelation(iWantItem.getId(), false);
		for (MarkRelationItem m : marks)
		{
			MarkItem mark = markService.findMark(m.getMarkId());
			MarkData mData = new MarkData();
			mData.setName(mark.getName());
			mData.setId(mark.getId());
			markData.add(mData);
		}
		data.setMarkData(markData);
		return data;
	}
}
