package com.busticketingsystem.routeservice.repository;

import com.busticketingsystem.routeservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>
{
}
