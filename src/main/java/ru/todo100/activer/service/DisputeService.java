package ru.todo100.activer.service;

import ru.todo100.activer.data.DisputeThemeData;
import ru.todo100.activer.form.DisputeThemeForm;
import ru.todo100.activer.model.DisputeThemeItem;
import ru.todo100.activer.qualifier.DisputeThemeQualifier;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface DisputeService {
    List<DisputeThemeItem> getItemByQualifier(DisputeThemeQualifier qualifier);
    Long getCountByQualifier(DisputeThemeQualifier qualifier);
    List<DisputeThemeData> getDataByQualifier(DisputeThemeQualifier qualifier);
    DisputeThemeItem editDispute(DisputeThemeForm form);
    DisputeThemeItem get(Integer id);
    void delete(Integer id);
}
