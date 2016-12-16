package ru.todo100.activer.jsp;

import org.apache.commons.lang.StringUtils;
import ru.todo100.activer.data.TripData;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class WorldMap {
    public String toString() {
        return "World Map;";
    }

    public String generateCData(final List<TripData> trips) {
        if (trips == null) return "";
        StringBuilder result = new StringBuilder();
        boolean wasFirst = false;
        for (int i = 0; i < trips.size(); i++) {
            if (StringUtils.isNotEmpty(trips.get(i).getCountryCode())) {
                if (wasFirst) {
                    result.append(",");
                } else {
                    wasFirst = true;
                }
                String code = trips.get(i).getCountryCode().substring(0,2);
                result.append("'").append(code).append("':1");
            }
        }
        return result.toString();

    }

}
