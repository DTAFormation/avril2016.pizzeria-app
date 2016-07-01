package fr.pizzeria.admin.metier;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
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

	// @Resource(name = "java:/Email")
	private Session session;

	public void send(String addresses, String topic, String textMessage) {

		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "aspmx.l.google.com");
		prop.put("mail.smtp.port", "25");

		Session session = Session.getDefaultInstance(prop, null);
//		session.getProperties().put("mail.smtp.host", "aspmx.l.google.com");
//		session.getProperties().put("mail.smtp.port", "25");
		System.out.println(session);
		// new javax.mail.Authenticator() {
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new PasswordAuthentication("username", "password");
		// }
		// });
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("test@DTA.fr"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
			message.setSubject(topic);
			message.setText(textMessage);
			message.setSentDate(new Date());
			Transport.send(message);

		} catch (MessagingException e) {
			Logger.getLogger(EMailService.class.getName()).log(Level.WARNING, "Cannot send mail", e);
		}

	}

}