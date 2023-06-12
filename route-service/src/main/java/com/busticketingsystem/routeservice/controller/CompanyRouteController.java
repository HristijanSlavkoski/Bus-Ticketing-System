package com.busticketingsystem.routeservice.controller;

import com.busticketingsystem.routeservice.dto.CompanyRouteRequest;
import com.busticketingsystem.routeservice.dto.CompanyRouteResponse;
import com.busticketingsystem.routeservice.service.CompanyRouteService;
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
@RequestMapping("/api/company-route")
@RequiredArgsConstructor
public class CompanyRouteController
{
	private final CompanyRouteService companyRouteService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCompanyRoute(@RequestBody CompanyRouteRequest companyRouteRequest)
	{
		companyRouteService.createCompanyRoute(companyRouteRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CompanyRouteResponse> getAllCompanyRoutes()
	{
		return companyRouteService.getAllCompanyRoutes();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CompanyRouteResponse getCompanyRoute(@PathVariable Long id)
	{
		return companyRouteService.getCompanyRoute(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateCompanyRoute(@PathVariable Long id, @RequestBody CompanyRouteRequest companyRouteRequest)
	{
		companyRouteService.updateCompanyRoute(id, companyRouteRequest);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCompanyRoute(@PathVariable Long id)
	{
		companyRouteService.deleteCompanyRoute(id);
	}

	@GetMapping("/company/{companyId}")
	@ResponseStatus(HttpStatus.OK)
	public List<CompanyRouteResponse> getCompanyRoutesByCompanyId(@PathVariable Long companyId)
	{
		return companyRouteService.getCompanyRoutesByCompanyId(companyId);
	}

	@GetMapping("/route/{routeId}")
	@ResponseStatus(HttpStatus.OK)
	public List<CompanyRouteResponse> getCompanyRoutesByRouteId(@PathVariable Long routeId)
	{
		return companyRouteService.getCompanyRoutesByRouteId(routeId);
	}

	@GetMapping("/cheapest/{routeId}")
	@ResponseStatus(HttpStatus.OK)
	public CompanyRouteResponse getCheapestRoute(@PathVariable Long routeId) {
		return companyRouteService.getCheapestRoute(routeId);
	}
}
