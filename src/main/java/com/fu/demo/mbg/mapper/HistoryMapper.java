package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fu.demo.mbg.dto.TitleResponseDto;

@Mapper
public interface HistoryMapper {
	void insertHistory(long userId, long articleId);
	
	List<TitleResponseDto> queryHistoryByUserId(long userId);
}
