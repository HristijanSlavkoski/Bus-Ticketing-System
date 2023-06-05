package com.busticketingsystem.routeservice.service;

import com.busticketingsystem.routeservice.dto.CompanyRouteRequest;
import com.busticketingsystem.routeservice.dto.CompanyRouteResponse;
import com.busticketingsystem.routeservice.model.CompanyRoute;
import com.busticketingsystem.routeservice.repository.CompanyRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyRouteServiceImpl implements CompanyRouteService
{
	private final CompanyRouteRepository companyRouteRepository;

	@Override
	public void createCompanyRoute(CompanyRouteRequest companyRouteRequest)
	{
		CompanyRoute companyRoute = CompanyRoute.builder()
				.companyId(companyRouteRequest.getCompanyId())
				.routeId(companyRouteRequest.getRouteId())
				.price(companyRouteRequest.getPrice())
				.build();

		companyRouteRepository.save(companyRoute);
	}

	@Override
	public List<CompanyRouteResponse> getAllCompanyRoutes()
	{
		List<CompanyRoute> companyRoutes = companyRouteRepository.findAll();

		return companyRoutes.stream().map(this::mapToCompanyRouteResponse).toList();
	}

	@Override
	public CompanyRouteResponse getCompanyRoute(Long id)
	{
		CompanyRoute companyRoute = companyRouteRepository.findById(id).orElseThrow(() -> new RuntimeException("CompanyRoute not found"));
		return mapToCompanyRouteResponse(companyRoute);
	}

	@Override
	public void updateCompanyRoute(Long id, CompanyRouteRequest companyRouteRequest)
	{
		CompanyRoute companyRoute = companyRouteRepository.findById(id).orElseThrow(() -> new RuntimeException("CompanyRoute not found"));
		companyRoute.setCompanyId(companyRouteRequest.getCompanyId());
		companyRoute.setRouteId(companyRouteRequest.getRouteId());
		companyRoute.setPrice(companyRouteRequest.getPrice());
		companyRouteRepository.save(companyRoute);
	}

	@Override
	public void deleteCompanyRoute(Long id)
	{
		companyRouteRepository.deleteById(id);
	}

	@Override
	public List<CompanyRouteResponse> getCompanyRoutesByCompanyId(Long companyId)
	{
		List<CompanyRoute> companyRoutes = companyRouteRepository.findByCompanyId(companyId);
		return companyRoutes.stream().map(this::mapToCompanyRouteResponse).toList();
	}

	@Override
	public List<CompanyRouteResponse> getCompanyRoutesByRouteId(Long routeId)
	{
		List<CompanyRoute> companyRoutes = companyRouteRepository.findByRouteId(routeId);
		return companyRoutes.stream().map(this::mapToCompanyRouteResponse).toList();
	}

	@Override
	public CompanyRouteResponse getCheapestRoute(Long routeId)
	{
		List<CompanyRoute> companyRoutes = companyRouteRepository.findByRouteId(routeId);
		CompanyRoute cheapestCompanyRoute = companyRoutes.stream()
				.min(Comparator.comparing(CompanyRoute::getPrice))
				.orElseThrow(() -> new RuntimeException("No routes found for the given id"));
		return mapToCompanyRouteResponse(cheapestCompanyRoute);
	}

	private CompanyRouteResponse mapToCompanyRouteResponse(CompanyRoute companyRoute)
	{
		return CompanyRouteResponse.builder()
				.id(companyRoute.getId())
				.companyId(companyRoute.getCompanyId())
				.routeId(companyRoute.getRouteId())
				.price(companyRoute.getPrice())
				.build();
	}
}