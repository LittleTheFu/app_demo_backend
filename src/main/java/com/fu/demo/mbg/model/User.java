package com.fu.demo.mbg.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class User implements Serializable {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "name")
	private String name;

	@ApiModelProperty(value = "account")
	private Account account;

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
