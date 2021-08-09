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
import com.fu.demo.mbg.model.Account;
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
	public CommonResult<List<ArticleDto>>  allArticle() {
		return CommonResult.success(articleService.listAllArticle());
	}
	
	@ApiOperation("获取指定文章")
	@GetMapping("/{id}")
	public CommonResult<ArticleDto>  getArticle(@PathVariable("id") long id) {
		return CommonResult.success(articleService.getArticleById(id));
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
	
	@ApiOperation("点赞文章")
	@RequestMapping(value = "/thumb/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult thumbArticle(Authentication authentication, @PathVariable("id") Long id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();
		
		if(articleService.isThumbed(id, userId)) {
			return CommonResult.success(null, "已经点赞过了啊！");
		}
		
		articleService.thumb(id, userId);
		
		return CommonResult.success(null);
	}
	
	@ApiOperation("取消点赞文章")
	@RequestMapping(value = "/unthumb/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult unThumbArticle(Authentication authentication, @PathVariable("id") Long id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();
		
		if(articleService.isThumbed(id, userId) == false) {
			return CommonResult.success(null, "你之前根本没有点赞啊！");
		}
		
		articleService.unThumb(id, userId);
		
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
