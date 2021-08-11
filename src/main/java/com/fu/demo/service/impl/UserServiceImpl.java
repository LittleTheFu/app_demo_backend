package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.FollowDto;
import com.fu.demo.mbg.dto.UserDto;
import com.fu.demo.mbg.mapper.UserMapper;
import com.fu.demo.mbg.model.User;
import com.fu.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserDto> listAllUser() {
		return userMapper.listAllUser();
	}

	@Override
	public UserDto getUserById(long id) {
		return userMapper.queryUserById(id);
	}

	@Override
	public void follow(FollowDto followDto) {
		userMapper.follow(followDto);
	}
	
	@Override
	public void unfollow(FollowDto followDto) {
		userMapper.unfollow(followDto);
	}
}
