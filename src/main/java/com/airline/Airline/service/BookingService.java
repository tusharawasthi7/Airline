package com.airline.Airline.service;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.airline.Airline.Dto.BookingDto;
import com.airline.Airline.entities.Booking;
import com.airline.Airline.entities.Carrier;
import com.airline.Airline.entities.Flight;
import com.airline.Airline.entities.FlightSchedule;
import com.airline.Airline.entities.User;
import com.airline.Airline.Exception.NoSeatAvailableException;
import com.airline.Airline.Exception.ObjectNotFoundException;
import com.airline.Airline.repositories.BookingRepository;
import com.airline.Airline.repositories.CarrierRepository;
import com.airline.Airline.repositories.FlightRepository;
import com.airline.Airline.repositories.FlightSchduleRepository;
import com.airline.Airline.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingService {

	
	BookingRepository bookingRepository;
	UserRepository userRepository;
	FlightRepository flightRepository;
	CarrierRepository carrierRepository;
	FlightSchduleRepository flightSchduleRepository;
	
	




	public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
			FlightRepository flightRepository, CarrierRepository carrierRepository,
			FlightSchduleRepository flightSchduleRepository) {
		super();
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
		this.carrierRepository = carrierRepository;
		this.flightSchduleRepository = flightSchduleRepository;
	}


	@Transactional
	public Booking bookFlight(Booking booking) {
			
		
		//1. Idempotency check later add it
		
		
		
		//Trying atomic decrement
		String seatClass =booking.getSeatClass();
		int updated=-1;
		if(seatClass.equalsIgnoreCase("Executive"))
		{
			updated=flightRepository.decrementExecutiveSeat(booking.getFlightSchedule().getFlightScheduleId());
		}
		else if(seatClass.equalsIgnoreCase("Economy")){
			updated=flightRepository.decrementEconomySeat(booking.getFlightSchedule().getFlightScheduleId());

		}
		else if(seatClass.equalsIgnoreCase("Bussiness")){
			updated=flightRepository.decrementBussinessSeat(booking.getFlightSchedule().getFlightScheduleId());
		}
		if(updated==0)
		{
			throw new NoSeatAvailableException("No Seats Available");
		}
		
		//When everything is fine 
		booking.setBookingStatus("Confirmed");
		bookingRepository.save(booking);
		
		
		//call the mail service asynchronously
		
		return booking;
	}


	public Double calculateBookingAmount(Booking booking) {
		
		
		Double totalFare=booking.getFlightSchedule().getFlight().getAirFare()*booking.getPassengers().size()*1.0;
		
		
		Double userCategoryDiscount=0.0;
		Double advanceBookingDiscount=0.0;
		Double seatCategoryDiscount=0.0;
		
		Carrier carrier=booking.getFlightSchedule().getFlight().getCarrier();

		
		//Discount due to User category
		String userCategory=booking.getUser().getCustomerCategory();
		if(userCategory.equalsIgnoreCase("Silver"))
		{
			userCategoryDiscount=carrier.getSilverUserDiscount()*1.0;
		}
		else if(userCategory.equalsIgnoreCase("Gold"))
		{
			userCategoryDiscount=carrier.getGoldUserDiscount()*1.0;
		}
		else if(userCategory.equalsIgnoreCase("Platinum"))
		{
			userCategoryDiscount=carrier.getPlatinumUserDiscount()*1.0;
		}
		
		
		
		
		// Discount due to Advance booking
		LocalDateTime currentDate=LocalDateTime.now();
		LocalDateTime dateOftravel=booking.getFlightSchedule().getDateOfTravel();
		Duration difference=Duration.between(currentDate, dateOftravel);
		Long advanceBooking=difference.toDays();
		
		if(advanceBooking>30)
		{
		advanceBookingDiscount=carrier.getDiscountPercentageThirtyDaysAdvanceBooking()*1.0;	
		}
		else if(advanceBooking>60)
		{
			advanceBookingDiscount=carrier.getDiscountPercentageSixDaysAdvanceBooking()*1.0;
		}
		else if(advanceBooking>90)
		{
			advanceBookingDiscount=carrier.getDiscountPercentageNinteyDaysAdvanceBooking()*1.0;
		}
		
		
		
		
		//Discount  on seat Category like bussiness executive and economy
		
		FlightSchedule flightSchedule=booking.getFlightSchedule();
		String seatCategory=booking.getSeatClass();
		if(seatCategory.equalsIgnoreCase("Bussiness"))
		{
			seatCategoryDiscount=flightSchedule.getBusinessClassBookedDiscount()*1.0;
		}
		else if(seatCategory.equalsIgnoreCase("Executive"))
		{
			seatCategoryDiscount=flightSchedule.getExecutiveClassBookedDiscount()*1.0;
		}
		else if(seatCategory.equalsIgnoreCase("Economy"))
		{
			seatCategoryDiscount=flightSchedule.getEconomyClassBookedDiscount()*1.0;
		}
		
		
		Double finalAmount=totalFare - (totalFare*userCategoryDiscount/100.0)-(totalFare*seatCategoryDiscount/100.0)-(totalFare*advanceBookingDiscount/100.0);
		return 	finalAmount;
	}


	public List<Booking> findAllBookingByDate(LocalDate date) {
		LocalDateTime start=date.atStartOfDay();
		LocalDateTime end=date.plusDays(1).atStartOfDay();
		return this.bookingRepository.findByDateOfBooking(start,end);
	}


//	public Double cancelBookingById(Integer bookingId) {
//		
//		System.out.println("Bokking id readed in cancel booking by id"+bookingId);
//		
//		Booking booked=this.bookingRepository.findById(bookingId).orElseThrow(() -> new ObjectNotFoundException("Booking", bookingId));
//		
//		
//		FlightSchedule flightSchedule=this.flightSchduleRepository.findById(booked.getBookingId()).get();
//		Flight flight=this.flightRepository.findById(flightSchedule.getFlightId()).get();
//		Carrier carrier=this.carrierRepository.findById(flight.getCarrierId()).get();
//		
//		
//		//Calculating refund
//		//Calculating discount to be given based on date
//		   Integer refundPercentage=0;
//		   Date bookingDate=booked.getDateOfTravel();
//		   Date currentDate=new Date();
//		   long diffInMillis = bookingDate.getTime() - currentDate.getTime();
//		   long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
//		   
//		   if(diffInDays==2)
//			   refundPercentage=carrier.getRefundPercentageForTicketCancellation2DaysBeforeTravelDate();
//		   else if(diffInDays==10)
//			   refundPercentage=carrier.getRefundPercentageForTicketCancellation10DaysBeforeTravelDate();
//		   else 
//			   refundPercentage=carrier.getRefundPercentageForTicketCancellation20DaysBeforeTravelDate();
//		   
//		   
//		   booked.setBookingStatus("Cancelled");
//		   
//		   this.bookingRepository.save(booked);
//		   
//		   return (booked.getBookingAmount()*refundPercentage)/100.0;
//		
//	}
//
//
//	public List<Booking> getAllBooking(Integer userId) {
//		return this.bookingRepository.findByUserId(userId);
//	}
//	
//	

}


