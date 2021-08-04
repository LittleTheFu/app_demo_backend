package com.fu.demo.mbg.model;

import io.swagger.annotations.ApiModelProperty;

public class UserArticleThumb {
	@ApiModelProperty(value = "id")
	private long id;

	@ApiModelProperty(value = "thumbUserId")
	private long thumbUserId;

	@ApiModelProperty(value = "thumbArticleId")
	private long thumbArticleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getThumbUserId() {
		return thumbUserId;
	}

	public void setThumbUserId(long thumbUserId) {
		this.thumbUserId = thumbUserId;
	}

	public long getThumbArticleId() {
		return thumbArticleId;
	}

	public void setThumbArticleId(long thumbArticleId) {
		this.thumbArticleId = thumbArticleId;
	}

}
