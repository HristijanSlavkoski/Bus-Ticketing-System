package com.busticketingsystem.bookingservice.repository;

import com.busticketingsystem.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>
{
	List<Booking> findByUserId(Long userId);

	List<Booking> findByCompanyRouteId(Long routeId);
}
