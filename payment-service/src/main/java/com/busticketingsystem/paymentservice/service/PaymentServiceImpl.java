package com.busticketingsystem.paymentservice.service;

import com.busticketingsystem.paymentservice.dto.PaymentRequest;
import com.busticketingsystem.paymentservice.dto.PaymentResponse;
import com.busticketingsystem.paymentservice.model.Payment;
import com.busticketingsystem.paymentservice.model.PaymentStatus;
import com.busticketingsystem.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService
{
	private final PaymentRepository paymentRepository;
	private final WebClient webClient;

	@Override
	public void createPayment(PaymentRequest paymentRequest)
	{
		Payment payment = mapToPayment(paymentRequest);
		paymentRepository.save(payment);
	}

	@Override
	public PaymentResponse getPayment(Long id)
	{
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
		return mapToPaymentResponse(payment);
	}

	@Override
	public void updatePayment(Long id, PaymentRequest paymentRequest)
	{
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
		payment.setPaymentDate(paymentRequest.getPaymentDate());
		payment.setAmount(paymentRequest.getAmount());
		payment.setStatus(paymentRequest.getStatus());
		payment.setBookingId(paymentRequest.getBookingId());
		paymentRepository.save(payment);

		if (payment.getStatus() == PaymentStatus.CONFIRMED)
		{
			webClient.put()
					.uri("http://localhost:8082/api/booking/" + +payment.getBookingId() + "confirm")
					.retrieve()
					.bodyToMono(Void.class)
					.block();
		}
		if (payment.getStatus() == PaymentStatus.FAILED)
		{
			webClient.put()
					.uri("http://localhost:8082/api/booking/" + +payment.getBookingId() + "cancel")
					.retrieve()
					.bodyToMono(Void.class)
					.block();
		}
	}

	@Override
	public void deletePayment(Long id)
	{
		paymentRepository.deleteById(id);
	}

	@Override
	public PaymentResponse getPaymentByBookingId(Long bookingId)
	{
		Payment payment = paymentRepository.findByBookingId(bookingId).orElseThrow(() -> new RuntimeException("Payment not found"));
		return mapToPaymentResponse(payment);
	}

	@Override
	public boolean isBookingPaid(Long bookingId)
	{
		Payment payment = paymentRepository.findByBookingId(bookingId).orElseThrow(() -> new RuntimeException("Payment not found"));
		return PaymentStatus.CONFIRMED.equals(payment.getStatus());
	}

	private Payment mapToPayment(PaymentRequest paymentRequest)
	{
		return Payment.builder()
				.bookingId(paymentRequest.getBookingId())
				.amount(paymentRequest.getAmount())
				.paymentDate(paymentRequest.getPaymentDate())
				.status(paymentRequest.getStatus())
				.build();
	}

	private PaymentResponse mapToPaymentResponse(Payment payment)
	{
		return PaymentResponse.builder()
				.id(payment.getId())
				.bookingId(payment.getBookingId())
				.amount(payment.getAmount())
				.paymentDate(payment.getPaymentDate())
				.status(payment.getStatus())
				.build();
	}
}

