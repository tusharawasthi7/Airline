package com.airline.Airline.converter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.airline.Airline.Dto.BookingDto;
import com.airline.Airline.Exception.ObjectNotFoundException;
import com.airline.Airline.entities.Booking;
import com.airline.Airline.entities.FlightSchedule;
import com.airline.Airline.entities.Passenger;
import com.airline.Airline.entities.User;
import com.airline.Airline.repositories.FlightSchduleRepository;
import com.airline.Airline.repositories.UserRepository;

@Component
public class BookingDtoToBookingConverter implements Converter<BookingDto, Booking> {

	@Autowired
	FlightSchduleRepository flightSchduleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Booking convert(BookingDto source) {
		Booking booking=new Booking();
		booking.setBookingId(source.bookingID());
		booking.setNoOfSeats(source.noOfSeats());
		booking.setSeatClass(source.seatClass());
		booking.setDateOfBooking(LocalDateTime.now());
		
	
		List<Passenger> passengers = source.passengers();
		if (passengers != null) {
		    for (Passenger p : passengers) {
		        p.setBooking(booking); // 🔑 set owning side
		    }
		    booking.setPassengers(passengers);
		}
		
		FlightSchedule flightSchedule=this.flightSchduleRepository.findById(source.flightScheduleId()).orElseThrow(()->new ObjectNotFoundException("Flight Schedule", source.flightScheduleId()));
		booking.setFlightSchedule(flightSchedule);
		
		User user=this.userRepository.findById(source.userId()).orElseThrow(()-> new ObjectNotFoundException("User",source.userId()));
		booking.setUser(user);
		
		
		return booking;
	}
}





