package ru.todo100.activer.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.ICanData;
import ru.todo100.activer.populators.ICanPopulator;
import ru.todo100.activer.service.ICanService;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
public interface ICanFacade
{
	List<ICanData> getByAccount(Integer id);

}
