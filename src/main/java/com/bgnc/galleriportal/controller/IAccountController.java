package com.bgnc.galleriportal.controller;

import com.bgnc.galleriportal.dto.AccountRequest;
import com.bgnc.galleriportal.dto.AccountResponse;

public interface IAccountController {

    public RootEntity<AccountResponse> saveAccount(AccountRequest accountRequest);
}
