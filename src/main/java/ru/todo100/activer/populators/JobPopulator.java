package ru.todo100.activer.populators;

import ru.todo100.activer.data.JobData;
import ru.todo100.activer.model.JobItem;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public class JobPopulator implements Populator<JobItem,JobData> {
    @Override
    public JobData populate(final JobItem jobItem) {
        final JobData jobData = new JobData();
        jobData.setCity(jobItem.getCity());
        jobData.setPost(jobItem.getPost());
        jobData.setWork(jobItem.getWorkplace());
        return jobData;
    }
}
