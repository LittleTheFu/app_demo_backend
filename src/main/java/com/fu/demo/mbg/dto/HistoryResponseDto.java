package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class HistoryResponseDto {
	@ApiModelProperty(value = "id")
	private long id;
	
	@ApiModelProperty(value = "title")
	private String title;
	
	@ApiModelProperty(value = "authorName")
	private String authorName;
	
	@ApiModelProperty(value = "authorIcon")
	private String authorIcon;
	
	@ApiModelProperty(value = "authorId")
	private long authorId;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorIcon() {
		return authorIcon;
	}

	public void setAuthorIcon(String authorIcon) {
		this.authorIcon = authorIcon;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

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
}