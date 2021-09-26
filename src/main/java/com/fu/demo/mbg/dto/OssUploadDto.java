package com.fu.demo.mbg.dto;

import io.swagger.annotations.ApiModelProperty;

public class OssUploadDto {
	@ApiModelProperty(value = "name")
	private String name;
	
	@ApiModelProperty(value = "url")
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
