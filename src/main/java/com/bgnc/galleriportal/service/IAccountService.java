package com.bgnc.galleriportal.service;


import com.bgnc.galleriportal.dto.AccountRequest;
import com.bgnc.galleriportal.dto.AccountResponse;

public interface IAccountService {

  public AccountResponse saveAccount(AccountRequest accountRequest);
}
