package com.fu.demo.mbg.dto;

import com.fu.demo.mbg.model.Mail;

import io.swagger.annotations.ApiModelProperty;

public class MailResponseDto extends Mail {
	@ApiModelProperty(value = "authorName")
	private String authorName;

	@ApiModelProperty(value = "authorIcon")
	private String authorIcon;

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
}
