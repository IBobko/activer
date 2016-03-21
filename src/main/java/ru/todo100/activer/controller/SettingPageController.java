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
import org.apache.velocity.texen.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/settings")
public class SettingPageController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String main(HttpServletRequest request,Model model) {
        model.addAttribute("pageType","settings");
        return "settings/index";
    }

    @Autowired
    PhotoService photoService1;
    @Autowired
    private AccountDao accountService;

    @RequestMapping(value = "/uploadphoto",method = RequestMethod.POST)
    public String uploadPhoto(HttpServletResponse res, HttpServletRequest request, Model model, @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {

        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

        File file2 = new File(photo.getName());
        FileUtils.writeByteArrayToFile(file2, photo.getBytes());
        org.apache.http.entity.FileEntity file = new org.apache.http.entity.FileEntity(file2);
        HttpPost httppost = new HttpPost("http://todo100.ru:18080/static/upload");
        FileBody file111 = new FileBody(file2);
        //MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        mpEntity.addPart("image", new FileBody(file2,photo.getContentType()));
        httppost.setEntity(mpEntity);

        HttpResponse response = httpclient.execute(httppost);


        StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
        String theString = writer.toString();
        res.getWriter().println(theString);


        photoService1.setPhoto(accountService.getCurrentAccount().getId(),theString);
        return "redirect:/profile";

    }



    @RequestMapping("/interests")
    public String interests(Model model) {
        model.addAttribute("pageType","settings");
        return "settings/interests";
    }

    @RequestMapping("/trips")
    public String trips(Model model) {
        model.addAttribute("pageType","settings");
        return "settings/trips";
    }

    @RequestMapping("/dreams")
    public String dreams(Model model) {
        model.addAttribute("pageType","settings");
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
