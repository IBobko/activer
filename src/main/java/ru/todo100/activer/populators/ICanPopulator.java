/*********************************************************************
 * The Initial Developer of the content of this file is NOVARDIS.
 * All portions of the code written by NOVARDIS are property of
 * NOVARDIS. All Rights Reserved.
 *
 * NOVARDIS
 * Detskaya st. 5A, 199106 
 * Saint Petersburg, Russian Federation 
 * Tel: +7 (812) 331 01 71
 * Fax: +7 (812) 331 01 70
 * www.novardis.com
 *
 * (c) 2015 by NOVARDIS
 *********************************************************************/
package ru.todo100.activer.populators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.ICanData;
import ru.todo100.activer.data.MarkData;
import ru.todo100.activer.model.ICanItem;
import ru.todo100.activer.model.MarkItem;
import ru.todo100.activer.model.MarkRelationItem;
import ru.todo100.activer.service.MarkRelationService;
import ru.todo100.activer.service.MarkService;

/**
 * @author Igor Bobko
 */
public class ICanPopulator implements Populator<ICanItem, ICanData>
{
	@Autowired
	private MarkRelationService markRelationService;

	@Autowired
	private MarkService markService;

	@Override
	public ICanData populate(final ICanItem iCanItem)
	{
		ICanData data = new ICanData();
		data.setId(iCanItem.getId());
		data.setDescription(iCanItem.getDescription());
		data.setTitle(iCanItem.getTitle());

		List<MarkData> markData = new ArrayList<>();
		List<MarkRelationItem> marks = markRelationService.findByRelation(iCanItem.getId(), true);
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
