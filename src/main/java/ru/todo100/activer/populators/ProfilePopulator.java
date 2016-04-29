package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.PhotoDao;
import ru.todo100.activer.data.DreamData;
import ru.todo100.activer.data.InterestData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.data.TripData;
import ru.todo100.activer.model.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class ProfilePopulator implements Populator<AccountItem, ProfileData> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Autowired
    private PhotoDao photoService;

    private EducationPopulator educationPopulator;

    private JobPopulator jobPopulator;

    private ChildrenPopulator childrenPopulator;

    private InterestPopulator interestPopulator;
    private TripPopulator tripPopulator;
    private DreamPopulator dreamPopulator;

    private DreamPopulator getDreamPopulator() {
        return dreamPopulator;
    }

    @Autowired
    public void setDreamPopulator(DreamPopulator dreamPopulator) {
        this.dreamPopulator = dreamPopulator;
    }

    private TripPopulator getTripPopulator() {
        return tripPopulator;
    }

    @Autowired
    public void setTripPopulator(TripPopulator tripPopulator) {
        this.tripPopulator = tripPopulator;
    }

    private InterestPopulator getInterestPopulator() {
        return interestPopulator;
    }

    @Autowired
    public void setInterestPopulator(InterestPopulator interestPopulator) {
        this.interestPopulator = interestPopulator;
    }

    private ChildrenPopulator getChildrenPopulator() {
        return childrenPopulator;
    }

    @Autowired
    public void setChildrenPopulator(ChildrenPopulator childrenPopulator) {
        this.childrenPopulator = childrenPopulator;
    }


    private JobPopulator getJobPopulator() {
        return jobPopulator;
    }

    @Autowired
    public void setJobPopulator(JobPopulator jobPopulator) {
        this.jobPopulator = jobPopulator;
    }

    private EducationPopulator getEducationPopulator() {
        return educationPopulator;
    }

    @Autowired
    public void setEducationPopulator(EducationPopulator educationPopulator) {
        this.educationPopulator = educationPopulator;
    }

    @Override
    public ProfileData populate(final AccountItem accountItem) {
        if (accountItem == null) {
            return null;
        }
        final AccountPhotoItem facePhoto = photoService.getByAccount(accountItem.getId());

        final ProfileData profileData = new ProfileData();
        profileData.setFirstName(accountItem.getFirstName());
        profileData.setLastName(accountItem.getLastName());

        profileData.setOnline(accountItem.getIsOnline());

        profileData.setId(accountItem.getId());
        if (accountItem.getBirthdate() != null) {
            profileData.setBirthDate(simpleDateFormat.format(accountItem.getBirthdate().getTime()));
        }

        /* Educations populate*/
        if (!accountItem.getEducationItems().isEmpty()) {
            profileData.setEducation(getEducationPopulator().populate(accountItem.getEducationItems().iterator().next()));
        }

        /* Jobs populate*/
        if (!accountItem.getJobItems().isEmpty()) {
            profileData.setJob(getJobPopulator().populate(accountItem.getJobItems().iterator().next()));
        }

        /* Children populate*/
        if (!accountItem.getChildrenItems().isEmpty()) {
            profileData.setChildren(getChildrenPopulator().populate(accountItem.getChildrenItems().iterator().next()));
        }

        /* Interests populate*/
        final List<InterestData> interests = new ArrayList<>();
        for (InterestItem item : accountItem.getInterestItems()) {
            interests.add(getInterestPopulator().populate(item));
        }
        profileData.setInterests(interests);

        /* Dreams populate*/
        final List<DreamData> dreams = new ArrayList<>();
        for (DreamItem item : accountItem.getDreamItems()) {
            dreams.add(getDreamPopulator().populate(item));
        }
        profileData.setDreams(dreams);

        /* Trips populate*/
        final List<TripData> trips = new ArrayList<>();
        for (TripItem item : accountItem.getTripItems()) {
            trips.add(getTripPopulator().populate(item));
        }
        profileData.setTrips(trips);

        if (facePhoto != null) {
            profileData.setFacePhotoUrl(facePhoto.getName());
            File f = new File(facePhoto.getName());
            profileData.setFacePhotoUrl(f.getParent() + "/" + "thumb_" + f.getName());
            profileData.setPhoto60x60(f.getParent() + "/" + "60x60_" + f.getName());
        }
        return profileData;
    }
}
