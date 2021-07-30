package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fu.demo.mbg.model.Account;

@Mapper
public interface AccountMapper {
    List<Account> queryAllAccount();

    void insert(Account account);
    
    Account queryAccountByEmail(String email);
}
