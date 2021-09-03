package com.fu.demo.mbg.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ArticleTag implements Serializable{
	@ApiModelProperty(value = "articleId")
	private long articleId;
	
	@ApiModelProperty(value = "articleTag")
	private String articleTag;
	
	private static final long serialVersionUID = 1L;

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getArticleTag() {
		return articleTag;
	}

	public void setArticleTag(String articleTag) {
		this.articleTag = articleTag;
	}
}