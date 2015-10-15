
package ru.todo100.activer.facade;

import java.util.List;

import ru.todo100.activer.data.IWantData;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
public interface IWantFacade
{
	List<IWantData> getByAccount(Integer id);
}
