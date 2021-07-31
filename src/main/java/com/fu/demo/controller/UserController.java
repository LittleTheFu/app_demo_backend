package com.fu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.common.api.CommonResult;
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
	public List<User> allArticle() {
		List<User> users =userService.listAllUser();
		return users;
	}
	
	@ApiOperation("根据id查询用户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<User> getById(@PathVariable("id") int id) {
		Account account = userService.getUserById(id).getAccount();
		User user = userService.getUserById(id);
		return CommonResult.success(user);
	}
}