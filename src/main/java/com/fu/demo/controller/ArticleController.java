package com.fu.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.mbg.dto.AccountSecurityDto;
import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.model.Article;
import com.fu.demo.service.AccountService;
import com.fu.demo.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ArticleController", description = "文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AccountService accountService;
	
	@ApiOperation("获取所有文章列表")
	@GetMapping("/all")
	public List<ArticleDto> allArticle() {
		return articleService.listAllArticle();
	}
	
	@ApiOperation("创建文章")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public int createArticle(Principal principal, @RequestBody CreateArticleDto createAccountDto) {
		String email = principal.getName();
		long userId = accountService.getUserIdByEmail(email);
		articleService.createArticle(createAccountDto, userId);
		return 0;
	}
}
