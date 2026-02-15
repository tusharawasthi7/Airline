package com.airline.Airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.Airline.entities.Carrier;
@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Integer> {

}