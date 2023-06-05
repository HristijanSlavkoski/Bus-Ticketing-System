package com.busticketingsystem.routeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRouteResponse
{
	private Long id;

	private Long companyId;

	private Long routeId;

	private Double price;
}
