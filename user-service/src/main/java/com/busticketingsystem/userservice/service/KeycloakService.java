package com.busticketingsystem.userservice.service;

import com.busticketingsystem.userservice.dto.KeycloakUser;
import com.busticketingsystem.userservice.dto.LoginRequest;
import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakService
{
	public AccessTokenResponse loginWithKeycloak(LoginRequest request);

	public int createUserWithKeycloak(KeycloakUser keycloakUser);
}
