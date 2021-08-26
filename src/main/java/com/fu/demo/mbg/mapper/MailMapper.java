package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.fu.demo.mbg.model.Mail;

@Mapper
public interface MailMapper {
	void insertMail(Mail mail);
	
	List<Mail> queryMailsByUserId(long userId);
}
