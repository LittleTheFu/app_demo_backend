package com.fu.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.dto.TitleResponseDto;

public interface HistoryService {
	void insertHistory(@Param("userId") long userId, @Param("articleId") long articleId);
	
	List<TitleResponseDto> getHistory(long userId);
}
