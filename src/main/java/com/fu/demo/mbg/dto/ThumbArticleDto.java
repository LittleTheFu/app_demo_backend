package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class ThumbArticleDto {
	@ApiModelProperty(value = "articleId")
	private long articleId;

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
}
