package com.fu.demo.mbg.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fu.demo.mbg.model.Mail;

@Mapper
public interface MailMapper {
	long insertMail(Mail mail);
}
