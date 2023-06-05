package com.busticketingsystem.bookingservice.service;

import com.busticketingsystem.bookingservice.dto.TicketTypeRequest;
import com.busticketingsystem.bookingservice.dto.TicketTypeResponse;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;

import java.util.List;

public interface TicketTypeService
{
	void createTicketType(TicketTypeRequest ticketTypeRequest);

	List<TicketTypeResponse> getAllTicketTypes();

	TicketTypeResponse getTicketType(Long id);

	void deleteTicketType(Long id);

	void updateTicketType(Long id, TicketTypeRequest ticketTypeRequest);

	List<TicketTypeResponse> getTicketTypesByType(TicketTypeEnum type);
}
