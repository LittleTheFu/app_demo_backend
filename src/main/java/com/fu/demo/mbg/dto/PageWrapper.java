package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class PageWrapper<T> {
	@ApiModelProperty(value = "pageNum")
	private long pageNum;
	
	@ApiModelProperty(value = "pages")
	private long pages;

	@ApiModelProperty(value = "content")
	private T content;

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
