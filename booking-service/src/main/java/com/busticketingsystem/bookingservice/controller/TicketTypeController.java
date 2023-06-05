package com.busticketingsystem.bookingservice.controller;

import com.busticketingsystem.bookingservice.dto.TicketTypeRequest;
import com.busticketingsystem.bookingservice.dto.TicketTypeResponse;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import com.busticketingsystem.bookingservice.service.TicketTypeService;
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
@RequestMapping("/api/ticket-type")
@RequiredArgsConstructor
public class TicketTypeController
{
	private final TicketTypeService ticketTypeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTicketType(@RequestBody TicketTypeRequest ticketTypeRequest)
	{
		ticketTypeService.createTicketType(ticketTypeRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TicketTypeResponse> getAllTicketTypes()
	{
		return ticketTypeService.getAllTicketTypes();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TicketTypeResponse getTicketType(@PathVariable Long id)
	{
		return ticketTypeService.getTicketType(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTicketType(@PathVariable Long id)
	{
		ticketTypeService.deleteTicketType(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateTicketType(@PathVariable Long id, @RequestBody TicketTypeRequest ticketTypeRequest)
	{
		ticketTypeService.updateTicketType(id, ticketTypeRequest);
	}

	@GetMapping("/type/{type}")
	@ResponseStatus(HttpStatus.OK)
	public List<TicketTypeResponse> getTicketTypesByType(@PathVariable TicketTypeEnum type)
	{
		return ticketTypeService.getTicketTypesByType(type);
	}
}
