package com.qp.grocery.controller;

import com.qp.grocery.dto.JwtRequest;
import com.qp.grocery.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService service;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
         return service.authenticateUser(jwtRequest.getUserName(), jwtRequest.getPassword());
    }
}
