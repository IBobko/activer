package ru.todo100.activer.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.CountryDao;
import ru.todo100.activer.data.InterestData;
import ru.todo100.activer.data.TripData;
import ru.todo100.activer.form.*;
import ru.todo100.activer.model.*;
import ru.todo100.activer.populators.InterestPopulator;
import ru.todo100.activer.populators.TripPopulator;
import ru.todo100.activer.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/settings")
@PreAuthorize("isAuthenticated()")
public class SettingPageController {

    private CountryDao countryDao;
    private List<CountryItem> countryItems;
    @Autowired
    private PhotoService photoService1;
    @Autowired
    private AccountDao accountService;
    @Autowired
    private InterestPopulator interestPopulator;

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
        model.addAttribute("pageType", "settings");

        final AccountItem account = accountService.getCurrentAccount();

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
            if (account.getEducationItems() != null && account.getEducationItems().size() > 0) {
                EducationItem education = account.getEducationItems().get(0);
                educationForm.setCity(education.getCity());
                educationForm.setUniversity(education.getUniversity());
                educationForm.setFaculty(education.getFaculty());
                if (education.getCountry() != null) {
                    educationForm.setCountry(education.getCountry().getCode());
                }
                educationForm.setYear(education.getEndYear());
            }

            if (account.getJobItems() != null && account.getJobItems().size() > 0) {
                JobItem job = account.getJobItems().get(0);
                jobForm.setCity(job.getCity());
                jobForm.setPost(job.getPost());
                jobForm.setWork(job.getWorkplace());
            }

            if (account.getChildrenItems() != null && account.getChildrenItems().size() > 0) {
                ChildrenItem child = account.getChildrenItems().get(0);
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
            List<EducationItem> educations = account.getEducationItems();
            if (educations == null) {
                educations = new ArrayList<>();
                account.setEducationItems(educations);
            }

            final EducationItem education;
            if (educations.size() == 0) {
                education = new EducationItem();
                educations.add(education);
            } else {
                education = educations.get(0);
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


            List<JobItem> jobs = account.getJobItems();
            if (jobs == null) {
                jobs = new ArrayList<>();
                account.setJobItems(jobs);
            }

            final JobItem job;
            if (jobs.size() == 0) {
                job = new JobItem();
                jobs.add(job);
            } else {
                job = jobs.get(0);
            }

            job.setCity(childrenEducationJobForm.getJobForm().getCity());
            job.setPost(childrenEducationJobForm.getJobForm().getPost());
            job.setWorkplace(childrenEducationJobForm.getJobForm().getWork());


            List<ChildrenItem> children = account.getChildrenItems();
            if (children == null) {
                children = new ArrayList<>();
                account.setChildrenItems(children);
            }

            final ChildrenItem child;
            if (children.size() == 0) {
                child = new ChildrenItem();
                children.add(child);
            } else {
                child = children.get(0);
            }

            child.setChildrenName(childrenEducationJobForm.getChildrenForm().getName());
            child.setBirthdateYear(childrenEducationJobForm.getChildrenForm().getYear());

            accountService.save(account);
        }
        return "redirect:/settings";
    }

    @RequestMapping(value = "/uploadphoto", method = RequestMethod.POST)
    public String uploadPhoto(HttpServletResponse res, @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {

        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

        File file2 = new File(photo.getName());
        FileUtils.writeByteArrayToFile(file2, photo.getBytes());
        org.apache.http.entity.FileEntity file = new org.apache.http.entity.FileEntity(file2);
        HttpPost httppost = new HttpPost("http://192.168.1.65:18080/static/upload");
        FileBody file111 = new FileBody(file2);
        //MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        mpEntity.addPart("image", new FileBody(file2, photo.getContentType()));
        httppost.setEntity(mpEntity);

        HttpResponse response = httpclient.execute(httppost);


        StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
        String theString = writer.toString();
        res.getWriter().println(theString);


        photoService1.setPhoto(accountService.getCurrentAccount().getId(), theString);
        return "redirect:/profile";

    }

    public InterestPopulator getInterestPopulator() {
        return interestPopulator;
    }

    public void setInterestPopulator(InterestPopulator interestPopulator) {
        this.interestPopulator = interestPopulator;
    }

    @RequestMapping(value = "/interests", method = RequestMethod.GET)
    public String interests(Model model) {
        model.addAttribute("pageType", "settings");

        final AccountItem account = accountService.getCurrentAccount();

        List<InterestItem> interestsItems = account.getInterestItems();
        List<InterestData> interests = new ArrayList<>();
        if (interestsItems != null) {
            for (InterestItem item: interestsItems) {
                interests.add(getInterestPopulator().populate(item));
            }
        }
        model.addAttribute("interests",interests);
        return "settings/interests";
    }

    @RequestMapping(value = "/interests", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void interestsPost(InterestForm interestForm) {
        List<InterestItem> interests = new ArrayList<>();
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


    private TripPopulator tripPopulator;

    @RequestMapping(value = "/trips",method = RequestMethod.GET)
    public String trips(Model model) {
        model.addAttribute("pageType", "settings");

        final TripForm tripForm = new TripForm();
        model.addAttribute("tripForm", tripForm);

        final List<TripData> trips = new ArrayList<>();
        final AccountItem account = accountService.getCurrentAccount();
        if (account.getTripItems() != null) {
            for (final TripItem trip : account.getTripItems()) {
                trips.add(getTripPopulator().populate(trip));
            }
        }
        model.addAttribute("trips", trips);
        return "settings/trips";
    }

    @RequestMapping(value = "/trips",method = RequestMethod.POST)
    public String tripsPost(@Valid TripForm tripForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            final AccountItem account = accountService.getCurrentAccount();
            final TripItem tripItem = new TripItem();
            tripItem.setCity(tripForm.getCity());
            tripItem.setYear(tripForm.getYear());

            for (final CountryItem country : getCountries()) {
                if (country.getCode().equals(tripForm.getCountry())) {
                    tripItem.setCountry(country);
                }
            }
            final List<TripItem> trips;
            if (account.getTripItems() != null) {
                trips = account.getTripItems();
            } else {
                trips = new ArrayList<>();
                account.setTripItems(trips);
            }
            trips.add(tripItem);
            accountService.save(account);
        }
        return "redirect:/settings/trips";
    }

    @RequestMapping(value = "/trips/remove",method = RequestMethod.GET)
    public String tripsPost(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("trip"));
            accountService.deleteTrip(id);
        } catch (NumberFormatException ignored) {}
        return "redirect:/settings/trips";
    }


    @RequestMapping("/dreams")
    public String dreams(Model model) {
        model.addAttribute("pageType", "settings");
        return "settings/dreams";
    }

    // HTTP POST request
    private void sendFile() throws Exception {
/*
        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
*/
    }

}
