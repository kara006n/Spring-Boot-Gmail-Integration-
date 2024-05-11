package com.MailSending.Service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.MailSending.Model.MailStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	public void sendMail(String mail, MailStructure mailStructure) {
		
		
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromMail);
		simpleMailMessage.setSubject(mailStructure.getSubject());
		simpleMailMessage.setText(mailStructure.getMessage());
		simpleMailMessage.setTo(mail);
		
		
		mailSender.send(simpleMailMessage);

	}
	
	
	public void sendMailWithAttachment(String toEmail,
			String body,
			String subject,
			String attachment) throws MessagingException {
		
		
		System.out.println("send mail is "+toEmail);
		System.out.println("body is "+ body);
		System.out.println("subject is "+ subject);
		System.out.println("attachment is "+attachment);
		
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom(fromMail);
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		
		mailSender.send(mimeMessage);
		
		
		
	}
	
}
	
	
