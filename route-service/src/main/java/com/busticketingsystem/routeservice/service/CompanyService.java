package com.busticketingsystem.routeservice.service;

import com.busticketingsystem.routeservice.dto.CompanyRequest;
import com.busticketingsystem.routeservice.dto.CompanyResponse;

import java.util.List;

public interface CompanyService
{
	void createCompany(CompanyRequest companyRequest);

	List<CompanyResponse> getAllCompanies();

	CompanyResponse getCompany(Long id);

	void updateCompany(Long id, CompanyRequest companyRequest);

	void deleteCompany(Long id);
}