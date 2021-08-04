package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.mapper.ArticleMapper;
import com.fu.demo.mbg.mapper.UserMapper;
import com.fu.demo.mbg.model.Article;
import com.fu.demo.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<ArticleDto> listAllArticle() {
		return articleMapper.queryAllArticle();
	}

	@Override
	public void createArticle(CreateArticleDto craeteArticleDto, long userId) {
		Article article = new Article();
		BeanUtils.copyProperties(craeteArticleDto, article);
		article.setArticleUserId(userId);

		articleMapper.insert(article);
	}

	@Override
	public int deleteArticle(long id) {
		return articleMapper.delete(id);
	}

}
