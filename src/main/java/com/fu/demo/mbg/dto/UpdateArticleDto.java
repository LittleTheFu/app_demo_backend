package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class UpdateArticleDto {
	@ApiModelProperty(value = "id")
	private long id;
	
	@ApiModelProperty(value = "title")
	private String title;
	
	@ApiModelProperty(value = "content")
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
}
