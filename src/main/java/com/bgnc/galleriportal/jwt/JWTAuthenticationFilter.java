package com.bgnc.galleriportal.jwt;

import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null) {
            filterChain.doFilter(request,response);
            return;
        }
        String username;
        String token = header.substring(7);

        try{
            username=jwtService.getUsernameByToken(token);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(userDetails!=null && jwtService.tokenIsValid(token)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                    authentication.setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ExpiredJwtException e) {
            throw new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPIRED, e.getMessage()));
        }
        catch (RuntimeException e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
        filterChain.doFilter(request,response);

    }
}
