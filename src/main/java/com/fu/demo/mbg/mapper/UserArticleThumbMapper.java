package com.fu.demo.mbg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserArticleThumbMapper {

	void thumb(@Param("articleId") long articleId, @Param("userId") long userId);
	
	void unThumb(@Param("articleId") long articleId, @Param("userId") long userId);

	long getThumbNum(@Param("articleId") long articleId, @Param("userId") long userId);
	
}
