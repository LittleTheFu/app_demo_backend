package com.fu.demo.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.mapper.ArticleMapper;
import com.fu.demo.mbg.mapper.UserArticleThumbMapper;
import com.fu.demo.mbg.mapper.UserMapper;
import com.fu.demo.mbg.model.Article;
import com.fu.demo.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private UserArticleThumbMapper thumbMapper;

	@Override
	public List<ArticleDto> listAllArticle() {
		return articleMapper.queryAllArticle();
	}

	@Override
	public List<ArticleDto> listAllArticle(long userId) {
		List<ArticleDto> articles = articleMapper.queryAllArticle();

		Iterator<ArticleDto> iter = articles.iterator();

		while (iter.hasNext()) {
			ArticleDto article = iter.next();
			
			boolean isThumbed = articleMapper.isThumbed(article.getId(), userId);
			article.setThumbState(isThumbed);
		}

		return articles;
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

	@Override
	public void thumb(long articleId, long userId) {
		if (!isThumbed(articleId, userId)) {
			thumbMapper.thumb(articleId, userId);
		}
	}

	@Override
	public void unThumb(long articleId, long userId) {
		if (isThumbed(articleId, userId)) {
			thumbMapper.unThumb(articleId, userId);
		}
	}

	@Override
	public boolean isThumbed(long articleId, long userId) {
		return thumbMapper.getThumbNum(articleId, userId) > 0;
	}

	@Override
	public ArticleDto getArticleById(long id) {
		return articleMapper.queryById(id);
	}

	@Override
	public ArticleDto getArticleById(long id, long userId) {
		ArticleDto article = articleMapper.queryById(id);
		article.setThumbState(articleMapper.isThumbed(id, userId));
		
		return article;
	}

	@Override
	public boolean isArticleThumbed(long id, long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getArticleThumbNumber(long id) {
		return articleMapper.queryThumbNumber(id);
	}
}
