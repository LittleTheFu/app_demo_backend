package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.FollowDto;
import com.fu.demo.mbg.dto.UserDto;
import com.fu.demo.mbg.model.User;

public interface UserService {
	List<UserDto> listAllUser();
	
	UserDto getUserById(long id);
	
	void follow(FollowDto followDto);
	
	void unfollow(FollowDto followDto);
}
