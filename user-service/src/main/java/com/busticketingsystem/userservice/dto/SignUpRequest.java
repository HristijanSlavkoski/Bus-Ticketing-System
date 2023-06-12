package com.busticketingsystem.userservice.dto;

import com.busticketingsystem.userservice.model.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest
{
	private String name;

	private String email;

	private String phoneNumber;

	private String password;

	private Boolean isNotificationEnabled;

	private NotificationType notificationType;
}