package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.Session;

import ru.todo100.activer.form.ICanForm;
import ru.todo100.activer.form.IWantForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.CategoryItem;
import ru.todo100.activer.model.ICanItem;
import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.Model3dItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
public class ICanService  extends ServiceAbstract{

	@Override
	public Class<? extends Item> getItemClass()
	{
		return ICanItem.class;
	}

	public ICanItem add(final AccountItem accountItem, final ICanForm iCanForm)
	{
		final ICanItem item = new ICanItem();
		item.setAccount(accountItem);
		item.setDescription(iCanForm.getDescription());
		item.setTitle(iCanForm.getTitle());
		item.setId(iCanForm.getId());
		Session session = getSession();
		session.saveOrUpdate(item);
		return item;
	}

	public List<ICanItem> getAll() {
		final Session session = getSession();
		final List<ICanItem> cans = session.createQuery("from ICanItem c").list();
		return cans;
	}

	public ICanItem get(Integer id) {
		final Session session = getSession();
		return  (ICanItem) session.get(this.getItemClass(), id);
	}
}
