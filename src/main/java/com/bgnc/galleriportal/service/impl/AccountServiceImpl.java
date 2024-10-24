package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AccountRequest;
import com.bgnc.galleriportal.dto.AccountResponse;
import com.bgnc.galleriportal.model.Account;
import com.bgnc.galleriportal.repository.AccountRepository;
import com.bgnc.galleriportal.service.IAccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private Account createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(accountRequest, account);
        return account;
    }


    @Override
    public AccountResponse saveAccount(AccountRequest accountRequest) {
        Account savedAccount=accountRepository.save( createAccount(accountRequest));
        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(savedAccount, accountResponse);
        return accountResponse;
    }
}
