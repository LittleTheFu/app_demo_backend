package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class UpdateUserNameDto {
	@ApiModelProperty(value = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
