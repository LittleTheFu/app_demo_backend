package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.common.api.AppException;
import com.fu.demo.common.api.ConstMessage;
import com.fu.demo.mbg.dto.CreateMailDto;
import com.fu.demo.mbg.dto.MailResponseDto;
import com.fu.demo.mbg.mapper.MailMapper;
import com.fu.demo.mbg.model.Mail;
import com.fu.demo.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private MailMapper mailMapper;

	@Override
	public void createMail(CreateMailDto createMailDto, long fromId) {
		Mail mail = new Mail();
		
		BeanUtils.copyProperties(createMailDto, mail);
		mail.setMailFromId(fromId);

		mailMapper.insertMail(mail);
	}

	@Override
	public List<MailResponseDto> getMails(long userId) {
		return mailMapper.queryMailsByUserId(userId);
	}

	@Override
	public void deleteMail(long id, long userId) {
		Mail mail = mailMapper.queryRawMail(id);
		if(mail.getMailToId() != userId) {
			throw new AppException(ConstMessage.NOT_OWNER);
		}
		
		int count = mailMapper.deleteMail(id);
		
		if(count < 1) {
			throw new AppException(ConstMessage.ACTION_NOT_DONE);
		}
	}
}
