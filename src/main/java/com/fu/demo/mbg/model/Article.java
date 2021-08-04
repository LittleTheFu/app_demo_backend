package com.fu.demo.mbg.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class Article implements Serializable {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "title")
	private String title;

	@ApiModelProperty(value = "content")
	private String content;
	
	@ApiModelProperty(value = "articleUserId")
	private Long articleUserId;
	
	@ApiModelProperty(value = "thumb")
	private Long thumb;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getArticleUserId() {
		return id;
	}

	public void setArticleUserId(Long id) {
		this.id = id;
	}
	
	public void setThumb(Long thumb) {
		this.thumb = thumb;
	}
	
	public long getThumb() {
		return thumb;
	}
}
