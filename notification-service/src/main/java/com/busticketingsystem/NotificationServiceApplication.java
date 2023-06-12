package com.busticketingsystem;

import com.busticketingsystem.event.BookingConfirmedEvent;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceApplication
{

	private final ObservationRegistry observationRegistry;

	public static void main(String[] args)
	{
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "Booking completed")
	public void handleNotification(BookingConfirmedEvent bookingConfirmedEvent)
	{
		Observation.createNotStarted("on-message", this.observationRegistry).observe(() ->
		{
			log.info("Got message <{}>", bookingConfirmedEvent);
			log.info("Received Notification for Order - {}",
					bookingConfirmedEvent.getBookingId());

			switch (bookingConfirmedEvent.getNotificationType())
			{
				case EMAIL:
					// code to send an email notification
					log.info("Sending email notification to {} for Booking - {}",
							bookingConfirmedEvent.getEmail(),
							bookingConfirmedEvent.getBookingId());
					break;
				case SMS:
					// code to send a phone notification
					log.info("Sending phone notification to {} for Booking - {}",
							bookingConfirmedEvent.getPhoneNumber(),
							bookingConfirmedEvent.getBookingId());
					break;
				default:
					log.info("No valid notification type found for Booking - {}",
							bookingConfirmedEvent.getBookingId());
					break;
			}
		});
	}
}
