package com.fu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.common.api.CommonResult;
import com.fu.demo.mbg.dto.AccountDetail;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.FollowDto;
import com.fu.demo.mbg.dto.FollowResponseDto;
import com.fu.demo.mbg.dto.UserDto;
import com.fu.demo.mbg.model.Account;
import com.fu.demo.mbg.model.User;
import com.fu.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "UserController", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation("获取所有用户")
	@GetMapping("/all")
	public List<UserDto> allUser(Authentication authentication) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long fromId = detail.getUserId();
		
		List<UserDto> users = userService.listAllUser(fromId);
		return users;
	}
	
	@ApiOperation("根据id查询用户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<UserDto> getById(Authentication authentication, @PathVariable("id") int id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long fromId = detail.getUserId();

		UserDto user = userService.getUserById(id, fromId);
		
		return CommonResult.success(user);
	}
	
	@ApiOperation("follow用户")
	@RequestMapping(value = "/follow/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<FollowResponseDto> follow(Authentication authentication, @PathVariable("id") Long id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();
		
		FollowDto followDto = new FollowDto();
		followDto.setFromId(userId);
		followDto.setToId(id);
		
		userService.follow(followDto);
		
		FollowResponseDto responseDto = new FollowResponseDto();
		responseDto.setFollowed(true);
		
		return CommonResult.success(responseDto);
	}
	
	@ApiOperation("unfollow用户")
	@RequestMapping(value = "/unfollow/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<FollowResponseDto> unfollow(Authentication authentication, @PathVariable("id") Long id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();
		
		FollowDto followDto = new FollowDto();
		followDto.setFromId(userId);
		followDto.setToId(id);
		
		userService.unfollow(followDto);
		
		FollowResponseDto responseDto = new FollowResponseDto();
		responseDto.setFollowed(false);
		
		return CommonResult.success(responseDto);
	}
}