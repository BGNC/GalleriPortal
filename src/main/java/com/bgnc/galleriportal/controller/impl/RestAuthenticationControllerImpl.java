package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.IRestAuthenticationController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.AuthRequest;

import com.bgnc.galleriportal.dto.AuthResponse;
import com.bgnc.galleriportal.dto.DtoUser;
import com.bgnc.galleriportal.dto.RefreshTokenRequest;
import com.bgnc.galleriportal.service.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class RestAuthenticationControllerImpl
        extends RestBaseController
        implements IRestAuthenticationController {


    private final IAuthenticationService authService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {

        return ok(authService.register(authRequest));

    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authService.authenticate(authRequest));
    }

    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return ok(authService.refreshToken(refreshTokenRequest));
    }
}

