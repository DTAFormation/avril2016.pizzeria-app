package fr.pizzeria.admin.metier;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EMailService {

	@Resource(name = "java:/Email")
	private Session session;

	public void send(String addresses, String topic, String textMessage) {

		Session session = Session.getDefaultInstance(fMailServerConfig, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("username", "password");
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
			message.setSubject(topic);
			message.setText(textMessage);

			Transport.send(message);

		} catch (MessagingException e) {
			Logger.getLogger(EMailService.class.getName()).log(Level.WARNING, "Cannot send mail", e);
		}

	}
	 private static Properties fMailServerConfig = new Properties();

	  static {
	    fetchConfig();
	  }
	  
	private static void fetchConfig() {
		// This file contains the javax.mail config properties mentioned above.
		Path path = Paths.get("email");
		try (InputStream input = Files.newInputStream(path)) {
			fMailServerConfig.load(input);
		} catch (IOException ex) {
			System.err.println("Cannot open and load mail server properties file.");
		}
	}

}