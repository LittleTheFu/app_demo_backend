package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.model.Article;

@Mapper
public interface ArticleMapper {
	List<ArticleDto> queryAllArticle();

	void insert(Article article);
	
	int delete(@Param("id") long id);
	
}
