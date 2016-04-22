package ru.todo100.activer.service;

import ru.todo100.activer.model.DisputeThemeItem;
import ru.todo100.activer.qualifier.DisputeThemeQualifier;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface DisputeService {
    List<DisputeThemeItem> getItemByQualifier(DisputeThemeQualifier qualifier);
}
