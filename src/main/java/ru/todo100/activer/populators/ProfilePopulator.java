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
 * @author Igor Bobko
 */
public class ProfilePopulator implements Populator<AccountItem, ProfileData> {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Autowired
    private PhotoDao photoService;

    private EducationPopulator educationPopulator;

    private JobPopulator jobPopulator;

    private ChildrenPopulator childrenPopulator;

    private InterestPopulator interestPopulator;
    private TripPopulator tripPopulator;
    private DreamPopulator dreamPopulator;

    public DreamPopulator getDreamPopulator() {
        return dreamPopulator;
    }

    @Autowired
    public void setDreamPopulator(DreamPopulator dreamPopulator) {
        this.dreamPopulator = dreamPopulator;
    }

    public TripPopulator getTripPopulator() {
        return tripPopulator;
    }

    @Autowired
    public void setTripPopulator(TripPopulator tripPopulator) {
        this.tripPopulator = tripPopulator;
    }

    public InterestPopulator getInterestPopulator() {
        return interestPopulator;
    }

    @Autowired
    public void setInterestPopulator(InterestPopulator interestPopulator) {
        this.interestPopulator = interestPopulator;
    }

    public ChildrenPopulator getChildrenPopulator() {
        return childrenPopulator;
    }

    @Autowired
    public void setChildrenPopulator(ChildrenPopulator childrenPopulator) {
        this.childrenPopulator = childrenPopulator;
    }


    public JobPopulator getJobPopulator() {
        return jobPopulator;
    }

    @Autowired
    public void setJobPopulator(JobPopulator jobPopulator) {
        this.jobPopulator = jobPopulator;
    }

    public EducationPopulator getEducationPopulator() {
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
        final PhotoItem facePhoto = photoService.getByAccount(accountItem.getId());

        final ProfileData profileData = new ProfileData();
        profileData.setFirstName(accountItem.getFirstName());
        profileData.setLastName(accountItem.getLastName());

        profileData.setId(accountItem.getId());
        if (accountItem.getBirthdate() != null) {
            profileData.setBirthDate(simpleDateFormat.format(accountItem.getBirthdate().getTime()));
        }

        if (accountItem.getEducationItems() != null && accountItem.getEducationItems().size() > 0) {
            profileData.setEducation(getEducationPopulator().populate(accountItem.getEducationItems().get(0)));
        }

        if (accountItem.getJobItems() != null && accountItem.getJobItems().size() > 0) {
            profileData.setJob(getJobPopulator().populate(accountItem.getJobItems().get(0)));
        }

        if (accountItem.getChildrenItems() != null && accountItem.getChildrenItems().size() > 0) {
            profileData.setChildren(getChildrenPopulator().populate(accountItem.getChildrenItems().get(0)));
        }

        final List<InterestData> interests = new ArrayList<>();
        if (accountItem.getInterestItems() != null) {
            for (InterestItem item : accountItem.getInterestItems()) {
                interests.add(getInterestPopulator().populate(item));
            }
        }
        profileData.setInterests(interests);

        final List<DreamData> dreams = new ArrayList<>();
        if (accountItem.getDreamItems() != null) {
            for (DreamItem item : accountItem.getDreamItems()) {
                dreams.add(getDreamPopulator().populate(item));
            }
        }
        profileData.setDreams(dreams);

        final List<TripData> tripDatas = new ArrayList<>();
        if (accountItem.getTripItems() != null) {
            for (TripItem item : accountItem.getTripItems()) {
                tripDatas.add(getTripPopulator().populate(item));
            }
        }
        profileData.setTrips(tripDatas);

        if (facePhoto != null) {
            profileData.setFacePhotoUrl(facePhoto.getPath());
            File f = new File(facePhoto.getPath());
            profileData.setFacePhotoUrl(f.getParent() + "/" + "thumb_" + f.getName());
            profileData.setPhoto60x60(f.getParent() + "/" + "60x60_" + f.getName());
        }
        return profileData;
    }
}
