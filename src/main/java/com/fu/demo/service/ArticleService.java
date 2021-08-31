package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.TitleResponseDto;
import com.fu.demo.mbg.dto.UpdateArticleDto;

public interface ArticleService {
	public List<ArticleDto> listAllArticle();
	
	public List<ArticleDto> listAllArticle(long userId);
	
	public ArticleDto getArticleById(long id);
	
	public ArticleDto getArticleById(long id, long userId);
	
	public int getArticleThumbNumber(long id);
	
	public boolean isArticleThumbed(long id, long userId);

	public long createArticle(CreateArticleDto createArticleDto, long userId);
	
	public boolean updateArticle(UpdateArticleDto updateArticleDto, long userId);

	public long createComment(long articleId, long userId, String content);
	
	public List<CommentResponseDto> getArticleComments(long articleId);
	
	public CommentResponseDto getArticleCommentById(long commentId);
	
	int deleteArticle(long id, long userId);
	
	boolean isThumbed(long articleId, long userId);
	
	void thumb(long articleId, long userId);
	
	void unThumb(long articleId, long userId);
	
	void bookmark(long articleId, long userId);
	
	void unBookmark(long articleId, long userId);
	
	List<TitleResponseDto> getBookmarkedArticles(long userId);
}
