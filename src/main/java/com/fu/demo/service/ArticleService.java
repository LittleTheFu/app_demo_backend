package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CreateArticleDto;

public interface ArticleService {
	public List<ArticleDto> listAllArticle();
	
	public List<ArticleDto> listAllArticle(long userId);
	
	public ArticleDto getArticleById(long id);
	
	public ArticleDto getArticleById(long id, long userId);
	
	public boolean isArticleThumbed(long id, long userId);

	public void createArticle(CreateArticleDto createArticleDto, long userId);
	
	int deleteArticle(long id);
	
	boolean isThumbed(long articleId, long userId);
	
	void thumb(long articleId, long userId);
	
	void unThumb(long articleId, long userId);
}
