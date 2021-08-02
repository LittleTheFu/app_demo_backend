package com.fu.demo.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fu.demo.mbg.dto.AccountInfoDto;
import com.fu.demo.mbg.model.Account;

@Mapper
public interface AccountMapper {
    List<AccountInfoDto> queryAllAccount();

    void insert(Account account);
    
    List<Account> queryAccountByEmail(@Param("email") String email);
    
    Account queryAccountById(@Param("id") long id); 
}
