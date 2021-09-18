package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class ResetPasswordDto {
	@ApiModelProperty(value = "email")
	private String email;

	@ApiModelProperty(value = "password")
	private String password;
	
	@ApiModelProperty(value = "code")
	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
