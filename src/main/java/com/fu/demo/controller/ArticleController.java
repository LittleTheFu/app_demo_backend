package com.fu.demo.controller;

import java.security.Principal;
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
	public CommonResult createArticle(Authentication authentication, @RequestBody CreateArticleDto createAccountDto) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();
		
		articleService.createArticle(createAccountDto, userId);
		
		return CommonResult.success(null);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult deleteArticle(@PathVariable("id") Long id) {
		int count = articleService.deleteArticle(id);
		if (count == 1) {
//            LOGGER.debug("deleteBrand success :id={}", id);
			return CommonResult.success(null);
		} else {
//            LOGGER.debug("deleteBrand failed :id={}", id);
			return CommonResult.failed("操作失败");
		}
	}
}
