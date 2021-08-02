package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.model.User;

@Mapper
public interface UserMapper {
	List<User> listAllUser();

	User queryUserById(long id);

	void setAccountId(@Param("userId") long userId, @Param("accountId") long accountId);
	
	void insert(User user);
}
