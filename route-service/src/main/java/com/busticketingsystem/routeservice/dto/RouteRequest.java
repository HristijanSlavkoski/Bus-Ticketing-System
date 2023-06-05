package com.busticketingsystem.routeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest
{
	private String startPoint;

	private String endPoint;

	private List<String> stops;

	private List<LocalTime> schedule;
}
