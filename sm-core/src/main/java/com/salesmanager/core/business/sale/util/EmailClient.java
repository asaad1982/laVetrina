package com.salesmanager.core.business.sale.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.sale.dto.MailSettings;

public class EmailClient {

	private static PropertiesReader props = PropertiesReader.getInstance("email.properties");

	public static void sendMail(String fromEmailAddress, String toMail, String subject, String mailBody) throws Exception{

		MailSettings mailSettings = loadMailSettings();
		mailSettings.setFromMailAddress(fromEmailAddress);

		sendMail(toMail, subject, mailBody, mailSettings);

	}

	private static MailSettings loadMailSettings() {
		String mailServer = props.getProperty("mailSender.host");
		String serverPort = props.getProperty("mailSender.port");
		String userName = props.getProperty("mailSender.username");
		String password = props.getProperty("mailSender.password");
		String authentication = props.getProperty("mailSender.mail.smtp.auth");
		String starttls = props.getProperty("mail.smtp.starttls.enable");
		String socketFactoryPort = props.getProperty("mail.socketFactoryPort");
		String socketFactoryClass = props.getProperty("mail.socketFactoryClass");
		String fromMailAddress = props.getProperty("mail.frommailaddress");
		return new MailSettings(mailServer, serverPort, userName, password, authentication, starttls,
				socketFactoryPort, socketFactoryClass, fromMailAddress);
	}

	private static void sendMail(String toMail, String subject,
			String mailBody, MailSettings mailSettings) throws Exception {

		Properties props = System.getProperties();

		props.put("mail.smtp.auth", mailSettings.getAuthentication());
		props.put("mail.smtp.host", mailSettings.getMailServer());
		props.put("mail.smtp.port", mailSettings.getPortNumber());
		if (StringUtils.isNotEmpty(mailSettings.getStarttls())) {
			props.put("mail.smtp.starttls.enable", mailSettings.getStarttls());
		}
		loadSSLProperties(props, mailSettings);
		final String username = mailSettings.getMailUserName();
		final String password = mailSettings.getMailPassword();

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		Message message = null;
		message = getMessage(mailSettings, toMail, subject, mailBody, session);
		Transport.send(message);

	}

	private static void loadSSLProperties(Properties props, MailSettings mailSettings) {
		if (StringUtils.isNotEmpty(mailSettings.getSocketFactoryPort())
				&& StringUtils.isNotEmpty(mailSettings.getSocketFactoryClass())) {
			props.put("mail.smtp.socketFactory.port", mailSettings.getSocketFactoryPort());
			props.put("mail.smtp.socketFactory.class", mailSettings.getSocketFactoryClass());
		}
	}

	
	private static Message getMessage(MailSettings mailSettings, String toMail, String subject, String mailBody, Session session) throws AddressException,
			MessagingException {
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(mailSettings.getFromMailAddress()));

		InternetAddress tos[] = InternetAddress.parse(StringUtils.join(toMail, ","));
		message.setRecipients(RecipientType.TO, tos);
		message.setSubject(subject);
		message.setSentDate(new Date());
		Multipart multipart = new MimeMultipart("mixed");
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(mailBody, "text/html");
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		return message;
	}
	
}