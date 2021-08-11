package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class ArticleThumbResponseDto {
	@ApiModelProperty(value = "thumb")
	private int thumb;
	
	@ApiModelProperty(value = "thumbState")
	private boolean thumbState;

	public int getThumb() {
		return thumb;
	}

	public void setThumb(int thumb) {
		this.thumb = thumb;
	}

	public boolean isThumbState() {
		return thumbState;
	}

	public void setThumbState(boolean thumbState) {
		this.thumbState = thumbState;
	}
}
