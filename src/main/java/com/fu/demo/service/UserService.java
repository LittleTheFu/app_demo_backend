package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.FollowDto;
import com.fu.demo.mbg.dto.UserDto;
import com.fu.demo.mbg.model.User;

public interface UserService {
	List<UserDto> listAllUser();
	
	List<UserDto> listAllUser(long fromId);
	
	UserDto getUserById(long id);
	
	UserDto getUserById(long id, long fromId);
	
	void follow(FollowDto followDto);
	
	void unfollow(FollowDto followDto);
	
	boolean isFollowed(long from, long to);
	
	//-----------------------------------------------------------------
	UserDto getCurrentUser();
	
	void currentUserFollow(long that);
	
	void currentUserUnfollow(long that);
}
