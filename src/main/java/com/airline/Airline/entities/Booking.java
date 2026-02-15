package com.airline.Airline.entities;



import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Booking {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    Integer bookingId;
    
	
	@ManyToOne
	@JoinColumn(name = "user_user_id", nullable = false) 
	@JsonIgnore
	User user;
	
	Integer noOfSeats;

	@OneToMany(mappedBy="booking", cascade=CascadeType.ALL)
	@JsonIgnore
	List<Passenger> passengers;
	LocalDateTime dateOfBooking;
	String bookingStatus;
	Integer bookingAmount;
	
	@ManyToOne
	@JoinColumn(name = "flight_schedule_flight_schedule_id", nullable = false)
	@JsonIgnore
	FlightSchedule flightSchedule;
	String  seatClass;
	String paymentStatus;
	
	
	public Booking() {
		super();
	}


	public Booking(Integer bookingId, User user, Integer noOfSeats, List<Passenger> passengers, LocalDateTime dateOfBooking,
			String bookingStatus, Integer bookingAmount, FlightSchedule flightSchedule, String seatClass,
			String paymentStatus) {
		super();
		this.bookingId = bookingId;
		this.user = user;
		this.noOfSeats = noOfSeats;
		this.passengers = passengers;
		this.dateOfBooking = dateOfBooking;
		this.bookingStatus = bookingStatus;
		this.bookingAmount = bookingAmount;
		this.flightSchedule = flightSchedule;
		this.seatClass = seatClass;
		this.paymentStatus = paymentStatus;
	}


	public Integer getBookingId() {
		return bookingId;
	}


	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getNoOfSeats() {
		return noOfSeats;
	}


	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}


	public List<Passenger> getPassengers() {
		return passengers;
	}


	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}


	public LocalDateTime getDateOfBooking() {
		return dateOfBooking;
	}


	public void setDateOfBooking(LocalDateTime dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}


	public String getBookingStatus() {
		return bookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	public Integer getBookingAmount() {
		return bookingAmount;
	}


	public void setBookingAmount(Integer bookingAmount) {
		this.bookingAmount = bookingAmount;
	}


	public FlightSchedule getFlightSchedule() {
		return flightSchedule;
	}


	public void setFlightSchedule(FlightSchedule flightSchedule) {
		this.flightSchedule = flightSchedule;
	}


	public String getSeatClass() {
		return seatClass;
	}


	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", user=" + user + ", noOfSeats=" + noOfSeats + ", passengers="
				+ passengers + ", dateOfBooking=" + dateOfBooking + ", bookingStatus=" + bookingStatus
				+ ", bookingAmount=" + bookingAmount + ", flightSchedule=" + flightSchedule + ", seatClass="
				+ seatClass + ", paymentStatus=" + paymentStatus + "]";
	}
   
	
	
	



	

	
}