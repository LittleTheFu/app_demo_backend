package com.fu.demo.mbg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class FollowDto {
	@JsonIgnore
	private long fromId;

	@ApiModelProperty(value = "toId")
	private long toId;

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public long getToId() {
		return toId;
	}

	public void setToId(long toId) {
		this.toId = toId;
	}
}
