package br.com.its.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import br.com.its.classes.User;

@Service
public class EmailNotificationService {
	
	public void sendEmail(User user) throws Exception{
			Properties props = new Properties();
			configEmail(props);
			Session session = Session.getDefaultInstance(props,	new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						String login = "yourusername";
						String password = "yourpassword";
						return new PasswordAuthentication(login,password);
					}
				});
	 
			try {
	 
				email(user, session);
	 
			} catch (MessagingException e) {
				throw new Exception(e);
			}
		}
	/**
	 */
	private void email(User user, Session session) throws MessagingException,
			AddressException {
		Message message = new MimeMessage(session);
		String fromEmail = "from@email.com";
		String subject = "Testing Send Email ITS";
		String bodyEmail = "olá," + "\n\n It is working \\o//";
		String toEmail = user.getEmail();
		message.setFrom(new InternetAddress(fromEmail));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail));
		message.setSubject(subject);
		message.setText(bodyEmail);
		Transport.send(message);
	}

	private void configEmail(Properties props) {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}
}
