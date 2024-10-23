package com.bgnc.galleriportal.service;


import com.bgnc.galleriportal.dto.AuthRequest;
import com.bgnc.galleriportal.dto.AuthResponse;
import com.bgnc.galleriportal.dto.DtoUser;
import com.bgnc.galleriportal.dto.RefreshTokenRequest;


public interface IAuthenticationService {

    DtoUser register(AuthRequest authRequest);
    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
