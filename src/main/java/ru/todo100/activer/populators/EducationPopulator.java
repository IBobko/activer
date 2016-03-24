package ru.todo100.activer.populators;

import ru.todo100.activer.data.EducationData;
import ru.todo100.activer.model.EducationItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class EducationPopulator implements Populator<EducationItem,EducationData> {
    @Override
    public EducationData populate(final EducationItem educationItem) {
        final EducationData educationData = new EducationData();
        educationData.setCity(educationItem.getCity());
        educationData.setYear(educationItem.getEndYear());
        educationData.setCountry(educationItem.getCountry().getName());
        educationData.setFaculty(educationItem.getFaculty());
        educationData.setUniversity(educationItem.getUniversity());
        return educationData;
    }
}
