package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class FollowResponseDto {
	@ApiModelProperty(value = "followed")
	private boolean followed;

	public boolean isFollowed() {
		return followed;
	}

	public void setFollowed(boolean followed) {
		this.followed = followed;
	}
}
