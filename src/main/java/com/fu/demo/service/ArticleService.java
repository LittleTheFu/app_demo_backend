package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.PageWrapper;
import com.fu.demo.mbg.dto.TitleResponseDto;
import com.fu.demo.mbg.dto.UpdateArticleDto;

public interface ArticleService {

	public PageWrapper<List<TitleResponseDto>> getTitlesByUser(long userId, int page);

	public List<ArticleDto> listAllArticle();

	public PageWrapper<List<ArticleDto>> listAllArticle(long userId, int page);

	public ArticleDto getArticleById(long id);

	public ArticleDto getArticleById(long id, long userId);

	public List<TitleResponseDto> getTitleByTag(String tag);

	public boolean deleteArticleTag(long articleId, String tag, long userId);

	public boolean addArticleTag(long articleId, String tag);

	public int getArticleThumbNumber(long id);

	public boolean isArticleThumbed(long id, long userId);

	public long createArticle(CreateArticleDto createArticleDto, long userId);

	public boolean updateArticle(UpdateArticleDto updateArticleDto, long userId);

	public long createComment(long articleId, long userId, String content);

	public PageWrapper<List<CommentResponseDto>> getArticleComments(long articleId, long userId, boolean isSortByDate,
			int page);

	public CommentResponseDto getArticleCommentById(long commentId);

	int deleteArticle(long id, long userId);

	boolean isThumbed(long articleId, long userId);

	void thumb(long articleId, long userId);

	void unThumb(long articleId, long userId);

	void bookmark(long articleId, long userId);

	void unBookmark(long articleId, long userId);

	PageWrapper<List<TitleResponseDto>> getBookmarkedArticles(long userId, int page);

	boolean thumbComment(long commentId, long userId);

	boolean unthumbComment(long commentId, long userId);
}
