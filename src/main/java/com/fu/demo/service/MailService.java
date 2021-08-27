package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.CreateMailDto;
import com.fu.demo.mbg.dto.MailResponseDto;

public interface MailService {
	void createMail(CreateMailDto createMailDto, long fromId);
	
	List<MailResponseDto> getMails(long userId);
	
	int deleteMail(long id, long userId);
}
