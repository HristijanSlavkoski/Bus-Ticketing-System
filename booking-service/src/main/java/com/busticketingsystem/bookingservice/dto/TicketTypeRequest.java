package com.busticketingsystem.bookingservice.dto;

import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeRequest
{
	private TicketTypeEnum type;
	private Double priceModifier;
}