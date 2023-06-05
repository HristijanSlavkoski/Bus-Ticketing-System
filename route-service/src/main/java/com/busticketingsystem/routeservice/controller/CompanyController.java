package com.busticketingsystem.routeservice.controller;

import com.busticketingsystem.routeservice.dto.CompanyRequest;
import com.busticketingsystem.routeservice.dto.CompanyResponse;
import com.busticketingsystem.routeservice.service.CompanyService;
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
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController
{
	private final CompanyService companyService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCompany(@RequestBody CompanyRequest companyRequest)
	{
		companyService.createCompany(companyRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CompanyResponse> getAllCompanies()
	{
		return companyService.getAllCompanies();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CompanyResponse getCompany(@PathVariable Long id)
	{
		return companyService.getCompany(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyRequest)
	{
		companyService.updateCompany(id, companyRequest);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCompany(@PathVariable Long id)
	{
		companyService.deleteCompany(id);
	}
}
