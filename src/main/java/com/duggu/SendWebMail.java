package com.duggu;

import java.util.*; 
import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;
public class SendWebMail {
	public static void main(String[] args) {
		SendWebMail obj_send_mail=new SendWebMail();
		obj_send_mail.send_mail("<-- Subject -->","<-- Message Body -->", "<-- Recipient mail address -->");
	}
	public String send_mail(String subject, String message,String email_id) {
		String mailStatus="Failed";
		String smtpServer = null; 
                String smtpPort = null;
		final String authAddress = "<-- Sender mail -->";      
		final String authPassword = "";     
        smtpServer = "<-- Mail Host Name -->";
        smtpPort = "<-- Port no -->";    
        try{    
                Properties props = new Properties();     
                props.put("mail.smtp.host", smtpServer); 
                props.put("mail.smtp.port", smtpPort);   
                props.put("mail.smtp.auth", "false");         
            // create some properties and get the default Session
            Session sessionMail = Session.getInstance(props, null);                                                                   
            sessionMail.setDebug(true);
            // create a message
            Message msg = new MimeMessage(sessionMail);
            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(authAddress);
            msg.setFrom(addressFrom);
            InternetAddress[] addressTo = new InternetAddress[1];
            addressTo[0] = new InternetAddress(email_id);
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            Transport.send(msg);
            mailStatus ="Success";
            System.out.println("Mail sent");
        }catch(Exception e){
                System.out.println(e);
        }
        return mailStatus;
	}
}