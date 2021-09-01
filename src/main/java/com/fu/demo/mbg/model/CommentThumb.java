package com.fu.demo.mbg.model;

import io.swagger.annotations.ApiModelProperty;

public class CommentThumb {
	
	@ApiModelProperty(value = "commentThumbCommentId")
	private long commentThumbCommentId;
	
	@ApiModelProperty(value = "commentThumbUserId")
	private long commentThumbUserId;

	public long getCommentThumbCommentId() {
		return commentThumbCommentId;
	}

	public void setCommentThumbCommentId(long commentThumbCommentId) {
		this.commentThumbCommentId = commentThumbCommentId;
	}

	public long getCommentThumbUserId() {
		return commentThumbUserId;
	}

	public void setCommentThumbUserId(long commentThumbUserId) {
		this.commentThumbUserId = commentThumbUserId;
	}
}
