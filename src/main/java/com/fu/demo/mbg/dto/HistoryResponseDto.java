package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class HistoryResponseDto {
	@ApiModelProperty(value = "id")
	private long id;
	
	@ApiModelProperty(value = "title")
	private String title;

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
}
