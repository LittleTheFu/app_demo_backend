package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.model.Comment;

@Mapper
public interface CommentMapper {
	List<CommentResponseDto> queryCommentByArticleId(@Param("articleId") long articleId,
			@Param("order_type") String order_type);

//	void insertComment(@Param("articleId") long articleId, @Param("userId") long userId,
//			@Param("content") String content);

	void insertComment(Comment comment);

	CommentResponseDto queryById(@Param("id") long id);

	long thumb(@Param("commentId") long commentId, @Param("userId") long userId);

	long unThumb(@Param("commentId") long commentId, @Param("userId") long userId);

	boolean isThumbed(@Param("commentId") long commentId, @Param("userId") long userId);
	
	long queryThumbNum(@Param("commentId") long commentId);
	
	void incThumb(@Param("commentId") long commentId);
	
	void decThumb(@Param("commentId") long commentId);
}
