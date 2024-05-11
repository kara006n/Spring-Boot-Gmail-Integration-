package com.MailSending.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MailSending.Model.MailStructure;
import com.MailSending.Service.MailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/send/{mail}")
	public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure) {

		System.out.println("mail recieved is " + mail);
		System.out.println(mailStructure);

		mailService.sendMail(mail, mailStructure);
		return "Succesfully send the mail";
	}

	
	@PostMapping("/sendWithAttachment/{toEmail}")
	public String sendMailWithAttachment(@PathVariable String toEmail, @RequestBody MailStructure mailStructure) throws MessagingException {

		
		mailService.sendMailWithAttachment(toEmail, mailStructure.getMessage(), mailStructure.getSubject(), "C:\\Users\\Lenovo\\OneDrive\\Desktop\\testImage.png");
		return "Succesfully send the mail with attachment";
	}

}
