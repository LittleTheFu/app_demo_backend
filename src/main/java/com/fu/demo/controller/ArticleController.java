package com.fu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.common.api.CommonResult;
import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.ArticleThumbResponseDto;
import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.CreateArticleResponseDto;
import com.fu.demo.mbg.dto.CreateCommentDto;
import com.fu.demo.mbg.dto.TitleResponseDto;
import com.fu.demo.mbg.dto.UpdateArticleDto;
import com.fu.demo.service.ArticleService;
import com.fu.demo.service.HistoryService;
import com.fu.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ArticleController", description = "文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	@Autowired
	private HistoryService historyService;

	@ApiOperation("获取所有文章列表")
	@GetMapping("/all")
	public CommonResult<List<ArticleDto>> allArticle() {
		long userId = userService.getCurrentUserId();

		return CommonResult.success(articleService.listAllArticle(userId));
	}

	@ApiOperation("获取指定文章")
	@GetMapping("/{id}")
	public CommonResult<ArticleDto> getArticle(@PathVariable("id") long id) {
		long userId = userService.getCurrentUserId();
		historyService.insertHistory(userId, id);

		return CommonResult.success(articleService.getArticleById(id, userId));
	}

	@ApiOperation("修改指定文章")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public CommonResult putArticle(@PathVariable("id") long id, @RequestBody UpdateArticleDto updateArticleDto) {
		long userId = userService.getCurrentUserId();

		boolean result = articleService.updateArticle(updateArticleDto, userId);

		if (result) {
			return CommonResult.success(null);
		}

		return CommonResult.failed("操作失败");
	}

	@ApiOperation("获取指定文章的评论")
	@GetMapping("/article_comments/{article_id}")
	public CommonResult<List<CommentResponseDto>> getArticleComments(@PathVariable("article_id") long article_id,
			@RequestParam("sort") String sortType) {
		long userId = userService.getCurrentUserId();

		boolean isSortByDate = (sortType.equals("date"));
		return CommonResult.success(articleService.getArticleComments(article_id, userId, isSortByDate));
	}

	@ApiOperation("创建文章")
	@RequestMapping(value = "/create_article", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<CreateArticleResponseDto> createArticle(@RequestBody CreateArticleDto createArticleDto) {
		long userId = userService.getCurrentUserId();

		long articleId = articleService.createArticle(createArticleDto, userId);

		CreateArticleResponseDto ret = new CreateArticleResponseDto();
		ret.setId(articleId);

		return CommonResult.success(ret);
	}

	@ApiOperation("创建评论")
	@RequestMapping(value = "/create_comment/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<CommentResponseDto> createComment(@PathVariable("id") Long id,
			@RequestBody CreateCommentDto createCommentDto) {
		long userId = userService.getCurrentUserId();

		long commentId = articleService.createComment(id, userId, createCommentDto.getContent());

		CommentResponseDto ret = articleService.getArticleCommentById(commentId);

		return CommonResult.success(ret);
	}

	@ApiOperation("收藏文章")
	@RequestMapping(value = "/bookmark/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult bookmarkArticle(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();

		articleService.bookmark(id, userId);

		return CommonResult.success(null);
	}

	@ApiOperation("取消收藏文章")
	@RequestMapping(value = "/unbookmark/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult unBookmarkArticle(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();

		articleService.unBookmark(id, userId);

		return CommonResult.success(null);
	}

	@ApiOperation("获取所有收藏文章")
	@GetMapping("/get_bookmark_articles")
	public CommonResult<List<TitleResponseDto>> getBookmarkArticles() {
		long userId = userService.getCurrentUserId();

		return CommonResult.success(articleService.getBookmarkedArticles(userId));
	}

	@ApiOperation("点赞文章")
	@RequestMapping(value = "/thumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<ArticleThumbResponseDto> thumbArticle(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();

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
	public CommonResult<ArticleThumbResponseDto> unThumbArticle(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();

		if (articleService.isThumbed(id, userId) == false) {
			return CommonResult.success(null, "你之前根本没有点赞啊！");
		}

		articleService.unThumb(id, userId);

		ArticleThumbResponseDto retObj = new ArticleThumbResponseDto();
		retObj.setThumbState(false);
		retObj.setThumb(articleService.getArticleThumbNumber(id));

		return CommonResult.success(retObj);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public CommonResult deleteArticle(@PathVariable("id") long id) {
		long userId = userService.getCurrentUserId();
		int count = articleService.deleteArticle(id, userId);
		if (count == 1) {
//            LOGGER.debug("deleteBrand success :id={}", id);
			return CommonResult.success(null);
		} else {
//            LOGGER.debug("deleteBrand failed :id={}", id);
			return CommonResult.failed("操作失败");
		}
	}

	@ApiOperation("点赞评论")
	@RequestMapping(value = "/comment_thumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult thumbComment(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();

		boolean result = articleService.thumbComment(id, userId);

		if (false == result) {
			return CommonResult.failed("操作失败");

		}

		return CommonResult.success(null);
	}

	@ApiOperation("取消点赞评论")
	@RequestMapping(value = "/comment_unthumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult unThumbComment(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();

		boolean result = articleService.unthumbComment(id, userId);

		if (false == result) {
			return CommonResult.failed("操作失败");

		}

		return CommonResult.success(null);
	}

}
