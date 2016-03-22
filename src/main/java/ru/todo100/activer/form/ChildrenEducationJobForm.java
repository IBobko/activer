package ru.todo100.activer.form;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class ChildrenEducationJobForm {
    private EducationForm educationForm;
    private JobForm jobForm;
    private ChildrenForm childrenForm;

    public EducationForm getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(EducationForm educationForm) {
        this.educationForm = educationForm;
    }

    public JobForm getJobForm() {
        return jobForm;
    }

    public void setJobForm(JobForm jobForm) {
        this.jobForm = jobForm;
    }

    public ChildrenForm getChildrenForm() {
        return childrenForm;
    }

    public void setChildrenForm(ChildrenForm childrenForm) {
        this.childrenForm = childrenForm;
    }
}
