package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class AccountInfoDto {
	@ApiModelProperty(value = "email")
	private String email;

	@ApiModelProperty(value = "email")
	private String name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
