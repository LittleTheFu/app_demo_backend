package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.AccountSecurityDto;
import com.fu.demo.mbg.model.Account;

public interface AccountService {
	List<Account> listAllAccount();
	
	void insert(AccountSecurityDto accountDto);
	
	Account getAccountByEmail(String email);
	
	Account getAccountById(long id);

	String login(String email, String password);
}
