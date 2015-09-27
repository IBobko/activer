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

public class MailBean {
	private JavaMailSenderImpl mailSender;
	private VelocityEngine velocityEngine;
	
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Autowired
	private ServletContext servletContext;
	
	public void sendCompleteSignUp(final AccountItem account){
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
	            message.setTo(account.getEmail());
	            message.setFrom("no-replay@3dplenty.com");
	            message.setSubject("Registration on 3dplenty.com");
	            Map<String, String> model = new HashMap<String, String>();
	            model.put("fullName", account.getFirstName() + " " + account.getLastName());
	            model.put("login", account.getUsername());
	            model.put("password", account.getPassword());
	            String text = VelocityEngineUtils.mergeTemplateIntoString(
	               velocityEngine, servletContext.getRealPath("/WEB-INF/velocity/email/registration.vm"), model);
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
	            message.setSubject("Registration on 3dplenty.com");
	            Map<String, String> model = new HashMap<String, String>();
	            model.put("fullName", account.getFirstName() + " " + account.getLastName());
	            model.put("login", account.getUsername());
	            model.put("password", account.getPassword());
	            String text = VelocityEngineUtils.mergeTemplateIntoString(
	               velocityEngine, servletContext.getRealPath("/WEB-INF/velocity/email/forgot.vm"), model);
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

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}	
}