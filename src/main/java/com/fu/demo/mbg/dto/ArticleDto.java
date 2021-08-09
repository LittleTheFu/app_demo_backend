package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class ArticleDto {
	@ApiModelProperty(value = "")
	private long id;
	
	@ApiModelProperty(value = "title")
	private String title;
	
	@ApiModelProperty(value = "content")
	private String content;
	
	@ApiModelProperty(value = "author")
	private String author;
	
	@ApiModelProperty(value = "thumb")
	private Long thumb;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public long getThumb() {
		return thumb;
	}
	
	public void setThumb(Long thumb) {
		this.thumb = thumb;
	}
}
