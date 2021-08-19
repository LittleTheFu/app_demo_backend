package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {
	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "name")
	private String name;
	
	@ApiModelProperty(value = "followed")
	private boolean followed;
	
	@ApiModelProperty(value = "icon")
	private String icon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getFollowed() {
		return followed;
	}

	public void setFollowed(boolean followed) {
		this.followed = followed;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
