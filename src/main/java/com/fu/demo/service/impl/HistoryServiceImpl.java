package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.HistoryResponseDto;
import com.fu.demo.mbg.mapper.HistoryMapper;
import com.fu.demo.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryMapper historyMapper;

	@Override
	public void insertHistory(long userId, long articleId) {
		historyMapper.insertHistory(userId, articleId);
	}

	@Override
	public List<HistoryResponseDto> getHistory(long userId) {
		return historyMapper.queryHistoryByUserId(userId);
	}
}

