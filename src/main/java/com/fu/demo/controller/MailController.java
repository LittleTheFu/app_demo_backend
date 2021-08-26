package com.fu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.common.api.CommonResult;
import com.fu.demo.mbg.dto.CreateMailDto;
import com.fu.demo.mbg.model.Mail;
import com.fu.demo.service.MailService;
import com.fu.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "MailController", description = "文章管理")
@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation("创建文章")
	@RequestMapping(value = "/create_mail", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createMail(@RequestBody CreateMailDto createMailDto) {
		long currentUserId = userService.getCurrentUserId();
		mailService.CreateMail(createMailDto, currentUserId);
		
		return CommonResult.success(null);
	}
}