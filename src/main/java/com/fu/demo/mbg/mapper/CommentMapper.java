package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateCommentDto;

@Mapper
public interface CommentMapper {
	List<CommentResponseDto> queryCommentByArticleId(@Param("articleId") long articleId);

	void insertComment(@Param("articleId") long articleId, @Param("userId") long userId,
			@Param("content") String content);
	
	CommentResponseDto queryById(@Param("id") long id);
}
