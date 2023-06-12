package com.busticketingsystem.userservice.service;

import com.busticketingsystem.userservice.dto.KeycloakUser;
import com.busticketingsystem.userservice.dto.LoginRequest;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class KeycloakServiceImpl implements KeycloakService
{

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private final Keycloak keycloak;

	public KeycloakServiceImpl(Keycloak keycloak)
	{
		this.keycloak = keycloak;
	}

	@Override
	public AccessTokenResponse loginWithKeycloak(LoginRequest request)
	{
		Keycloak loginKeycloak = buildKeycloak(request.getUsername(), request.getPassword());

		AccessTokenResponse accessTokenResponse = null;

		try
		{
			accessTokenResponse = loginKeycloak.tokenManager().getAccessToken();
			return accessTokenResponse;
		} catch (Exception e)
		{
			return null;
		}
	}

	private Keycloak buildKeycloak(String username, String password)
	{
		return KeycloakBuilder.builder()
				.realm("bus-ticketing-system-realm")
				.serverUrl("http://localhost:8181")
				.clientId("spring-cloud-client")
				.clientSecret("xz5a1cdzJ7AsEuJJLmg0f4mgG7YuoGWX")
				.username(username)
				.password(password)
				.build();

	}

	@Override
	public int createUserWithKeycloak(KeycloakUser keycloakUser)
	{
		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak is started");

		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setFirstName(keycloakUser.getFirstName());
		userRepresentation.setLastName(keycloakUser.getLastName());
		userRepresentation.setEmail(keycloakUser.getEmail());
		userRepresentation.setUsername(keycloakUser.getUsername());
		HashMap<String, List<String>> clientRoles = new HashMap<>();
		clientRoles.put("spring-cloud-client", Collections.singletonList(keycloakUser.getRole()));
		userRepresentation.setClientRoles(clientRoles);

		userRepresentation.setEnabled(true);

		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | userRepresentation is completed");

		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setType("password");
		credentialRepresentation.setValue(keycloakUser.getPassword());
		credentialRepresentation.setTemporary(false);

		userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | credentialRepresentation is completed");

		UsersResource usersResource = keycloak.realm("bus-ticketing-system-realm").users();

		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | usersResource : " + usersResource.toString());

		// Create user (requires manage-users role)
		Response response = usersResource.create(userRepresentation);

		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | Create User : ");
		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | response STATUS : " + response.getStatus());
		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | response INFO : " + response.getStatusInfo());

		String userId = CreatedResponseUtil.getCreatedId(response);
		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | userId : " + userId);

		RoleRepresentation savedRoleRepresentation = keycloak.realm("bus-ticketing-system-realm").roles()
				.get(keycloakUser.getRole()).toRepresentation();

		keycloak.realm("bus-ticketing-system-realm").users().get(userId).roles().realmLevel()
				.add(Arrays.asList(savedRoleRepresentation));

		LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | Added Role to User");

		return response.getStatus();
	}
}
