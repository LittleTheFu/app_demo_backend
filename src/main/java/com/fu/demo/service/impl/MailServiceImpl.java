package com.fu.demo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.CreateMailDto;
import com.fu.demo.mbg.mapper.MailMapper;
import com.fu.demo.mbg.model.Mail;
import com.fu.demo.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private MailMapper mailMapper;

	@Override
	public void CreateMail(CreateMailDto createMailDto, long fromId) {
		Mail mail = new Mail();
		
		BeanUtils.copyProperties(createMailDto, mail);
		mail.setMailFromId(fromId);

		mailMapper.insertMail(mail);
	}
}