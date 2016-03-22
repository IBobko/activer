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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.CountryDao;
import ru.todo100.activer.form.*;
import ru.todo100.activer.model.*;
import ru.todo100.activer.service.PhotoService;

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
public class SettingPageController {

    private CountryDao countryDao;
    private List<CountryItem> countryItems;
    @Autowired
    private PhotoService photoService1;
    @Autowired
    private AccountDao accountService;

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

            for (CountryItem country: getCountries()) {
                if (country.getCode().equals(childrenEducationJobForm.getEducationForm().getCountry())){
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
        HttpPost httppost = new HttpPost("http://todo100.ru:18080/static/upload");
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


    @RequestMapping("/interests")
    public String interests(Model model) {
        model.addAttribute("pageType", "settings");
        return "settings/interests";
    }

    @RequestMapping("/trips")
    public String trips(Model model) {
        model.addAttribute("pageType", "settings");
        return "settings/trips";
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
