package com.fu.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.mapper.HistoryMapper;
import com.fu.demo.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{

	@Autowired
	private HistoryMapper historyMapper;
	
	@Override
	public void insertHistory(long userId, long articleId) {
		historyMapper.insertHistory(userId, articleId);
	}
}
