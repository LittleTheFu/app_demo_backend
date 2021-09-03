package com.fu.demo.mbg.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ArticleDto {
	@ApiModelProperty(value = "id")
	private long id;
	
	@ApiModelProperty(value = "title")
	private String title;
	
	@ApiModelProperty(value = "content")
	private String content;
	
	@ApiModelProperty(value = "author")
	private String author;
	
	@ApiModelProperty(value = "authorId")
	private long authorId;
	
	@ApiModelProperty(value = "authorIcon")
	private String authorIcon;
	
	@ApiModelProperty(value = "thumb")
	private long thumb;
	
	@ApiModelProperty(value = "thumbState")
	private boolean thumbState;
	
	@ApiModelProperty(value = "deletable")
	private boolean deletable;
	
	@ApiModelProperty(value = "editable")
	private boolean editable;
	
	@ApiModelProperty(value = "bookmarked")
	private boolean bookmarked;
	
	@ApiModelProperty(value = "tags")
	private List<String> tags;
	
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
	
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	
	public long getAuthorId() {
		return authorId;
	}
	
	public void setAuthorIcon(String authorIcon) {
		this.authorIcon = authorIcon;
	}
	
	public String getAuthorIcon() {
		return this.authorIcon;
	}
	
	public long getThumb() {
		return thumb;
	}
	
	public void setThumb(long thumb) {
		this.thumb = thumb;
	}
	
	public void setThumbState(boolean thumbState) {
		this.thumbState = thumbState;
	}
	
	public boolean getThumbState() {
		return thumbState;
	}
	
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}
	
	public boolean getDeletable() {
		return this.deletable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public boolean getEditable() {
		return this.editable;
	}

	public boolean isBookmarked() {
		return bookmarked;
	}

	public void setBookmarked(boolean bookmarked) {
		this.bookmarked = bookmarked;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
