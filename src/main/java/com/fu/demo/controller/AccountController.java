package com.fu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.mbg.model.Account;
import com.fu.demo.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AccountController", description = "账户管理")
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@ApiOperation("获取所有账户列表")
	@GetMapping("/account")
	public List<Account> allArticle() {
		return accountService.listAllAccount();
	}
}
