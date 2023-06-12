package com.busticketingsystem.userservice.service;


import com.busticketingsystem.userservice.dto.SignUpRequest;

public interface UserService {
    public String signUpUser(SignUpRequest signUpRequest);
}
