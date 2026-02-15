package com.airline.Airline.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airline.Airline.entities.Flight;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	Page<Flight> findByOriginContainingAndDestinationContaining(String origin, String destination,Pageable page);


	@Modifying
	@Query("UPDATE Flight f SET f.seatCapacityEconomyClass = f.seatCapacityEconomyClass - 1 WHERE f.flightId = :flightId AND f.seatCapacityEconomyClass > 0")
	int decrementEconomySeat(@Param("flightId") Integer flightId);

	@Modifying
	@Query("UPDATE Flight f SET f.seatCapacityBusinessClass = f.seatCapacityBusinessClass - 1 WHERE f.flightId = :flightId AND f.seatCapacityBusinessClass > 0")
	int decrementBussinessSeat(@Param("flightId") Integer flightId);


	@Modifying
	@Query("UPDATE Flight f SET f.seatCapacityExecutiveClass = f.seatCapacityExecutiveClass - 1  WHERE f.id = :flightId AND f.seatCapacityExecutiveClass > 0")
	int decrementExecutiveSeat(@Param("flightId") Integer flightId);

	
	
}
