package ru.todo100.activer.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.todo100.activer.model.AccountItem;

import java.util.HashMap;
import java.util.Map;

public class MailService {
    private JavaMailSenderImpl mailSender;

    public Configuration getFreemarkerMailConfiguration() {
        return freemarkerMailConfiguration;
    }

    public void setFreemarkerMailConfiguration(Configuration freemarkerMailConfiguration) {
        this.freemarkerMailConfiguration = freemarkerMailConfiguration;
    }

    private Configuration freemarkerMailConfiguration;

    public static boolean isValidEmailAddress(String email) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void sendCompleteSignUp(final AccountItem account) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom("support@todo100.ru");
            message.setTo(account.getEmail());
            message.setSubject("Registration on todo100.ru");
            Map<String, Object> model = new HashMap<>();
            model.put("fullName", account.getFirstName() + " " + account.getLastName());
            model.put("login", account.getUsername());
            model.put("password", account.getPassword());

            Template t = getFreemarkerMailConfiguration().getTemplate("registration.vm");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
            message.setText(text, true);
        };
        getMailSender().send(preparator);
    }

    public void sendForgotPassword(final AccountItem account) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(account.getEmail());
            message.setFrom("no-replay@3dplenty.com");
            message.setSubject("Registration on onoffline.ru");
            Map<String, Object> model = new HashMap<>();
            model.put("fullName", account.getFirstName() + " " + account.getLastName());
            model.put("login", account.getUsername());
            model.put("password", account.getPassword());

            Template t = getFreemarkerMailConfiguration().getTemplate("forgot.vm");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
            message.setText(text, true);
        };
        getMailSender().send(preparator);
    }

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

}