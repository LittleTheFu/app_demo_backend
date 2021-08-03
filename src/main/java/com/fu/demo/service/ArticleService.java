package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.model.Article;

public interface ArticleService {
	public List<ArticleDto> listAllArticle();
	
	public void createArticle(CreateArticleDto createArticleDto, long userId);
}
