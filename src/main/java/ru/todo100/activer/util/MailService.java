package ru.todo100.activer.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import ru.todo100.activer.model.AccountItem;

public class MailService {
	private JavaMailSenderImpl mailSender;
	private VelocityEngine velocityEngine;

	@Autowired
	ServletContext servletContext;

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void sendCompleteSignUp(final AccountItem account){
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				message.setFrom("support@todo100.ru");
				message.setTo(account.getEmail());
				message.setSubject("Registration on todo100.ru");
	            Map<String, Object> model = new HashMap<>();
	            model.put("fullName", account.getFirstName() + " " + account.getLastName());
	            model.put("login", account.getUsername());
				model.put("password", account.getPassword());


				String text = VelocityEngineUtils.mergeTemplateIntoString(
	               velocityEngine, "\\WEB-INF\\velocity\\email\\registration.vm","UTF-8", model);
	            message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}
	
	public void sendForgotPassword(final AccountItem account) {
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
	            message.setTo(account.getEmail());
	            message.setFrom("no-replay@3dplenty.com");
	            message.setSubject("Registration on onoffline.ru");
	            Map<String, Object> model = new HashMap<>();
	            model.put("fullName", account.getFirstName() + " " + account.getLastName());
	            model.put("login", account.getUsername());
	            model.put("password", account.getPassword());
	            String text = VelocityEngineUtils.mergeTemplateIntoString(
	               velocityEngine, "/forgot.vm", model);
	            message.setText(text, true);
			}
		};
		mailSender.send(preparator);		
	}
	
	public static boolean isValidEmailAddress(String email) {
	       java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
	       java.util.regex.Matcher m = p.matcher(email);
	       return m.matches();
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

}