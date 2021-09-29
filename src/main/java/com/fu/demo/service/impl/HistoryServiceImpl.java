package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.PageWrapper;
import com.fu.demo.mbg.dto.TitleResponseDto;
import com.fu.demo.mbg.mapper.HistoryMapper;
import com.fu.demo.service.HistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryMapper historyMapper;

	@Override
	public void insertHistory(long userId, long articleId) {
		historyMapper.insertHistory(userId, articleId);
	}

	@Override
	public PageWrapper<List<TitleResponseDto>> getHistory(long userId, int page) {
		final int ITEM_PER_PAGE = 4;

		PageHelper.startPage(page, ITEM_PER_PAGE);
		List<TitleResponseDto> titles = historyMapper.queryHistoryByUserId(userId);
		PageInfo<TitleResponseDto> pageInfo = new PageInfo<TitleResponseDto>(titles);
		
		PageWrapper<List<TitleResponseDto>> wrapper = new PageWrapper<List<TitleResponseDto>>();
		wrapper.setContent(titles);
		wrapper.setPages(pageInfo.getPages());
		wrapper.setPageNum(pageInfo.getPageNum());
		
		return wrapper;
	}
}
