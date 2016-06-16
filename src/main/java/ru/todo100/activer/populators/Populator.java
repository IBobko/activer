package ru.todo100.activer.populators;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface Populator<SOURCE,TARGET>
{
	TARGET populate(final SOURCE source);
}
