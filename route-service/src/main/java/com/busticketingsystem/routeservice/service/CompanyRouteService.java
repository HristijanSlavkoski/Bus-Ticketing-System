package com.busticketingsystem.routeservice.service;

import com.busticketingsystem.routeservice.dto.CompanyRouteRequest;
import com.busticketingsystem.routeservice.dto.CompanyRouteResponse;

import java.util.List;

public interface CompanyRouteService
{
	void createCompanyRoute(CompanyRouteRequest companyRouteRequest);

	List<CompanyRouteResponse> getAllCompanyRoutes();

	CompanyRouteResponse getCompanyRoute(Long id);

	void updateCompanyRoute(Long id, CompanyRouteRequest companyRouteRequest);

	void deleteCompanyRoute(Long id);

	List<CompanyRouteResponse> getCompanyRoutesByCompanyId(Long companyId);

	List<CompanyRouteResponse> getCompanyRoutesByRouteId(Long routeId);

	CompanyRouteResponse getCheapestRoute(Long routeId);
}
