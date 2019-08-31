package com.practice.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;


public class EmailUtility {
	private Properties emailProperties ;
	private ConfigPropertiesUtility configPropertiesUtility;
	private Properties properties;
	private String hostName;
	private String senderEmail;
	private String receiverEmail;
	private String senderPassword;
	private String emailPort;
	
	public EmailUtility() {
		System.out.println("Email Utility Constructor");
		this.readProperties();
		this.setEmailProperties();
	}
	
	private void setEmailProperties(){
		emailProperties = new Properties();
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		emailProperties.put("mail.smtp.host", hostName);
		emailProperties.put("mail.smtp.port", emailPort);
	}
	
	private void readProperties() {
		configPropertiesUtility = new ConfigPropertiesUtility();
		properties = configPropertiesUtility.getPropertyInstance();
		senderEmail = properties.getProperty("senderEmail");
		System.out.println("Sender Email:"+senderEmail);
		receiverEmail = properties.getProperty("receiverEmail");
		System.out.println("Receiver Email : "+receiverEmail);
		senderPassword = properties.getProperty("senderPassword");
		System.out.println("Password "+senderPassword);
		hostName = properties.getProperty("hostName");
		System.out.println("Host Name: "+hostName);
		/*emailPort = properties.getProperty("emailPort");
		System.out.println("Port "+emailPort);*/
	}

	public void sendEmail() {
		
		/*EmailAuthenticator authenticator = new EmailAuthenticator(senderEmail, senderPassword);
		Session session = Session.getDefaultInstance(emailProperties,authenticator);*/
		Session session = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(senderEmail, senderPassword);
		    }
		});
		
		Message message = new MimeMessage(session);
		
		try {
			InternetAddress senderAddress = new InternetAddress(senderEmail);
			InternetAddress recipientAddress = new InternetAddress(receiverEmail);
			Multipart multipart = new MimeMultipart();
			
			message.setFrom(senderAddress);
			message.setRecipient(RecipientType.TO, recipientAddress);
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyymmdd");	
			LocalDateTime localDateTime = LocalDateTime.now();
			
			message.setSubject("****Automation Test Suite Results for "+dateTimeFormatter.format(localDateTime)+"****");
			System.out.println("****Automation Test Suite Results for "+dateTimeFormatter.format(localDateTime)+"****");
			
			BodyPart messageText = new MimeBodyPart();
			messageText.setText("Please find attached automation test run report");
			multipart.addBodyPart(messageText);
			
			BodyPart attachmentFile = new MimeBodyPart();
			String filePath = System.getProperty("user.dir")+"\\test-output\\emailable-report.html";
			System.out.println(filePath);
			DataSource dataSource = new FileDataSource(filePath);
			DataHandler dataHandler = new DataHandler(dataSource);
			attachmentFile.setDataHandler(dataHandler);
			multipart.addBodyPart(attachmentFile);
			
			message.setContent(multipart);
			Transport.send(message);
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
