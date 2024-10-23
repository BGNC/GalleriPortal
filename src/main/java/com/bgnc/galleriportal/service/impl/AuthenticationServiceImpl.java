package com.bgnc.galleriportal.service.impl;


import com.bgnc.galleriportal.dto.AuthRequest;
import com.bgnc.galleriportal.dto.AuthResponse;
import com.bgnc.galleriportal.dto.DtoUser;
import com.bgnc.galleriportal.dto.RefreshTokenRequest;
import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import com.bgnc.galleriportal.jwt.JWTService;
import com.bgnc.galleriportal.model.RefreshToken;
import com.bgnc.galleriportal.model.User;
import com.bgnc.galleriportal.repository.RefreshTokenRepository;
import com.bgnc.galleriportal.repository.UserRepository;
import com.bgnc.galleriportal.service.IAuthenticationService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationProvider authenticationProvider;

    private final JWTService jwtService;

    private final RefreshTokenRepository refreshTokenRepository;


    private User createUser(AuthRequest authRequest) {

        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setCreateTime(new Date());
        return user;


    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setCreateTime(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        Optional<User> optUser = userRepository.findByUsername(authRequest.getUsername());
        User user = createUser(authRequest);
        if(user.getId()==null){
            UUID uuid = UUID.randomUUID();

            if(optUser.isPresent()){
                throw new BaseException(new ErrorMessage(MessageType.USERNAME_ALREADY_EXISTS,uuid.toString()));
            }
        }

        User savedUser = userRepository.save(createUser(authRequest));
        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationProvider.authenticate(authentication);

            Optional<User> optUser = userRepository.findByUsername(authRequest.getUsername());
            if(optUser.isEmpty()){
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXISTS,UUID.randomUUID().toString()));
            }

            String accessToken = jwtService.generateToken(optUser.get());
            RefreshToken refreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));

            return new AuthResponse(accessToken,refreshToken.getRefreshToken());



        } catch (RuntimeException e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_IS_INVALID, e.getMessage()));
        }


    }

    private boolean refreshTokenIsExpired(Date expireDate){
        return new Date().before(expireDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenRequest);
        if(optRefreshToken.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_NOT_FOUND, refreshTokenRequest.getRefreshToken()));
        }
        if(!refreshTokenIsExpired(optRefreshToken.get().getExpireDate())){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_ALREADY_EXPIRED, refreshTokenRequest.getRefreshToken()));
        }
        User user = optRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenRepository.save(createRefreshToken(user));
        return new AuthResponse(accessToken,refreshToken.getRefreshToken());
    }
}
