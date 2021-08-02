package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.model.Article;

@Mapper
public interface ArticleMapper {
    List<ArticleDto> queryAllArticle();
}
