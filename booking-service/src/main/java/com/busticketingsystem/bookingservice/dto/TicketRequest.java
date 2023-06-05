package com.busticketingsystem.bookingservice.dto;

import com.busticketingsystem.bookingservice.model.TicketTypeEnum;
import com.busticketingsystem.bookingservice.model.TicketValidity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest
{
	private Long bookingId;
	private LocalDate issueDate;
	private TicketValidity validity;
	private TicketTypeEnum type;
	private Integer usageCount;
}
