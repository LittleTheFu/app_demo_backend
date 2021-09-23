package com.fu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.common.api.CommonResult;
import com.fu.demo.mbg.dto.PageWrapper;
import com.fu.demo.mbg.dto.TitleResponseDto;
import com.fu.demo.service.HistoryService;
import com.fu.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "历史管理")
@RestController
@RequestMapping("/history")
public class HistoryController {
	@Autowired
	HistoryService historyService;

	@Autowired
	UserService userService;

	@ApiOperation("获取所有历史文章列表")
	@GetMapping
	public CommonResult<PageWrapper<List<TitleResponseDto>>> getHistory(@RequestParam("page") int page) {
		long userId = userService.getCurrentUserId();

		return CommonResult.success(historyService.getHistory(userId, page));
	}
}
