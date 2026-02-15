









package com.airline.Airline.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class FlightSchedule {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
Integer flightScheduleId;

@OneToOne
Flight flight;

@OneToMany(mappedBy="flightSchedule")
List<Booking> booking;

LocalDateTime dateOfTravel;
Integer businessClassBookedDiscount;
Integer economyClassBookedDiscount;
Integer executiveClassBookedDiscount;

public Integer getFlightScheduleId() {
	return flightScheduleId;
}
public void setFlightScheduleId(Integer flightScheduleId) {
	this.flightScheduleId = flightScheduleId;
}
public Flight  getFlight() {
	return flight;
}
public void setFligt(Flight flight) {
	this.flight = flight;
}
public LocalDateTime getDateOfTravel() {
	return dateOfTravel;
}
public void setDateOfTravel(LocalDateTime dateOfTravel) {
	this.dateOfTravel = dateOfTravel;
}
public Integer getBusinessClassBookedDiscount() {
	return businessClassBookedDiscount;
}
public void setBusinessClassBookedDiscount(Integer businessClassBookedDiscount) {
	this.businessClassBookedDiscount = businessClassBookedDiscount;
}
public Integer getEconomyClassBookedDiscount() {
	return economyClassBookedDiscount;
}
public void setEconomyClassBookedDiscount(Integer economyClassBookedDiscount) {
	this.economyClassBookedDiscount = economyClassBookedDiscount;
}
public Integer getExecutiveClassBookedDiscount() {
	return executiveClassBookedDiscount;
}
public void setExecutiveClassBookedDiscount(Integer executiveClassBookedDiscount) {
	this.executiveClassBookedDiscount = executiveClassBookedDiscount;
}
public List<Booking> getBooking() {
	return booking;
}
public void setBooking(List<Booking> booking) {
	this.booking = booking;
}
public void setFlight(Flight flight) {
	this.flight = flight;
}



}
