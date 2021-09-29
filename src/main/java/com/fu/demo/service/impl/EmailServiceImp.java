package com.fu.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fu.demo.service.EmailService;

@Service
public class EmailServiceImp implements EmailService {
	@Value("${email.user}")
	private String EMAIL_USER;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void send(String address, String title, String content) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(EMAIL_USER);
        simpleMailMessage.setTo(address);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
	}

}
