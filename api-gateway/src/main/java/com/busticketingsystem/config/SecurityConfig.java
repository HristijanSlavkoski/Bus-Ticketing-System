package com.busticketingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig
{

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity)
	{
		serverHttpSecurity
				.csrf(csrf -> csrf.disable())
				// If we are doing UI in future
				// we need to permitAll() the static resources
				// with .pathMatchers(/<NAME_OF_PATH>/**).permitAll
				.authorizeExchange(exchange ->
						exchange.anyExchange().authenticated())
				// If we want to upgrade to spring 3.1.0, we need to change jwt, since jwt is deprecated
//				.oauth2ResourceServer((oauth2ResourceServer) ->
//						oauth2ResourceServer
//								.jwt(jwt -> jwt
//										.decode(jwtDecoder())
//								)
//				);
				.oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
		return serverHttpSecurity.build();
	}
}
