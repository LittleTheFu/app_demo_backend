package com.fu.demo.mbg.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
	void insertHistory(long userId, long articleId);
}
