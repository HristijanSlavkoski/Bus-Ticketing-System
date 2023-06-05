package com.busticketingsystem.routeservice.repository;

import com.busticketingsystem.routeservice.model.CompanyRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRouteRepository extends JpaRepository<CompanyRoute, Long>
{
	List<CompanyRoute> findByCompanyId(Long companyId);

	List<CompanyRoute> findByRouteId(Long routeId);
}
