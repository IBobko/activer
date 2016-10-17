package ru.todo100.activer.populators;

import ru.todo100.activer.data.InterestData;
import ru.todo100.activer.model.InterestItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class InterestPopulator implements Populator<InterestItem,InterestData> {
    @Override
    public InterestData populate(final InterestItem interestItem) {
        final InterestData interestData = new InterestData();
        interestData.setName(interestItem.getName());
        interestData.setId(interestItem.getId());
        return interestData;
    }
}
