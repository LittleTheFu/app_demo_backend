package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fu.demo.mbg.model.User;

@Mapper
public interface UserMapper {
	List<User> listAllUser();
	
	User queryUserById(int id);
}
