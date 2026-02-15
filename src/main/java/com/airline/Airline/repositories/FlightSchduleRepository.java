package com.airline.Airline.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.Airline.entities.FlightSchedule;

@Repository
public interface FlightSchduleRepository extends JpaRepository<FlightSchedule, Integer>{

	List<FlightSchedule> findByDateOfTravel(LocalDateTime newDate);

}
