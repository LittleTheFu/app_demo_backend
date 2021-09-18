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
    
    void setUserId(@Param("accountId") long accountId, @Param("userId") long userId);
    
    long getUserIdByEmail(@Param("email") String email);
    
    long getAccountIdByEmail(@Param("email") String email);
    
    boolean isAccountExsit(@Param("email") String email);
    
    int addResetString(@Param("id") long id, @Param("code") String code);
    
    String getResetCode(@Param("id") long id);
    
    int changePassword(@Param("id") long id, @Param("password") String password);
    
    void clearResetCode(@Param("id") long id);
}
