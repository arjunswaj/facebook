package edu.iiitb.facebook.action.login;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class SendingEmail {

public boolean	sendmail(String email,String password) throws AddressException, MessagingException
{
	
	
	String host = "smtp.gmail.com";
	String port = "587";
	final String adminUser = "group1ooad@gmail.com";
	final String adminPassword = "ooad1234";

	// sets SMTP server properties
	Properties properties = new Properties();
	properties.put("mail.smtp.host", host);
	properties.put("mail.smtp.port", port);
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.user", adminUser);
	properties.put("mail.password", adminPassword);

	// creates a new session with an authenticator
	Authenticator auth = new Authenticator() {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(adminUser, adminPassword);
		}
	};
	Session session = Session.getInstance(properties, auth);

	// creates a new e-mail message
	Message msg = new MimeMessage(session);

	msg.setFrom(new InternetAddress(adminUser));
	InternetAddress[] toAddresses = { new InternetAddress(email) };
	msg.setRecipients(Message.RecipientType.TO, toAddresses);
	msg.setSubject("Facebook Account - SignUp Notification Mail");
	msg.setSentDate(new Date());

	// creates message part
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart
			.setContent(
					"Hello User,"
							+ " Greetings from Facebook! You received this email because you have created"
							+ " an account with this Email and "
							+ "This is your password: "
							+ password
							+ " Thanking you."
							+ " The Group1-OOAD-IIITB-2014", "text/html");

	// creates multi-part
	Multipart multipart = new MimeMultipart();
	multipart.addBodyPart(messageBodyPart);

	// sets the multi-part as e-mail's content
	msg.setContent(multipart);
	// sends the e-mail
	Transport.send(msg);
	return true;

}
	
}
