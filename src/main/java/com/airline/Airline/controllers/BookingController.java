package com.airline.Airline.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.Airline.Dto.BookingDto;
import com.airline.Airline.Dto.FlightDto;
import com.airline.Airline.entities.Booking;
import com.airline.Airline.entities.Flight;
import com.airline.Airline.converter.BookingDtoToBookingConverter;
import com.airline.Airline.converter.BookingToBookingDtoConverter;
import com.airline.Airline.service.BookingService;
import com.airline.Airline.System.Result;
import com.airline.Airline.System.StatusCode;
import com.airline.Airline.validation.BookingAmount;
import com.airline.Airline.validation.BookingRegister;

@RestController
public class BookingController {
	
	
	BookingService bookingService;
	BookingDtoToBookingConverter bookingDtoToBookingConverter;
	BookingToBookingDtoConverter bookingToBookingDtoConverter;
	
	
	

	
	public BookingController(BookingService bookingService, BookingDtoToBookingConverter bookingDtoToBookingConverter,
			BookingToBookingDtoConverter bookingToBookingDtoConverter) {
		super();
		this.bookingService = bookingService;
		this.bookingDtoToBookingConverter = bookingDtoToBookingConverter;
		this.bookingToBookingDtoConverter = bookingToBookingDtoConverter;
	}


	//Booking a new seat
	@PostMapping("/user/booking")
	public Result addBooking( @Validated(BookingRegister.class)  @RequestBody BookingDto bookingDto)
	{
		Booking booking=this.bookingDtoToBookingConverter.convert(bookingDto);
		booking.setBookingAmount(this.bookingService.calculateBookingAmount(booking).intValue());
		Booking savedBooking=this.bookingService.bookFlight(booking);
		BookingDto savedBookingDto=this.bookingToBookingDtoConverter.convert(savedBooking);
		return new Result(true,StatusCode.SUCCESS,"Booking Successfull",savedBookingDto);
	}

		@GetMapping("/public/booking/{date}")	public Result getAllBookingByDate(@PathVariable LocalDate date)	{			List<Booking> bookingByDate=this.bookingService.findAllBookingByDate(date);
	    List<BookingDto> bookingByDateDto=bookingByDate.stream().map((x) -> bookingToBookingDtoConverter.convert(x)).collect(Collectors.toList());	    	    	return new Result(true, StatusCode.SUCCESS, "All Booking By date is :",bookingByDateDto);	}
	
	
//	//Calculate booking amount
//	@PostMapping("/bookingAmount")
//	public Result getBookingAmount(@Validated(BookingAmount.class) @RequestBody BookingDto bookingDto)
//	{
//		Double amount=this.bookingService.calculateBookingAmount(bookingDto);
//		return new Result(true,StatusCode.SUCCESS,"Total booking amount has been calculated",amount);
//	}
//	
//	
//	@GetMapping("/cancel/{bookingId}")
//	public Result cancelBooking(@PathVariable Integer bookingId)
//	{
//		Double  refundAmount=this.bookingService.cancelBookingById(bookingId);
//		return  new Result(true,StatusCode.SUCCESS,"Ticket cancelled Successfully, refund amount will be delivered",refundAmount);
//		
//	}
//	
//	@GetMapping("/{userId}")
//	public Result getAllBookings(@PathVariable Integer userId)
//	{
//		List<Booking> foundAllBooking=this.bookingService.getAllBooking(userId);
//	      List<BookingDto>  foundAllBookingDto=foundAllBooking.stream()
//	    		  .map((booking) -> this.bookingToBookingDtoConverter.convert(booking))
//	    		  .collect(Collectors.toList());
//		 
//		  return new Result(true,StatusCode.SUCCESS,"All bookings",foundAllBookingDto);
//	}


	
	
}










