package com.busticketingsystem.routeservice.service;

import com.busticketingsystem.routeservice.dto.RouteRequest;
import com.busticketingsystem.routeservice.dto.RouteResponse;
import com.busticketingsystem.routeservice.model.Route;
import com.busticketingsystem.routeservice.repository.RouteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService
{
	private final RouteRepository routeRepository;
	private Set<String> cities;

	@PostConstruct
	public void loadCities() throws IOException
	{
		ClassPathResource resource = new ClassPathResource("cities.json");
		try (InputStream input = resource.getInputStream();
		     BufferedReader reader = new BufferedReader(new InputStreamReader(input)))
		{
			cities = reader.lines().collect(Collectors.toSet());
		}
	}

	public boolean isValidCity(String city)
	{
		return cities.contains(city);
	}

	@Override
	public void createRoute(RouteRequest routeRequest)
	{
		if (!isValidCity(routeRequest.getStartPoint()))
		{
			throw new IllegalArgumentException("Invalid start point: " + routeRequest.getStartPoint());
		}
		if (!isValidCity(routeRequest.getEndPoint()))
		{
			throw new IllegalArgumentException("Invalid end point: " + routeRequest.getEndPoint());
		}
		for (String stop : routeRequest.getStops())
		{
			if (!isValidCity(stop))
			{
				throw new IllegalArgumentException("Invalid stop: " + stop);
			}
		}

		Route route = Route.builder()
				.startPoint(routeRequest.getStartPoint())
				.endPoint(routeRequest.getEndPoint())
				.stops(routeRequest.getStops())
				.schedule(routeRequest.getSchedule())
				.build();

		routeRepository.save(route);
	}

	@Override
	public List<RouteResponse> getAllRoutes()
	{
		List<Route> routes = routeRepository.findAll();

		return routes.stream().map(this::mapToRouteResponse).toList();
	}

	@Override
	public RouteResponse getRoute(Long id)
	{
		Route route = routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Route not found"));
		return mapToRouteResponse(route);
	}

	@Override
	public void updateRoute(Long id, RouteRequest routeRequest)
	{
		Route route = routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Route not found"));
		route.setStartPoint(routeRequest.getStartPoint());
		route.setEndPoint(routeRequest.getEndPoint());
		route.setStops(routeRequest.getStops());
		route.setSchedule(routeRequest.getSchedule());
		routeRepository.save(route);
	}

	@Override
	public void deleteRoute(Long id)
	{
		routeRepository.deleteById(id);
	}

	@Override
	public List<RouteResponse> getRoutesByStartPoint(String startPoint)
	{
		List<Route> routes = routeRepository.findByStartPoint(startPoint);
		return routes.stream().map(this::mapToRouteResponse).toList();
	}

	@Override
	public List<RouteResponse> getRoutesByEndPoint(String endPoint)
	{
		List<Route> routes = routeRepository.findByEndPoint(endPoint);
		return routes.stream().map(this::mapToRouteResponse).toList();
	}

	private RouteResponse mapToRouteResponse(Route route)
	{
		return RouteResponse.builder()
				.id(route.getId())
				.startPoint(route.getStartPoint())
				.endPoint(route.getEndPoint())
				.stops(route.getStops())
				.schedule(route.getSchedule())
				.build();
	}
}
