package com.busticketingsystem.routeservice.controller;

import com.busticketingsystem.routeservice.dto.RouteRequest;
import com.busticketingsystem.routeservice.dto.RouteResponse;
import com.busticketingsystem.routeservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController
{
	private final RouteService routeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createRoute(@RequestBody RouteRequest routeRequest)
	{
		routeService.createRoute(routeRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<RouteResponse> getAllProducts()
	{
		return routeService.getAllRoutes();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RouteResponse getRoute(@PathVariable Long id)
	{
		return routeService.getRoute(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateRoute(@PathVariable Long id, @RequestBody RouteRequest routeRequest)
	{
		routeService.updateRoute(id, routeRequest);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteRoute(@PathVariable Long id)
	{
		routeService.deleteRoute(id);
	}

	@GetMapping("/start/{startPoint}")
	@ResponseStatus(HttpStatus.OK)
	public List<RouteResponse> getRoutesByStartPoint(@PathVariable String startPoint)
	{
		return routeService.getRoutesByStartPoint(startPoint);
	}

	@GetMapping("/end/{endPoint}")
	@ResponseStatus(HttpStatus.OK)
	public List<RouteResponse> getRoutesByEndPoint(@PathVariable String endPoint)
	{
		return routeService.getRoutesByEndPoint(endPoint);
	}
}
