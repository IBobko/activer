package ru.todo100.activer.facade;

import java.util.List;

import ru.todo100.activer.data.ICanData;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
public interface ICanFacade
{
	List<ICanData> getByAccount(Integer id);

}
