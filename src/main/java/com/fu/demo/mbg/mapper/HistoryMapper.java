package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fu.demo.mbg.dto.HistoryResponseDto;

@Mapper
public interface HistoryMapper {
	void insertHistory(long userId, long articleId);
	
	List<HistoryResponseDto> queryHistoryByUserId(long userId);
}
