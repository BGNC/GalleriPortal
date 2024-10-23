package com.bgnc.galleriportal.controller;

import com.bgnc.galleriportal.dto.AuthRequest;
import com.bgnc.galleriportal.dto.AuthResponse;
import com.bgnc.galleriportal.dto.DtoUser;
import com.bgnc.galleriportal.dto.RefreshTokenRequest;


public interface IRestAuthenticationController {
     RootEntity<DtoUser> register(AuthRequest authRequest);
     RootEntity<AuthResponse> authenticate(AuthRequest authRequest);
     RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
