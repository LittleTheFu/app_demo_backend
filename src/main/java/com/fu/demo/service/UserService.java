package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.model.User;

public interface UserService {
	List<User> listAllUser();
	
	User getUserById(int id);
}
