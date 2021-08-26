package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class CreateMailDto {
	@ApiModelProperty(value = "mailToId")
	private long mailToId;
	
	@ApiModelProperty(value = "content")
	private String content;

	public long getMailToId() {
		return mailToId;
	}

	public void setMailToId(long mailToId) {
		this.mailToId = mailToId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}