package com.fu.demo.mbg.dto;

import com.fu.demo.mbg.model.Account;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SpringSecurity需要的用户详情 Created by macro on 2018/4/26.
 */
public class AccountDetail implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Account account;

	public AccountDetail(Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_ACCOUNT"));

		return list;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getEmail();
	}
	
	public long getAccountId() {
		return account.getId();
	}
	
	public long getUserId() {
		return account.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
