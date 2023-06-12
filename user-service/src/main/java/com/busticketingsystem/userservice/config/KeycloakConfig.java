package com.busticketingsystem.userservice.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig
{
	@Bean
	public KeycloakConfigResolver keycloakConfigResolver()
	{
		return new KeycloakSpringBootConfigResolver();
	}

	@Bean
	public Keycloak keycloak()
	{
		return Keycloak.getInstance("http://localhost:8181",
				"bus-ticketing-system-realm",
				"administrator",
				"administrator",
				"spring-cloud-client",
				"xz5a1cdzJ7AsEuJJLmg0f4mgG7YuoGWX");
	}
}
