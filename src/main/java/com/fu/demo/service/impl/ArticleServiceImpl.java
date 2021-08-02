package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.mapper.ArticleMapper;
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

}
