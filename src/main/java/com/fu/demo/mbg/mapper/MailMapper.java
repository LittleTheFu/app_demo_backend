package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fu.demo.mbg.dto.MailResponseDto;
import com.fu.demo.mbg.model.Mail;

@Mapper
public interface MailMapper {
	void insertMail(Mail mail);
	
	List<MailResponseDto> queryMailsByUserId(long userId);
	
	int deleteMail(long id);
	
	Mail queryRawMail(long id);
}
