package com.fu.demo.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.AccountDto;
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

	@Override
	public void insert(AccountDto accountDto) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDto, account);

		accountMapper.insert(account);

		return;
	}
	
	@Override
	public Account getAccountByEmail(String email) {
		return accountMapper.queryAccountByEmail(email);
	}

}
