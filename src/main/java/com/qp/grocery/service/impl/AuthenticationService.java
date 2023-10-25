package com.qp.grocery.service.impl;

import com.qp.grocery.configuration.JwtTokenProvider;
import com.qp.grocery.exception.CustomException;
import com.qp.grocery.model.User;
import com.qp.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String authenticateUser(String userName, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            Optional<User> optionalUser = repository.findById(userName);

            if(optionalUser.isEmpty()) {
                throw new UsernameNotFoundException("User " + userName + " not found");
            }

            return jwtTokenProvider.createToken(userName, optionalUser.get().getRole());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
