package com.busticketingsystem.routeservice.service;

import com.busticketingsystem.routeservice.dto.CompanyRequest;
import com.busticketingsystem.routeservice.dto.CompanyResponse;
import com.busticketingsystem.routeservice.model.Company;
import com.busticketingsystem.routeservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
	private final CompanyRepository companyRepository;

	@Override
	public void createCompany(CompanyRequest companyRequest)
	{
		Company company = Company.builder()
				.name(companyRequest.getName())
				.build();

		companyRepository.save(company);
	}

	@Override
	public List<CompanyResponse> getAllCompanies()
	{
		List<Company> companies = companyRepository.findAll();

		return companies.stream().map(this::mapToCompanyResponse).toList();
	}

	@Override
	public CompanyResponse getCompany(Long id)
	{
		Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
		return mapToCompanyResponse(company);
	}

	@Override
	public void updateCompany(Long id, CompanyRequest companyRequest)
	{
		Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
		company.setName(companyRequest.getName());
		companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id)
	{
		companyRepository.deleteById(id);
	}

	private CompanyResponse mapToCompanyResponse(Company company)
	{
		return CompanyResponse.builder()
				.id(company.getId())
				.name(company.getName())
				.build();
	}
}