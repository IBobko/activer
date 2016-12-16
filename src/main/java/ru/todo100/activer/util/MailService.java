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
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
            message.setFrom("support@onoffline.ru");
            message.setTo(account.getEmail());
            message.setSubject("Registration on onoffline.ru");
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
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
            message.setTo(account.getEmail());
            message.setFrom("support@onoffline.ru");
            message.setSubject("Password on onoffline.ru");
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

    public void sendFriendNotification(final AccountItem fromAccount, final String toEmail) {
        MimeMessagePreparator preparator = mimeMessage -> {
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(toEmail);
            message.setFrom("no-replay@onoffline.ru");
            message.setSubject("Ваш друг прислал вам ссылку на onoffline.ru");
            final Map<String, Object> model = new HashMap<>();
            model.put("referCode", fromAccount.getReferCode());
            model.put("friendName", fromAccount.getFirstName() + " " + fromAccount.getLastName());
            final Template t = getFreemarkerMailConfiguration().getTemplate("friend-notification.vm");
            final String text = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
            mimeMessage.setContent(text,"text/html; charset=utf-8");
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