package com.busticketingsystem.bookingservice.repository;

import com.busticketingsystem.bookingservice.model.TicketType;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long>
{
	List<TicketType> findByType(TicketTypeEnum type);
}
