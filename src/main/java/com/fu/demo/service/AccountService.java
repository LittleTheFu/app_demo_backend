package com.fu.demo.service;

import java.util.List;

import com.fu.demo.mbg.dto.AccountInfoDto;
import com.fu.demo.mbg.dto.AccountSecurityDto;
import com.fu.demo.mbg.model.Account;

public interface AccountService {
	List<AccountInfoDto> listAllAccount();
	
	void insert(AccountSecurityDto accountDto);
	
	Account getAccountByEmail(String email);
	
	Account getAccountById(long id);

	String login(String email, String password);

	long getUserIdByEmail(String email);
	
	String getResetLink(String email);
}
