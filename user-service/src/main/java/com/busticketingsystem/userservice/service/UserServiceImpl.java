package com.busticketingsystem.userservice.service;

import com.busticketingsystem.userservice.dto.KeycloakUser;
import com.busticketingsystem.userservice.dto.SignUpRequest;
import com.busticketingsystem.userservice.dto.UserResponse;
import com.busticketingsystem.userservice.model.User;
import com.busticketingsystem.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService
{

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;
	private final KeycloakService keycloakService;

	@Override
	public String signUpUser(SignUpRequest signUpRequest)
	{

		LOGGER.info("UserServiceImpl | signUpUser is started");

		KeycloakUser keycloakUser = new KeycloakUser();
		keycloakUser.setFirstName(signUpRequest.getName());
		keycloakUser.setLastName(null);
		keycloakUser.setEmail(signUpRequest.getEmail());
		keycloakUser.setPassword(signUpRequest.getPassword());
		keycloakUser.setRole(null);
		keycloakUser.setUsername(signUpRequest.getName());

		int status = keycloakService.createUserWithKeycloak(keycloakUser);

		if (status == 201)
		{

			LOGGER.info("UserServiceImpl | signUpUser | status : " + status);

			User signUpUser = mapToCompanyRouteResponse(signUpRequest);
			userRepository.save(signUpUser);

			return "Sign Up completed";
		}

		return "Not Register";
	}

	@Override
	public UserResponse getUserById(Long id)
	{
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return mapToUserResponse(user);
	}

	private User mapToCompanyRouteResponse(SignUpRequest signUpRequest)
	{
		return User.builder()
				.email(signUpRequest.getEmail())
				.name(signUpRequest.getName())
				.notificationType(signUpRequest.getNotificationType())
				.password(signUpRequest.getPassword())
				.isNotificationEnabled(signUpRequest.getIsNotificationEnabled()).build();
	}

	private UserResponse mapToUserResponse(User user)
	{
		return UserResponse.builder()
				.phoneNumber(user.getPhoneNumber())
				.id(user.getId())
				.notificationType(user.getNotificationType())
				.email(user.getEmail())
				.isNotificationEnabled(user.getIsNotificationEnabled())
				.name(user.getName())
				.build();
	}
}
