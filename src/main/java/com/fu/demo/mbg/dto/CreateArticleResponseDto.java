package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class CreateArticleResponseDto {
	@ApiModelProperty(value = "id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
