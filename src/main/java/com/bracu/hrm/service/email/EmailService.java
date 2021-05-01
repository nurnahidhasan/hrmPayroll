package com.bracu.hrm.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bracu.hrm.model.email.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

	 @Autowired
	    private JavaMailSender emailSender;

	    public void sendSimpleMessage(Mail mail) throws MessagingException {

	        MimeMessage message = emailSender.createMimeMessage();
	        message.setFrom("BRAC University<noreply@bracu.ac.bd>");
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom("BRAC University<noreply@bracu.ac.bd>");
	        helper.setSubject(mail.getSubject());
	        helper.setText(mail.getContent());
	        helper.setTo(mail.getTo());
	        
	       // helper.setFrom(mail.getTo());

	        helper.addAttachment("attachment-document-name.jpg", new ClassPathResource("memorynotfound-logo.jpg"));
	      
	        emailSender.send(message);

	    }
}
