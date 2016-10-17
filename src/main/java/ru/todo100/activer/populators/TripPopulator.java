package ru.todo100.activer.populators;

import ru.todo100.activer.data.TripData;
import ru.todo100.activer.model.TripItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class TripPopulator implements Populator<TripItem, TripData> {
    @Override
    public TripData populate(final TripItem tripItem) {
        final TripData tripData = new TripData();
        tripData.setId(tripItem.getId());
        tripData.setCity(tripItem.getCity());
        tripData.setYear(tripItem.getYear());
        if (tripItem.getCountry() != null) {
            tripData.setCountry(tripItem.getCountry().getName());
            tripData.setCountryCode(tripItem.getCountry().getCode());
        }
        return tripData;
    }
}
