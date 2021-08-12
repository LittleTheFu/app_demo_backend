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
import com.fu.demo.mbg.dto.ArticleThumbResponseDto;
import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.CreateCommentDto;
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
	public CommonResult<List<ArticleDto>> allArticle(Authentication authentication) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();

		return CommonResult.success(articleService.listAllArticle(userId));
	}

	@ApiOperation("获取指定文章")
	@GetMapping("/{id}")
	public CommonResult<ArticleDto> getArticle(Authentication authentication, @PathVariable("id") long id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();

		return CommonResult.success(articleService.getArticleById(id, userId));
	}

	@ApiOperation("获取指定文章的评论")
	@GetMapping("/article_comments/{article_id}")
	public CommonResult<List<CommentResponseDto>> getArticleComments(@PathVariable("article_id") long article_id) {
		return CommonResult.success(articleService.getArticleComments(article_id));
	}

	@ApiOperation("创建评论")
	@RequestMapping(value = "/create_comment/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<List<CommentResponseDto>> createComment(Authentication authentication,
			@PathVariable("id") Long id, @RequestBody CreateCommentDto createCommentDto) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();

		articleService.createComment(id, userId, createCommentDto.getContent());
		
		List<CommentResponseDto> ret = articleService.getArticleComments(id);

		return CommonResult.success(ret);
	}

	@ApiOperation("点赞文章")
	@RequestMapping(value = "/thumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<ArticleThumbResponseDto> thumbArticle(Authentication authentication,
			@PathVariable("id") Long id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();

		if (articleService.isThumbed(id, userId)) {
			return CommonResult.success(null, "已经点赞过了啊！");
		}

		articleService.thumb(id, userId);

		ArticleThumbResponseDto retObj = new ArticleThumbResponseDto();
		retObj.setThumbState(true);
		retObj.setThumb(articleService.getArticleThumbNumber(id));

		return CommonResult.success(retObj);
	}

	@ApiOperation("取消点赞文章")
	@RequestMapping(value = "/unthumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<ArticleThumbResponseDto> unThumbArticle(Authentication authentication,
			@PathVariable("id") Long id) {
		AccountDetail detail = (AccountDetail) authentication.getPrincipal();
		long userId = detail.getUserId();

		if (articleService.isThumbed(id, userId) == false) {
			return CommonResult.success(null, "你之前根本没有点赞啊！");
		}

		articleService.unThumb(id, userId);

		ArticleThumbResponseDto retObj = new ArticleThumbResponseDto();
		retObj.setThumbState(false);
		retObj.setThumb(articleService.getArticleThumbNumber(id));

		return CommonResult.success(retObj);
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
