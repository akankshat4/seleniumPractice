package practice;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	public static void main(String argds[]) {
		String recepient = "akanksha.tomar90@gmail.com";
		String senderEmail = "akankshat4@gmail.com";
		String password = "vaibhav.singh144";
		String hostName = "smtp.gmail.com";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", hostName);
		properties.put("mail.smtp.port", "25");
		
		EmailAuthenticator authenticator = new EmailAuthenticator(senderEmail, password);
		authenticator.getPasswordAuthentication();
		
		Session session = Session.getDefaultInstance(properties, authenticator);
		
		try {
			Message message = new MimeMessage(session);
			InternetAddress addressFrom = new InternetAddress(senderEmail);
			InternetAddress addressTo = new InternetAddress(recepient);
			
			message.setFrom(addressFrom);
			message.setRecipient(RecipientType.TO, addressTo);
			message.setSubject("This is testing sample");
			//message.setText("Hello There !!!");
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hello There");
			  message.setContent(
		              "<h1>This is actual message embedded in HTML tags</h1>",
		             "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			System.out.println(System.getProperty("user.dir"));
			String fileName = "C:\\Users\\Akanksha Tomar\\workspace\\FrameworkDesign\\test-output\\emailable-report.html";
			DataSource dataSource = new FileDataSource(fileName);
			DataHandler dataHandler = new DataHandler(dataSource);
			messageBodyPart.setDataHandler(dataHandler);
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Message sent successfully");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
