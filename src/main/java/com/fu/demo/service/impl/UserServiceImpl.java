package com.fu.demo.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.AccountDetail;
import com.fu.demo.mbg.dto.FollowDto;
import com.fu.demo.mbg.dto.UserDto;
import com.fu.demo.mbg.mapper.UserMapper;
import com.fu.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
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
		while (iterator.hasNext()) {
			UserDto user = iterator.next();

			boolean isFollowed = userMapper.isFollowed(fromId, user.getId());
			user.setFollowed(isFollowed);
		}

		return users;
	}

	@Override
	public List<UserDto> listAllUserWithCurrentUser() {
		AccountDetail detail = getCurrentAccount();

		if (detail == null)
			return null;

		long fromId = detail.getUserId();

		List<UserDto> users = userMapper.listAllUser();

		Iterator<UserDto> iterator = users.iterator();
		while (iterator.hasNext()) {
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
	public UserDto getUserByIdWithCurrentUser(long id) {
		AccountDetail detail = getCurrentAccount();

		if (detail == null)
			return null;

		long fromId = detail.getUserId();
		UserDto user = userMapper.queryUserById(id);

//		user.setIcon("http://101.132.41.44:9000/zero/cover.png");

		boolean followed = userMapper.isFollowed(fromId, id);
		user.setFollowed(followed);

		return user;
	}

	private void updateFollowFlag(List<UserDto> that, List<UserDto> myFollowings) {
		that.forEach((t) -> {
			myFollowings.forEach((m) -> {
				if (m.getId() == t.getId()) {
					t.setFollowed(true);
				}
			});
		});
	}

	@Override
	public List<UserDto> getFollowingsWithCurrentUser(long id) {
		List<UserDto> followings = userMapper.getFollowings(id);

		long currentUserId = getCurrentUserId();
		List<UserDto> myFollowings = userMapper.getFollowings(currentUserId);
		
		updateFollowFlag(followings, myFollowings);

		return followings;
	}

	@Override
	public List<UserDto> getFollowerWithCurrentUser(long id) {
		List<UserDto> followers = userMapper.getFollowers(id);

		long currentUserId = getCurrentUserId();
		List<UserDto> myFollowings = userMapper.getFollowings(currentUserId);
		
		updateFollowFlag(followers, myFollowings);

		return followers;
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

	@Override
	public UserDto getCurrentUser() {
		AccountDetail detail = getCurrentAccount();

		if (detail == null) {
			return null;
		}

		long id = detail.getUserId();

		return userMapper.queryUserById(id);
	}

	@Override
	public long getCurrentUserId() {
		AccountDetail detail = getCurrentAccount();

		if (detail == null) {
			return 0;
		}

		long id = detail.getUserId();

		return id;
	}

	@Override
	public void currentUserFollow(long that) {
		AccountDetail detail = getCurrentAccount();
		if (detail == null)
			return;

		long currentUserId = detail.getUserId();

		FollowDto dto = new FollowDto();
		dto.setFromId(currentUserId);
		dto.setToId(that);

		userMapper.follow(dto);
	}

	@Override
	public void currentUserUnfollow(long that) {
		AccountDetail detail = getCurrentAccount();
		if (detail == null)
			return;

		long currentUserId = detail.getUserId();

		FollowDto dto = new FollowDto();
		dto.setFromId(currentUserId);
		dto.setToId(that);

		userMapper.unfollow(dto);
	}

	@Override
	public void setCurrentUserIcon(String icon) {
		AccountDetail detail = getCurrentAccount();
		if (detail == null) {
			return;
		}

		long id = detail.getUserId();
		userMapper.setIcon(id, icon);
	}
	
	@Override
	public void setCurrentUserName(String name) {
		long userId = getCurrentUserId();
		userMapper.setName(userId, name);
	}

	private AccountDetail getCurrentAccount() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		AccountDetail detail = (AccountDetail) auth.getPrincipal();

		return detail;
	}
}
