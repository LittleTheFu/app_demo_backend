package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class EmailDto {
	@ApiModelProperty(value = "email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
