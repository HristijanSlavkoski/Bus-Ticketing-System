package com.busticketingsystem.routeservice.repository;

import com.busticketingsystem.routeservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long>
{
	List<Route> findByStartPoint(String startPoint);

	List<Route> findByEndPoint(String endPoint);
}
