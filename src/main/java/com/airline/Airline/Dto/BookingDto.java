package com.airline.Airline.Dto;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.airline.Airline.entities.Passenger;
import com.airline.Airline.validation.BookingAmount;
import com.airline.Airline.validation.BookingRegister;
import com.airline.Airline.validation.CancelBooking;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookingDto(
		@NotNull(message="Booking Id is required" ,groups={CancelBooking.class})
		Integer bookingID ,
		
		@NotNull(message = "User Id  is required",groups = {BookingRegister.class ,BookingAmount.class}) 
		Integer userId,

		@NotNull(message = "No of seats is required",groups = {BookingRegister.class ,BookingAmount.class })
		Integer noOfSeats,

		
		

		@NotNull(message = "Flight ID is required",groups = {BookingRegister.class ,BookingAmount.class}) 
		Integer flightScheduleId,
		
		
		@DateTimeFormat(pattern = "yyyy-MM-dd") 
		@NotNull(message = "Date is required" , groups = {BookingRegister.class ,BookingAmount.class})
		@FutureOrPresent(message = "Schedule date should be present or future", groups = {BookingRegister.class }) 
		LocalDateTime dateOfBooking,

		
		@NotEmpty(message = "Seat category is required",groups = {BookingRegister.class ,BookingAmount.class})
		String seatClass,
		String bookingStatus,
		Integer bookingAmount,
		@NotEmpty(message="Passenger list is required" , groups= {BookingRegister.class})
		List<Passenger> passengers		
	){


}


