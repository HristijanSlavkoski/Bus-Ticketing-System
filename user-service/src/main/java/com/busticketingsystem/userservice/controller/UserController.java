package com.busticketingsystem.userservice.controller;


import com.busticketingsystem.userservice.dto.LoginRequest;
import com.busticketingsystem.userservice.dto.SignUpRequest;
import com.busticketingsystem.userservice.dto.UserResponse;
import com.busticketingsystem.userservice.service.KeycloakService;
import com.busticketingsystem.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController
{
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private final UserService userService;
	private final KeycloakService keycloakService;

	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest)
	{

		LOGGER.info("UserController | signUpUser is started");
		return ResponseEntity.ok(userService.signUpUser(signUpRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest request)
	{

		LOGGER.info("UserController | login is started");

		AccessTokenResponse accessTokenResponse = keycloakService.loginWithKeycloak(request);
		if (accessTokenResponse == null)
		{
			LOGGER.info("UserController | login | Http Status Bad Request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accessTokenResponse);
		}

		LOGGER.info("UserController | login | Http Status Ok");

		return ResponseEntity.ok(accessTokenResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
	{
		UserResponse user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
}
