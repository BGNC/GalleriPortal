package com.bgnc.galleriportal.config;

import java.util.Optional;

import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import com.bgnc.galleriportal.model.User;
import com.bgnc.galleriportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class AppConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> optional = userRepository.findByUsername(username);
                if(optional.isEmpty()) {
                    throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND_EXCEPTION, username));
                }
                return optional.get();
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider  = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}