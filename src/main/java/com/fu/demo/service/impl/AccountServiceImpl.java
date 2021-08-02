package com.fu.demo.service.impl;

import java.util.List;
import com.fu.demo.common.utils.JwtTokenUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.AccountInfoDto;
import com.fu.demo.mbg.dto.AccountSecurityDto;
import com.fu.demo.mbg.mapper.AccountMapper;
import com.fu.demo.mbg.model.Account;
import com.fu.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<AccountInfoDto> listAllAccount() {
		return accountMapper.queryAllAccount();
	}

	@Override
	public void insert(AccountSecurityDto accountDto) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDto, account);

		String encodePassword = passwordEncoder.encode(account.getPassword());
		account.setPassword(encodePassword);

		accountMapper.insert(account);

		return;
	}

	@Override
	public Account getAccountByEmail(String email) {
		List<Account> accounts = accountMapper.queryAccountByEmail(email);
		if (accounts != null && accounts.size() > 0) {
			return accounts.get(0);
		}
		return null;
	}

	@Override
	public String login(String email, String password) {
		String token = null;
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("密码不正确");
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
		} catch (AuthenticationException e) {
//			LOGGER.warn("登录异常:{}", e.getMessage());
		}
		return token;
	}

	@Override
	public Account getAccountById(long id) {
		return accountMapper.queryAccountById(id);
	}

}
