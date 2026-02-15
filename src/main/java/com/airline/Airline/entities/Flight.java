package com.airline.Airline.entities;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Flight {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	     Integer flightId;
	    
	    
	    @OneToOne
	    Carrier carrier; 

	    
	    String origin;

	
	    String destination;

	 
	    Integer airFare;

	
	    Integer seatCapacityEconomyClass;

	    Integer seatCapacityBusinessClass;

	    Integer seatCapacityExecutiveClass;
	    
	    

		public Flight() {
			super();
		}



		public Flight(Integer flightId, Carrier carrier, String origin, String destination, Integer airFare,
				Integer seatCapacityEconomyClass, Integer seatCapacityBusinessClass,
				Integer seatCapacityExecutiveClass) {
			super();
			this.flightId = flightId;
			this.carrier = carrier;
			this.origin = origin;
			this.destination = destination;
			this.airFare = airFare;
			this.seatCapacityEconomyClass = seatCapacityEconomyClass;
			this.seatCapacityBusinessClass = seatCapacityBusinessClass;
			this.seatCapacityExecutiveClass = seatCapacityExecutiveClass;
		}



		public Integer getFlightId() {
			return flightId;
		}



		public void setFlightId(Integer flightId) {
			this.flightId = flightId;
		}



		public Carrier getCarrier() {
			return carrier;
		}



		public void setCarrier(Carrier carrier) {
			this.carrier = carrier;
		}



		public String getOrigin() {
			return origin;
		}



		public void setOrigin(String origin) {
			this.origin = origin;
		}



		public String getDestination() {
			return destination;
		}



		public void setDestination(String destination) {
			this.destination = destination;
		}



		public Integer getAirFare() {
			return airFare;
		}



		public void setAirFare(Integer airFare) {
			this.airFare = airFare;
		}



		public Integer getSeatCapacityEconomyClass() {
			return seatCapacityEconomyClass;
		}



		public void setSeatCapacityEconomyClass(Integer seatCapacityEconomyClass) {
			this.seatCapacityEconomyClass = seatCapacityEconomyClass;
		}



		public Integer getSeatCapacityBusinessClass() {
			return seatCapacityBusinessClass;
		}



		public void setSeatCapacityBusinessClass(Integer seatCapacityBusinessClass) {
			this.seatCapacityBusinessClass = seatCapacityBusinessClass;
		}



		public Integer getSeatCapacityExecutiveClass() {
			return seatCapacityExecutiveClass;
		}



		public void setSeatCapacityExecutiveClass(Integer seatCapacityExecutiveClass) {
			this.seatCapacityExecutiveClass = seatCapacityExecutiveClass;
		}
	    
	    
	    
	    
	    
	
}
