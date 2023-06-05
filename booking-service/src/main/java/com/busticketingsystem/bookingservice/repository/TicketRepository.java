package com.busticketingsystem.bookingservice.repository;

import com.busticketingsystem.bookingservice.model.Ticket;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import com.busticketingsystem.bookingservice.model.TicketValidity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>
{
	List<Ticket> findByValidity(TicketValidity validity);

	List<Ticket> findByType(TicketTypeEnum type);
}
