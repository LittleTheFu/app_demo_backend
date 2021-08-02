package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class AccountSecurityDto {
	@ApiModelProperty(value = "email")
	private String email;

	@ApiModelProperty(value = "password")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}