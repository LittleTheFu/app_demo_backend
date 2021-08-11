package com.fu.demo.service.impl;

import java.util.Iterator;
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
	public List<UserDto> listAllUser(long fromId) {
		List<UserDto> users = userMapper.listAllUser();
		
		Iterator<UserDto> iterator = users.iterator();
		while(iterator.hasNext()) {
			UserDto user = iterator.next();
			
			boolean isFollowed = userMapper.isFollowed(fromId, user.getId());  
			user.setFollowed(isFollowed);
		}
		
		return users;
	}

	@Override
	public UserDto getUserById(long id) {
		return userMapper.queryUserById(id);
	}
	
	@Override
	public UserDto getUserById(long id, long fromId) {
		UserDto user = userMapper.queryUserById(id);
		
		boolean followed = userMapper.isFollowed(fromId, id);
		user.setFollowed(followed);

		return user;
	}

	@Override
	public void follow(FollowDto followDto) {
		userMapper.follow(followDto);
	}
	
	@Override
	public void unfollow(FollowDto followDto) {
		userMapper.unfollow(followDto);
	}

	@Override
	public boolean isFollowed(long from, long to) {
		return userMapper.isFollowed(from, to);
	}
}
