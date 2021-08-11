package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class ArticleThumbResponseDto {
	@ApiModelProperty(value = "thumbNumber")
	private int thumbNumber;
	
	@ApiModelProperty(value = "thumbState")
	private boolean thumbState;

	public int getThumbNumber() {
		return thumbNumber;
	}

	public void setThumbNumber(int thumbNumber) {
		this.thumbNumber = thumbNumber;
	}

	public boolean isThumbState() {
		return thumbState;
	}

	public void setThumbState(boolean thumbState) {
		this.thumbState = thumbState;
	}
}
