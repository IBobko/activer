package ru.todo100.activer.facade;

import ru.todo100.activer.data.TopLineData;

import java.util.Set;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface TopLineFacade {
    Set<TopLineData> getTopLines();
}
