package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.CreateMailDto;
import com.fu.demo.mbg.model.Mail;

public interface MailService {
	void createMail(CreateMailDto createMailDto, long fromId);
	
	List<Mail> getMails(long userId);
}
