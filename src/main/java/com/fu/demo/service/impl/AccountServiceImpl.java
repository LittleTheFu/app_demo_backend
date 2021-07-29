package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.mapper.AccountMapper;
import com.fu.demo.mbg.model.Account;
import com.fu.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public List<Account> listAllAccount() {
		return accountMapper.queryAllAccount();
	}

}
