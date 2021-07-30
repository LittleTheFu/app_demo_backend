package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.AccountDto;
import com.fu.demo.mbg.model.Account;

public interface AccountService {
	List<Account> listAllAccount();
	
	void insert(AccountDto accountDto);
	
	Account getAccountByEmail(String email);

	String login(String email, String password);
}
