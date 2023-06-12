package com.busticketingsystem.userservice.dto;

import com.busticketingsystem.userservice.model.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse
{
	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	private Boolean isNotificationEnabled;
	private NotificationType notificationType;
}