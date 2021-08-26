package com.fu.demo.service;

import com.fu.demo.mbg.dto.CreateMailDto;
import com.fu.demo.mbg.model.Mail;

public interface MailService {
	void CreateMail(CreateMailDto createMailDto, long fromId);
}
