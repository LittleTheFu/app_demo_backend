package com.fu.demo.mbg.model;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class Comment implements Serializable{
	@ApiModelProperty(value = "id")
	private long id;

	@ApiModelProperty(value = "articleCommentArticleId")
	private long articleCommentArticleId;
	
	@ApiModelProperty(value = "articleCommentUserId")
	private long articleCommentUserId;
	
	@ApiModelProperty(value = "articleCommentContent")
	private String articleCommentContent;
	
	@ApiModelProperty(value = "articleCommentDate")
	private Date articleCommentDate;
	
	private static final long serialVersionUID = 1L;

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
}
