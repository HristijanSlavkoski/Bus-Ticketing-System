package com.busticketingsystem.bookingservice.service;

import com.busticketingsystem.bookingservice.dto.TicketTypeRequest;
import com.busticketingsystem.bookingservice.dto.TicketTypeResponse;
import com.busticketingsystem.bookingservice.model.TicketType;
import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import com.busticketingsystem.bookingservice.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService
{
	private final TicketTypeRepository ticketTypeRepository;

	@Override
	public void createTicketType(TicketTypeRequest ticketTypeRequest)
	{
		TicketType ticketType = mapToTicketType(ticketTypeRequest);
		ticketTypeRepository.save(ticketType);
	}

	@Override
	public List<TicketTypeResponse> getAllTicketTypes()
	{
		List<TicketType> ticketTypes = ticketTypeRepository.findAll();
		return ticketTypes.stream().map(this::mapToTicketTypeResponse).toList();
	}

	@Override
	public TicketTypeResponse getTicketType(Long id)
	{
		TicketType ticketType = ticketTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("TicketType not found"));
		return mapToTicketTypeResponse(ticketType);
	}

	@Override
	public void deleteTicketType(Long id)
	{
		ticketTypeRepository.deleteById(id);
	}

	@Override
	public void updateTicketType(Long id, TicketTypeRequest ticketTypeRequest)
	{
		TicketType ticketType = ticketTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("TicketType not found"));
		ticketType.setType(ticketTypeRequest.getType());
		ticketType.setPriceModifier(ticketTypeRequest.getPriceModifier());
		ticketTypeRepository.save(ticketType);
	}

	@Override
	public List<TicketTypeResponse> getTicketTypesByType(TicketTypeEnum type)
	{
		List<TicketType> ticketTypes = ticketTypeRepository.findByType(type);
		return ticketTypes.stream().map(this::mapToTicketTypeResponse).toList();
	}

	private TicketType mapToTicketType(TicketTypeRequest ticketTypeRequest)
	{
		return TicketType.builder()
				.type(ticketTypeRequest.getType())
				.priceModifier(ticketTypeRequest.getPriceModifier())
				.build();
	}

	private TicketTypeResponse mapToTicketTypeResponse(TicketType ticketType)
	{
		return TicketTypeResponse.builder()
				.id(ticketType.getId())
				.type(ticketType.getType())
				.priceModifier(ticketType.getPriceModifier())
				.build();
	}
}
