package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.IAccountController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.AccountRequest;
import com.bgnc.galleriportal.dto.AccountResponse;
import com.bgnc.galleriportal.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/account")
@RequiredArgsConstructor
public class RestAccountControllerImpl extends RestBaseController implements IAccountController {


    private final IAccountService accountService;

    @PostMapping("/saveAccount")
    @Override
    public RootEntity<AccountResponse> saveAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return ok(accountService.saveAccount(accountRequest));
    }
}
