package com.fu.demo.mbg.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class User implements Serializable {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "name")
	private String name;

	@ApiModelProperty(value = "accountId")
	private Long accountId;

	private static final long serialVersionUID = 1L;

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

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}
