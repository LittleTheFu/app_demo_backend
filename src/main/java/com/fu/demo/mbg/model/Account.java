package com.fu.demo.mbg.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

public class Account implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "email")
	private String email;

	@ApiModelProperty(value = "password")
	private String password;
	
	@ApiModelProperty(value = "user")
	private User user;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}
