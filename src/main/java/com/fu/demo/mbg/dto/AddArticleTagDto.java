package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class AddArticleTagDto {
	
	@ApiModelProperty(value = "tag")
	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
