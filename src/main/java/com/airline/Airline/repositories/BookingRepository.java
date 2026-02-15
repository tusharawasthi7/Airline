package com.airline.Airline.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airline.Airline.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

	@Modifying
	@Query("Select b from  Booking b where b.dateOfBooking >= :start AND b.dateOfBooking<= :end ")
	List<Booking> findByDateOfBooking(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


}





