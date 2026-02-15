package com.airline.Airline.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.BookingDto;
import com.airline.Airline.entities.Booking;

@Component
public class BookingToBookingDtoConverter implements Converter<Booking,BookingDto> {

	@Override
	public BookingDto convert(Booking source) {
		BookingDto bookingDto=new BookingDto(
				source.getBookingId(),
			source.getUser().getUserId(),
				source.getNoOfSeats(),
				
				source.getFlightSchedule().getFlightScheduleId(),
				
				source.getDateOfBooking(),
				source.getSeatClass(),
				source.getBookingStatus(),
				source.getBookingAmount(),
				source.getPassengers()
				);
		
		return bookingDto;
	}

}