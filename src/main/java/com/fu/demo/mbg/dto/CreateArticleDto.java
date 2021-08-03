package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class CreateArticleDto {
	@ApiModelProperty(value = "title")
	private String title;
	
	@ApiModelProperty(value = "content")
	private String content;
	
//	@ApiModelProperty(value = "articleUserId")
//	private long articleUserId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

//	public long getArticleUserId() {
//		return articleUserId;
//	}
//
//	public void setArticleUserId(long articleUserId) {
//		this.articleUserId = articleUserId;
//	}
}
