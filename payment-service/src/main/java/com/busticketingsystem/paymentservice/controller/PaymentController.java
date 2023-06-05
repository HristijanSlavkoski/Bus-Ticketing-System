package com.busticketingsystem.paymentservice.controller;

import com.busticketingsystem.paymentservice.dto.PaymentRequest;
import com.busticketingsystem.paymentservice.dto.PaymentResponse;
import com.busticketingsystem.paymentservice.service.PaymentService;
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

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController
{

	private final PaymentService paymentService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createPayment(@RequestBody PaymentRequest paymentRequest)
	{
		paymentService.createPayment(paymentRequest);
	}

	@GetMapping("/{id}")
	public PaymentResponse getPayment(@PathVariable Long id)
	{
		return paymentService.getPayment(id);
	}

	@PutMapping("/{id}")
	public void updatePayment(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest)
	{
		paymentService.updatePayment(id, paymentRequest);
	}

	@DeleteMapping("/{id}")
	public void deletePayment(@PathVariable Long id)
	{
		paymentService.deletePayment(id);
	}

	@GetMapping("/booking/{bookingId}")
	public PaymentResponse getPaymentByBookingId(@PathVariable Long bookingId)
	{
		return paymentService.getPaymentByBookingId(bookingId);
	}

	@GetMapping("/booking/{bookingId}/ispaid")
	public boolean isBookingPaid(@PathVariable Long bookingId)
	{
		return paymentService.isBookingPaid(bookingId);
	}
}
