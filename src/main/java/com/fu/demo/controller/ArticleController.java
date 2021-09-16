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

import com.fu.demo.common.api.AppException;
import com.fu.demo.common.api.CommonResult;
import com.fu.demo.common.api.ConstMessage;
import com.fu.demo.mbg.dto.AddArticleTagDto;
import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.ArticleThumbResponseDto;
import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.CreateArticleResponseDto;
import com.fu.demo.mbg.dto.CreateCommentDto;
import com.fu.demo.mbg.dto.PageWrapper;
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

	@ApiOperation("获取某个用户的文章的标题")
	@GetMapping("/get_titles_by_user/{id}")
	public CommonResult<PageWrapper<List<TitleResponseDto>>> getTitlesByUser(@PathVariable("id") long id,
			@RequestParam("page") int page) {
		return CommonResult.success(articleService.getTitlesByUser(id, page));
	}

	@ApiOperation("获取所有文章列表")
	@GetMapping("/all")
	public CommonResult<PageWrapper<List<TitleResponseDto>>> allArticle(@RequestParam("page") int page) {
		long userId = userService.getCurrentUserId();

//		long x = 1/0;
//		if(true)
//		throw new AppException(ConstMessage.ERROR_MSG);

//		TitleResponseDto titleResponseDto = null;
//		titleResponseDto.setId(9);

		return CommonResult.success(articleService.listAllArticle(userId, page));
	}

	@ApiOperation("根据tag获取标题")
	@GetMapping("/get_titles_by_tag")
	public CommonResult<PageWrapper<List<TitleResponseDto>>> getTitlesByTag(@RequestParam("tag") String tag,
			@RequestParam("page") int page) {
		return CommonResult.success(articleService.getTitleByTag(tag, page));
	}

	@RequestMapping(value = "/delete_article_tag", method = RequestMethod.DELETE)
	@ResponseBody
	public CommonResult deleteArticleTag(@RequestParam("tag") String tag, @RequestParam("id") long id) {
		long userId = userService.getCurrentUserId();
		articleService.deleteArticleTag(id, tag, userId);

		return CommonResult.success(null);
	}

	@RequestMapping(value = "/add_article_tag/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult addArticleTag(@PathVariable("id") long id, @RequestBody AddArticleTagDto addArticleTagDto) {
		articleService.addArticleTag(id, addArticleTagDto.getTag());

		return CommonResult.success(null);
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
		articleService.updateArticle(updateArticleDto, userId);

		return CommonResult.success(null);
	}

	@ApiOperation("获取指定文章的评论")
	@GetMapping("/article_comments/{article_id}")
	public CommonResult<PageWrapper<List<CommentResponseDto>>> getArticleComments(
			@PathVariable("article_id") long article_id, @RequestParam("sort") String sortType,
			@RequestParam("page") int page) {
		long userId = userService.getCurrentUserId();

		boolean isSortByDate = (sortType.equals("date"));
		return CommonResult.success(articleService.getArticleComments(article_id, userId, isSortByDate, page));
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
	public CommonResult<PageWrapper<List<TitleResponseDto>>> getBookmarkArticles(@RequestParam("page") int page) {
		long userId = userService.getCurrentUserId();

		return CommonResult.success(articleService.getBookmarkedArticles(userId, page));
	}

	@ApiOperation("点赞文章")
	@RequestMapping(value = "/thumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<ArticleThumbResponseDto> thumbArticle(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();

		if (articleService.isThumbed(id, userId)) {
//			return CommonResult.success(null, "已经点赞过了啊！");
			throw new AppException(ConstMessage.YOU_HAVE_THUMBED_IT_BEFORE);
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
//			return CommonResult.success(null, "你之前根本没有点赞啊！");
			throw new AppException(ConstMessage.YOU_HAVE_NOT_THUMBED_IT_YET);
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
		articleService.deleteArticle(id, userId);

		return CommonResult.success(null);
	}

	@ApiOperation("点赞评论")
	@RequestMapping(value = "/comment_thumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult thumbComment(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();
		articleService.thumbComment(id, userId);

		return CommonResult.success(null);
	}

	@ApiOperation("取消点赞评论")
	@RequestMapping(value = "/comment_unthumb/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult unThumbComment(@PathVariable("id") Long id) {
		long userId = userService.getCurrentUserId();
		articleService.unthumbComment(id, userId);

		return CommonResult.failed("操作失败");
	}
}
