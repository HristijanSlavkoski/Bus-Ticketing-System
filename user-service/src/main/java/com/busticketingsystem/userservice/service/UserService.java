package com.busticketingsystem.userservice.service;


import com.busticketingsystem.userservice.dto.SignUpRequest;
import com.busticketingsystem.userservice.dto.UserResponse;

public interface UserService {
    public String signUpUser(SignUpRequest signUpRequest);

	UserResponse getUserById(Long id);
}
