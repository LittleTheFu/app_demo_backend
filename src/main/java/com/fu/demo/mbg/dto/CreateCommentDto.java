package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class CreateCommentDto {
	@ApiModelProperty(value = "content")
	private String content;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
