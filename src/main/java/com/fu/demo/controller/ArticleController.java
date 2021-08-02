package com.fu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.model.Article;
import com.fu.demo.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ArticleController", description = "文章管理")
@RestController
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@ApiOperation("获取所有文章列表")
	@GetMapping("/article")
	public List<ArticleDto> allArticle() {
		return articleService.listAllArticle();
	}
}
