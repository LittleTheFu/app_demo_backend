package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.TitleResponseDto;
import com.fu.demo.mbg.model.Article;
import com.fu.demo.mbg.model.ArticleTag;

@Mapper
public interface ArticleMapper {
	ArticleTag getTag(@Param("id") long id);

	List<TitleResponseDto> queryTitlesByTag(@Param("tag") String tag);

	long deleteArticleTag(@Param("id") long id, @Param("tag") String tag);

	List<TitleResponseDto> queryTitlesByUser(@Param("userId") long userId);

	List<ArticleDto> queryAllArticle();

	List<ArticleDto> queryAllArticleWithThumbState(@Param("userId") long userId);

	ArticleDto queryById(long id);

	ArticleDto queryByIdWithThumbState(@Param("id") long id, @Param("userId") long userId);

	int queryThumbNumber(@Param("id") long id);

	boolean isThumbed(@Param("id") long id, @Param("userId") long userId);

	boolean isBookmarked(@Param("id") long id, @Param("userId") long userId);

	void bookmark(@Param("articleId") long articleId, @Param("userId") long userId);

	void unBookmark(@Param("articleId") long articleId, @Param("userId") long userId);

	List<TitleResponseDto> queryBookmarkedArticle(@Param("userId") long userId);

	void insert(Article article);

	int delete(@Param("id") long id);

	long queryAuthorId(@Param("id") long id);

	void updateArticle(@Param("id") long id, @Param("title") String title, @Param("content") String content);
}
