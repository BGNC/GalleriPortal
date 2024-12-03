package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AccountRequest;
import com.bgnc.galleriportal.dto.AccountResponse;
import com.bgnc.galleriportal.model.Account;
import com.bgnc.galleriportal.repository.AccountRepository;
import com.bgnc.galleriportal.repository.GenericRepository;
import com.bgnc.galleriportal.service.IAccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AccountServiceImpl extends GenericServiceImpl<Account,Long> implements IAccountService {

    public AccountServiceImpl(GenericRepository<Account, Long> genericRepository) {
        super(genericRepository);
    }

    private Account createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(accountRequest, account);
        return account;
    }


    @Override
    public AccountResponse saveAccount(AccountRequest accountRequest) {

        Account savedAccount=save(createAccount(accountRequest));
        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(savedAccount, accountResponse);
        return accountResponse;
    }
}
