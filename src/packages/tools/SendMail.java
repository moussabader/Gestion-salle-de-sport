/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.tools;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMultipart;

/**
 *
 * @author asus
 */
public class SendMail {
    
  /*  public static void sendMail(String to){
        final String username = "s.kammmoun97@gmail.com";
		final String password = "52895612css";
		String fromEmail = "s.kammmoun97@yahoo.com";
		String toEmail = to;
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Subject Line");
			
			Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("My multipart text");
			
			//Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile("/home/parallels/Documents/docs/javamail.pdf");
			
			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			
			//Attach multipart to message
			msg.setContent(emailContent);
			
			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
        


   /** public static void sendMail() {
        try {
            String host = "smtp.gmail.com";
            String user = "soulaymennabil.kammoun@esprit.tn";
            String pass = "181JMT0051";
            String to = "soulaymennabil.kammoun@esprit.tn";
            String from = "Tunisia";
            String subject = "modification de base de donnee ";
            String messageText = "base de donnee modifier successfully";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.ssl.trust", "*");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("email send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void sendMail(String from, String to, String Subject, String message) {
        try {
            String host = "smtp.gmail.com";
            String user = "s.kammoun97@gmail.tn";
            String pass = "52895612";
            String too = to;
            String fro = from;
            String subject = Subject;
            String messageText = message;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(fro));
            InternetAddress[] address = {new InternetAddress(too)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
*/
    public static void sendMail(String to) {
        try {
            String user = "s.kammoun97@gmail.tn";
            String pass = "52895612";
            String too = to;
            String fro = "s.kammoun97@gmail.tn";
            String subject = "modification de base de donnee ";
            String messageText = "base de donnee modifier successfully";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(fro));
            InternetAddress[] address = {new InternetAddress(too)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "s.kammoun97@gmail.tn", "52895612");
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("email send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    } 

}
