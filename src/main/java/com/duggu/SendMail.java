package com.duggu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;

public class SendMail {

	//public String mail() {
	public static void main(String arg[]) {	
					
		SendMail obj = new SendMail();
		obj.sendMailto("<-- Recipient mail address -->","<-- Subject -->","<-- Message Body -->");
	}
	
	public String sendMailto(String receiver, String subject, String text)
	{
		System.out.println("Reached upto here.,");
		try {
        	String header ="From:";
    		Properties props = System.getProperties();
            props.put("SMTP","<-- Mail Host Name -->");
            props.put("smtp_port","<-- Port no -->");
            
         /* 
          * If you provide Username and Password for your Mail host to send mail then use the below part
          *   
          *   props.put("mail.smtps.auth","true");
            props.put("mail.smtps.username","<-- Username -->");
            props.put("mail.smtps.password","<-- Password -->");
            Session session = Session.getInstance(props,
           	  new javax.mail.Authenticator() {
          		protected PasswordAuthentication getPasswordAuthentication() {
          			return new PasswordAuthentication("<-- Username -->", "<-- Password -->");
          			}
          		  }); */
            
            // If there is no authendication then, 
            
            Session session = Session.getInstance(props, null);
        	Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("<-- Sender mail -->"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver, false));
	        msg.setSubject(subject);
	        msg.setText(text);
	        msg.setHeader(header,receiver);
	        msg.setSentDate(new Date());
	        SMTPTransport t = (SMTPTransport)session.getTransport("smtp");
	        t.connect("<-- Mail Host Name -->","<-- Sender mail -->","<-- Password -->");
	        t.sendMessage(msg, msg.getAllRecipients());		        
	        t.close();
		} catch (AddressException e) {
		
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		};
	

		return "Success"; 
		
	}

}
