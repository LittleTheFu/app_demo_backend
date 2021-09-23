package com.fu.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.common.api.CommonResult;
import com.fu.demo.mbg.dto.AccountInfoDto;
import com.fu.demo.mbg.dto.AccountSecurityDto;
import com.fu.demo.mbg.dto.EmailDto;
import com.fu.demo.mbg.dto.ResetPasswordDto;
import com.fu.demo.mbg.model.Account;
import com.fu.demo.service.AccountService;
import com.fu.demo.service.EmailService;
import com.fu.demo.service.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Api(tags = "账户管理")
@RestController
@RequestMapping("/account")
public class AccountController {

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private AccountService accountService;

	@Autowired
	private EmailService emailService;

	@ApiOperation("根据id查询账户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Account> getById(@PathVariable("id") int id) {
		return CommonResult.success(accountService.getAccountById(id));
	}

	@ApiOperation("获取所有账户列表")
	@GetMapping("/all")
	public List<AccountInfoDto> allArticle() {
		return accountService.listAllAccount();
	}

	@ApiOperation("注册账户")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createBrand(@RequestBody AccountSecurityDto accountDto) {
		accountService.insert(accountDto);
		return CommonResult.success(null);
	}

	@ApiOperation("申请得到重置密码的链接")
	@RequestMapping(value = "/want_reset_password", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult wantResetPassword(@RequestBody EmailDto emailDto) {
		String link = accountService.getResetLink(emailDto.getEmail());
//		emailService.send(emailDto.getEmail(), "reset link", link);

		return CommonResult.success(link);
	}

	@ApiOperation("设置密码")
	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		String email = resetPasswordDto.getEmail();
		String password = resetPasswordDto.getPassword();
		String code = resetPasswordDto.getCode();
		
		accountService.resetPassword(email, password, code);
		
		return CommonResult.success(null);
	}

	@ApiOperation(value = "登录以后返回token")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult login(@RequestBody AccountSecurityDto accountDto) {
		String token = accountService.login(accountDto.getEmail(), accountDto.getPassword());
		if (token == null) {
			return CommonResult.validateFailed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);
	}
}
