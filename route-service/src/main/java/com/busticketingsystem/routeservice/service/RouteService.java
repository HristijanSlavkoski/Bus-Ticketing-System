package com.busticketingsystem.routeservice.service;

import com.busticketingsystem.routeservice.dto.RouteRequest;
import com.busticketingsystem.routeservice.dto.RouteResponse;

import java.util.List;

public interface RouteService
{
	void createRoute(RouteRequest routeRequest);

	List<RouteResponse> getAllRoutes();

	RouteResponse getRoute(Long id);

	void updateRoute(Long id, RouteRequest routeRequest);

	void deleteRoute(Long id);

	List<RouteResponse> getRoutesByStartPoint(String startPoint);

	List<RouteResponse> getRoutesByEndPoint(String endPoint);
}
