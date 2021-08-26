package com.fu.demo.mbg.model;

import io.swagger.annotations.ApiModelProperty;

public class Mail {
	@ApiModelProperty(value = "id")
	private long id;

	@ApiModelProperty(value = "mailFromId")
	private long mailFromId;

	@ApiModelProperty(value = "mailToId")
	private long mailToId;
	
	@ApiModelProperty(value = "content")
	private String content;
	
	private static final long serialVersionUID = 1L;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMailFromId() {
		return mailFromId;
	}

	public void setMailFromId(long mailFromId) {
		this.mailFromId = mailFromId;
	}

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
