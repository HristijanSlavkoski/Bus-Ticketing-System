package com.busticketingsystem.paymentservice.dto;

import com.busticketingsystem.paymentservice.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest
{
	private Long bookingId;
	private Double amount;
	private LocalDate paymentDate;
	private PaymentStatus status;
}
