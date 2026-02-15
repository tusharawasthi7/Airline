package com.airline.Airline.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Passenger {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer passengerId;
	String passengerName;
	Integer age;
	
	
	@ManyToOne
	@JsonIgnore
	Booking booking;
	
	
	public Booking getBooking() {
		return booking;
	}



	public void setBooking(Booking booking) {
		this.booking = booking;
	}



	public Passenger() {
		super();
	}
	


	public Passenger(Integer passengerId, String passengerName, Integer age) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.age = age;
	}



	public Integer getPassengerId() {
		return passengerId;
	}


	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Passengers [passengerId=" + passengerId + ", passengerName=" + passengerName + ", age=" + age + "]";
	}
	
	
	
}
