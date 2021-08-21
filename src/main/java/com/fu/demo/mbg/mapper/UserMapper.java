package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.dto.FollowDto;
import com.fu.demo.mbg.dto.UserDto;
import com.fu.demo.mbg.model.User;

@Mapper
public interface UserMapper {
	List<UserDto> listAllUser();

	UserDto queryUserById(long id);

	void setAccountId(@Param("userId") long userId, @Param("accountId") long accountId);
	
	void setIcon(@Param("userId") long userId, @Param("icon") String icon);
	
	void insert(User user);
	
	void follow(FollowDto followDto);
	
	void unfollow(FollowDto followDto);
	
	boolean isFollowed(@Param("fromId") long fromId, @Param("toId") long toId);
}
