package ru.todo100.activer.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.CountryDao;
import ru.todo100.activer.dao.PhotoDao;
import ru.todo100.activer.data.*;
import ru.todo100.activer.form.*;
import ru.todo100.activer.model.*;
import ru.todo100.activer.populators.DreamPopulator;
import ru.todo100.activer.populators.InterestPopulator;
import ru.todo100.activer.populators.TripPopulator;
import ru.todo100.activer.service.NewsService;
import ru.todo100.activer.service.PhotoService;
import ru.todo100.activer.util.ResizeImage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/settings")
public class SettingPageController {
    @Value("${static.host}")
    private String staticHost;
    private CountryDao countryDao;
    private List<CountryItem> countryItems;
    @Autowired
    private PhotoService photoService1;
    @Autowired
    private AccountDao accountService;

    private InterestPopulator interestPopulator;
    private TripPopulator tripPopulator;
    private DreamPopulator dreamPopulator;
    @Autowired
    private NewsService newsService;
    private PhotoDao photoDao;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @ModelAttribute("countries")
    public List<CountryItem> getCountries() {
        if (countryItems == null) {
            countryItems = getCountryDao().getAll();
        }
        return countryItems;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String main(final Model model) {
        model.addAttribute("pageType", "settings/info");

        final AccountItem account = accountService.getCurrentAccount();

        model.addAttribute("photo", photoService1.getSizedPhoto(account.getId()));

        if (!model.containsAttribute("mainInfoForm")) {
            final PayPalAccountForm payPalAccountForm = new PayPalAccountForm();
//            mainInfoForm.setFirstName(account.getFirstName());
//            mainInfoForm.setLastName(account.getLastName());
//            mainInfoForm.setSex(account.getSex());
//            mainInfoForm.setMaritalStatus(account.getMaritalStatus());
//            mainInfoForm.setBirthDate(account.getBirthdate());
//            if (account.getBirthdate() != null) {
//                mainInfoForm.setBirthDate(account.getBirthdate());
//            }

            model.addAttribute("payPalForm", payPalAccountForm);
        }

        if (!model.containsAttribute("mainInfoForm")) {
            final MainInfoForm mainInfoForm = new MainInfoForm();
            mainInfoForm.setFirstName(account.getFirstName());
            mainInfoForm.setLastName(account.getLastName());
            mainInfoForm.setSex(account.getSex());
            mainInfoForm.setMaritalStatus(account.getMaritalStatus());
            mainInfoForm.setBirthDate(account.getBirthdate());
            if (account.getBirthdate() != null) {
                mainInfoForm.setBirthDate(account.getBirthdate());
            }

            model.addAttribute("mainInfoForm", mainInfoForm);
        }
        if (!model.containsAttribute("childrenEducationJobForm")) {
            final ChildrenEducationJobForm childrenEducationJobForm = new ChildrenEducationJobForm();
            final JobForm jobForm = new JobForm();
            childrenEducationJobForm.setJobForm(jobForm);
            final EducationForm educationForm = new EducationForm();
            childrenEducationJobForm.setEducationForm(educationForm);
            final ChildrenForm childrenForm = new ChildrenForm();
            childrenEducationJobForm.setChildrenForm(childrenForm);
            if (!account.getEducationItems().isEmpty()) {
                EducationItem education = account.getEducationItems().iterator().next();
                educationForm.setCity(education.getCity());
                educationForm.setUniversity(education.getUniversity());
                educationForm.setFaculty(education.getFaculty());
                if (education.getCountry() != null) {
                    educationForm.setCountry(education.getCountry().getCode());
                }
                educationForm.setYear(education.getEndYear());
            }

            if (!account.getJobItems().isEmpty()) {
                JobItem job = account.getJobItems().iterator().next();
                jobForm.setCity(job.getCity());
                jobForm.setPost(job.getPost());
                jobForm.setWork(job.getWorkplace());
            }

            if (!account.getChildrenItems().isEmpty()) {
                ChildrenItem child = account.getChildrenItems().iterator().next();
                childrenForm.setName(child.getChildrenName());
                childrenForm.setYear(child.getBirthdateYear());
            }

            model.addAttribute("childrenEducationJobForm", childrenEducationJobForm);
        }


        return "settings/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String mainPost(@Valid final MainInfoForm mainInfoForm, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            final AccountItem account = accountService.getCurrentAccount();
            account.setFirstName(mainInfoForm.getFirstName());
            account.setLastName(mainInfoForm.getLastName());
            account.setSex(mainInfoForm.getSex());
            account.setMaritalStatus(mainInfoForm.getMaritalStatus());
            account.setBirthdate(mainInfoForm.getBirthDate());
            accountService.save(account);
        }
        return "redirect:/settings";
    }

    @RequestMapping(value = "/advancedPost", method = RequestMethod.POST)
    public String advancedPost(@Valid final ChildrenEducationJobForm childrenEducationJobForm, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            final AccountItem account = accountService.getCurrentAccount();
            final Set<EducationItem> educations = account.getEducationItems();

            final EducationItem education;
            if (educations.isEmpty()) {
                education = new EducationItem();
                educations.add(education);
            } else {
                education = educations.iterator().next();
            }

            education.setCity(childrenEducationJobForm.getEducationForm().getCity());
            education.setEndYear(childrenEducationJobForm.getEducationForm().getYear());
            education.setFaculty(childrenEducationJobForm.getEducationForm().getFaculty());
            education.setUniversity(childrenEducationJobForm.getEducationForm().getUniversity());

            for (CountryItem country : getCountries()) {
                if (country.getCode().equals(childrenEducationJobForm.getEducationForm().getCountry())) {
                    education.setCountry(country);
                }
            }


            Set<JobItem> jobs = account.getJobItems();


            final JobItem job;
            if (jobs.isEmpty()) {
                job = new JobItem();
                jobs.add(job);
            } else {
                job = jobs.iterator().next();
            }

            job.setCity(childrenEducationJobForm.getJobForm().getCity());
            job.setPost(childrenEducationJobForm.getJobForm().getPost());
            job.setWorkplace(childrenEducationJobForm.getJobForm().getWork());


            Set<ChildrenItem> children = account.getChildrenItems();

            final ChildrenItem child;
            if (children.size() == 0) {
                child = new ChildrenItem();
                children.add(child);
            } else {
                child = children.iterator().next();
            }

            child.setChildrenName(childrenEducationJobForm.getChildrenForm().getName());
            child.setBirthdateYear(childrenEducationJobForm.getChildrenForm().getYear());

            accountService.save(account);
        }
        return "redirect:/settings";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadphoto", method = RequestMethod.POST)
    public PhotoAvatarSizeData uploadPhoto(final HttpServletRequest req, @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {
        final String contentType = photo.getContentType();

        final File originalFile = new File(photo.getOriginalFilename());
        FileUtils.writeByteArrayToFile(originalFile, photo.getBytes());
        final String theString = sendFile(originalFile, contentType);


        final PhotoAvatarSizeData photoAvatarSizeData = new PhotoAvatarSizeData();
        photoAvatarSizeData.setPhotoOriginal(theString);

        final File fileShowingSize = getNewFile(originalFile, 300, 300);
        String showingSize = sendFile(fileShowingSize, contentType);


        photoAvatarSizeData.setPhotoShowing(showingSize);


        final File fileThumbnailSize = getNewFile(originalFile, 100, 100);
        String size100 = sendFile(fileThumbnailSize, contentType);


        photoAvatarSizeData.setPhotoThumbnail(size100);


        fileShowingSize.deleteOnExit();
        fileThumbnailSize.deleteOnExit();
        originalFile.deleteOnExit();

        photoService1.setPhoto(accountService.getCurrentAccount().getId(), photoAvatarSizeData);

        newsService.addNews(accountService.getCurrentAccount().getId(), "AVATAR",photoAvatarSizeData.getPhotoOriginal());

        return photoAvatarSizeData;
    }

    public String sendFile(File file, String contentType) throws IOException {
        final HttpClient httpclient = HttpClientBuilder.create().build();
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        final HttpPost httppost = new HttpPost(staticHost + "/static/upload");
        builder.addPart("image", new FileBody(file, ContentType.create(contentType)));
        httppost.setEntity(builder.build());
        final HttpResponse response = httpclient.execute(httppost);
        final StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
        return writer.toString();
    }

    public File getNewFile(File file, int width, int height) {
        String newName = RandomStringUtils.randomAlphanumeric(6);
        final File newFile = new File(newName);
        String extension = FilenameUtils.getExtension(file.getName());
        ResizeImage.resize(file, newFile, extension, width, height);
        return newFile;
    }

    public InterestPopulator getInterestPopulator() {
        return interestPopulator;
    }

    @Autowired
    public void setInterestPopulator(InterestPopulator interestPopulator) {
        this.interestPopulator = interestPopulator;
    }

    @RequestMapping(value = "/interests", method = RequestMethod.GET)
    public String interests(Model model) {
        model.addAttribute("pageType", "settings/info/interest");

        final AccountItem account = accountService.getCurrentAccount();

        Set<InterestItem> interestsItems = account.getInterestItems();
        List<InterestData> interests = new ArrayList<>();

        for (InterestItem item : interestsItems) {
            interests.add(getInterestPopulator().populate(item));
        }

        model.addAttribute("interests", interests);
        return "settings/interests";
    }

    @RequestMapping(value = "/interests", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void interestsPost(InterestForm interestForm) {
        Set<InterestItem> interests = new HashSet<>();
        if (interestForm.getTags() != null) {
            for (String interest : interestForm.getTags()) {
                InterestItem interestItem = new InterestItem();
                interestItem.setName(interest);
                interests.add(interestItem);
            }

        }
        final AccountItem account = accountService.getCurrentAccount();

        account.setInterestItems(interests);
        accountService.save(account);
        accountService.deleteOldInterests();

    }

    public TripPopulator getTripPopulator() {
        return tripPopulator;
    }

    @Autowired
    public void setTripPopulator(TripPopulator tripPopulator) {
        this.tripPopulator = tripPopulator;
    }

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public String trips(Model model) {
        model.addAttribute("pageType", "settings/info/trip");

        final TripForm tripForm = new TripForm();
        model.addAttribute("tripForm", tripForm);

        final List<TripData> trips = new ArrayList<>();
        final AccountItem account = accountService.getCurrentAccount();

        for (final TripItem trip : account.getTripItems()) {
            trips.add(getTripPopulator().populate(trip));
        }

        model.addAttribute("trips", trips);
        return "settings/trips";
    }

    @RequestMapping(value = "/trips", method = RequestMethod.POST)
    public String tripsPost(@Valid final TripForm tripForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            final AccountItem account = accountService.getCurrentAccount();
            final TripItem tripItem = new TripItem();
            tripItem.setCity(tripForm.getCity());
            String[] date = tripForm.getYear().split("/");
            tripItem.setYear(Integer.parseInt(date[1]));
            for (final CountryItem country : getCountries()) {
                if (country.getCode().equals(tripForm.getCountry())) {
                    tripItem.setCountry(country);
                }
            }
            final Set<TripItem> trips = account.getTripItems();
            trips.add(tripItem);
            accountService.save(account);
        }
        return "redirect:/settings/trips";
    }

    @RequestMapping(value = "/trips/remove", method = RequestMethod.GET)
    public String tripsPost(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("trip"));
            accountService.deleteTrip(id);
        } catch (NumberFormatException ignored) {
        }
        return "redirect:/settings/trips";
    }

    public DreamPopulator getDreamPopulator() {
        return dreamPopulator;
    }

    @Autowired
    public void setDreamPopulator(DreamPopulator dreamPopulator) {
        this.dreamPopulator = dreamPopulator;
    }

    @RequestMapping("/dreams")
    public String dreams(Model model) {
        model.addAttribute("pageType", "settings/info/dream");
        final DreamForm dreamForm = new DreamForm();
        model.addAttribute("dreamForm", dreamForm);
        final AccountItem account = accountService.getCurrentAccount();
        final List<DreamData> dreams = new ArrayList<>();

        for (DreamItem dreamItem : account.getDreamItems()) {
            final DreamData dreamData = getDreamPopulator().populate(dreamItem);
            dreams.add(dreamData);
        }

        model.addAttribute("dreams", dreams);
        return "settings/dreams";
    }

    @RequestMapping(value = "/dreams/upload", method = RequestMethod.POST)
    public String dreamsUpload(@Valid DreamForm dreamForm, BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()) {
            final AccountItem account = accountService.getCurrentAccount();

            DreamItem dreamItem = null;
            if (dreamForm.getId() != null) {
                for (DreamItem item : account.getDreamItems()) {
                    if (item.getId().equals(dreamForm.getId())) {
                        dreamItem = item;
                    }
                }
            }

            if (dreamItem == null) {
                dreamItem = new DreamItem();
            }

            dreamItem.setName(dreamForm.getText());

            if (dreamForm.getPhoto() != null && !dreamForm.getPhoto().isEmpty()) {
                final HttpClient httpclient = HttpClientBuilder.create().build();
                final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                final File file = new File(dreamForm.getPhoto().getName());
                FileUtils.writeByteArrayToFile(file, dreamForm.getPhoto().getBytes());
                final HttpPost httppost = new HttpPost("http://192.168.1.65:18080/static/upload");
                builder.addPart("image", new FileBody(file, ContentType.create(dreamForm.getPhoto().getContentType())));
                httppost.setEntity(builder.build());
                final HttpResponse response = httpclient.execute(httppost);
                final StringWriter writer = new StringWriter();
                IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
                final String theString = writer.toString();
                System.out.println(theString);
                dreamItem.setPhoto(theString);
            }
            final Set<DreamItem> dreamItems = account.getDreamItems();
            dreamItems.add(dreamItem);
            accountService.save(account);
        }
        return "redirect:/settings/dreams";
    }

    @RequestMapping(value = "/dreams/remove")
    public String removeDream(final HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("dream"));
            accountService.deleteDream(id);
        } catch (NumberFormatException ignored) {
        }
        return "redirect:/settings/dreams";
    }

    @RequestMapping(value = "/leftmenusave")
    @ResponseBody
    public void leftmenusave(final HttpServletRequest request) {
        if (request.getParameter("yes") != null) {
            request.getSession().setAttribute("leftmenu", 1);
        } else {
            request.getSession().removeAttribute("leftmenu");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/croppedSaveImage", method = RequestMethod.POST)
    public void croppedSaveImage(final HttpServletRequest request, @RequestParam(value = "croppedImage", required = false) final MultipartFile cropped) throws IOException {
        File file = null;
        File miniCroppedFile = null;
        try {
            // Always PNG came
            final String extension = ".png";
            final ProfileData profileData = accountService.getCurrentProfileData(request.getSession());
            final AccountPhotoItem accountPhotoItem = getPhotoDao().getByAccount(profileData.getId());
            file = new File(cropped.getOriginalFilename() + extension);
            FileUtils.writeByteArrayToFile(file, cropped.getBytes());
            final String croppedFileName = sendFile(file, cropped.getContentType());
            accountPhotoItem.setNameAvatar(croppedFileName);
            miniCroppedFile = getNewFile(file, 60, 60);
            final String miniCroppedFileName = sendFile(miniCroppedFile, cropped.getContentType());
            accountPhotoItem.setNameMini(miniCroppedFileName);
            getPhotoDao().save(accountPhotoItem);
        } finally {
            if (miniCroppedFile != null) {
                miniCroppedFile.deleteOnExit();
            }
            if (file != null) {
                file.deleteOnExit();
            }
        }
    }

    public PhotoDao getPhotoDao() {
        return photoDao;
    }

    @Autowired
    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

}
