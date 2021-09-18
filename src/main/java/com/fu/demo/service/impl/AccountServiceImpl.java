package com.fu.demo.service.impl;

import java.util.List;

import com.fu.demo.common.api.AppException;
import com.fu.demo.common.api.ConstMessage;
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
import com.fu.demo.mbg.mapper.UserMapper;
import com.fu.demo.mbg.model.Account;
import com.fu.demo.mbg.model.User;
import com.fu.demo.service.AccountService;

import cn.hutool.core.util.RandomUtil;

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
	
	@Autowired
	private UserMapper userMapper;

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
		
		User user = new User();
		user.setName("new_user");
		userMapper.insert(user);
		
		accountMapper.setUserId(account.getId(), user.getId());
		userMapper.setAccountId(user.getId(), account.getId());

//		long newId = account.getId();
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
			long userId = accountMapper.getUserIdByEmail(email);
			token = jwtTokenUtil.generateToken(userDetails, userId);
//			token = jwtTokenUtil.generateToken(userDetails);
		} catch (AuthenticationException e) {
//			LOGGER.warn("登录异常:{}", e.getMessage());
		}
		return token;
	}

	@Override
	public Account getAccountById(long id) {
		return accountMapper.queryAccountById(id);
	}
	
	@Override
	public long getUserIdByEmail(String email) {
		return accountMapper.getUserIdByEmail(email);
	}

	@Override
	public String getResetLink(String email) {
		boolean isExsit = accountMapper.isAccountExsit(email);
		if(false == isExsit) {
			throw new AppException(ConstMessage.NO_SUCH_ACCOUNT);
		}
		
		long id = accountMapper.getAccountIdByEmail(email);
		
		String clientAddress = "http://localhost:3000/reset/";
		String code = RandomUtil.randomString(32);
		String link = clientAddress + code;
		
		accountMapper.addResetString(id, code);
//		int count = accountMapper.addResetString(id, link);
//		if(count != 1) {
//			throw new AppException(ConstMessage.ACTION_NOT_DONE);
//		}
		
		return link;
	}

	@Override
	public void resetPassword(String email, String password, String code) {
		boolean isExsit = accountMapper.isAccountExsit(email);
		if(false == isExsit) {
			throw new AppException(ConstMessage.NO_SUCH_ACCOUNT);
		}
		
		long id = accountMapper.getAccountIdByEmail(email);

		String savedCode = accountMapper.getResetCode(id);
		
		if(!code.equals(savedCode)) {
			throw new AppException(ConstMessage.WRONG_RESET_CODE);
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		
		int count = accountMapper.changePassword(id, encodedPassword);
		if(count != 1) {
			throw new AppException(ConstMessage.ACTION_NOT_DONE);
		}
		
		accountMapper.clearResetCode(id);
	}

}
