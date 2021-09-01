package com.fu.demo.mbg.dto;

import java.util.Date;

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
	
	@ApiModelProperty(value = "articleCommentUserIcon")
	private String articleCommentUserIcon;
	
	@ApiModelProperty(value = "articleCommentContent")
	private String articleCommentContent;
	
	@ApiModelProperty(value = "articleCommentDate")
	private Date articleCommentDate;
	
	@ApiModelProperty(value = "thumbState")
	private boolean thumbState;
	
	@ApiModelProperty(value = "articleCommentThumbNum")
	private long articleCommentThumbNum;

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
	
	public String getArticleCommentUserIcon() {
		return articleCommentUserIcon;
	}

	public void setArticleCommentUserIcon(String articleCommentUserIcon) {
		this.articleCommentUserIcon = articleCommentUserIcon;
	}

	public String getArticleCommentContent() {
		return articleCommentContent;
	}

	public void setArticleCommentContent(String articleCommentContent) {
		this.articleCommentContent = articleCommentContent;
	}

	public Date getArticleCommentDate() {
		return articleCommentDate;
	}

	public void setArticleCommentDate(Date articleCommentDate) {
		this.articleCommentDate = articleCommentDate;
	}

	public boolean isThumbState() {
		return thumbState;
	}

	public void setThumbState(boolean thumbState) {
		this.thumbState = thumbState;
	}

	public long getArticleCommentThumbNum() {
		return articleCommentThumbNum;
	}

	public void setArticleCommentThumbNum(long articleCommentThumbNum) {
		this.articleCommentThumbNum = articleCommentThumbNum;
	}
}
