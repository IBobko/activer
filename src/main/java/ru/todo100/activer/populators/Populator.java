package ru.todo100.activer.populators;

import java.text.SimpleDateFormat;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface Populator<SOURCE,TARGET> extends Facade
{
	TARGET populate(final SOURCE source);
}
