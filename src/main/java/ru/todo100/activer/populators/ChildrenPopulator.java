package ru.todo100.activer.populators;

import ru.todo100.activer.data.ChildrenData;
import ru.todo100.activer.model.ChildrenItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class ChildrenPopulator implements Populator<ChildrenItem, ChildrenData> {
    @Override
    public ChildrenData populate(ChildrenItem childrenItem) {
        final ChildrenData childrenData = new ChildrenData();
        childrenData.setName(childrenItem.getChildrenName());
        childrenData.setYear(childrenItem.getBirthdateYear());
        return childrenData;
    }
}
