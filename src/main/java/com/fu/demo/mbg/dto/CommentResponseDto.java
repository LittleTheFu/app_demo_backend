package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class CommentResponseDto {
	@ApiModelProperty(value = "id")
	private long id;
	
	@ApiModelProperty(value = "articleCommentArticleId")
	private long articleCommentArticleId;
	
	@ApiModelProperty(value = "articleCommentUserId")
	private long articleCommentUserId;
	
	@ApiModelProperty(value = "articleCommentUserName")
	private String articleCommentUserName;
	
	@ApiModelProperty(value = "articleCommentContent")
	private String articleCommentContent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getArticleCommentArticleId() {
		return articleCommentArticleId;
	}

	public void setArticleCommentArticleId(long articleCommentArticleId) {
		this.articleCommentArticleId = articleCommentArticleId;
	}

	public long getArticleCommentUserId() {
		return articleCommentUserId;
	}

	public void setArticleCommentUserId(long articleCommentUserId) {
		this.articleCommentUserId = articleCommentUserId;
	}

	public String getArticleCommentUserName() {
		return articleCommentUserName;
	}

	public void setArticleCommentUserName(String articleCommentUserName) {
		this.articleCommentUserName = articleCommentUserName;
	}

	public String getArticleCommentContent() {
		return articleCommentContent;
	}

	public void setArticleCommentContent(String articleCommentContent) {
		this.articleCommentContent = articleCommentContent;
	}
	
}
