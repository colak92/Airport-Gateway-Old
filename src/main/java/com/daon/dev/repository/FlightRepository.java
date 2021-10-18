package com.daon.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daon.dev.domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	
	@Query(value = "SELECT * FROM flight WHERE " + "MATCH (flight_number) " + "AGAINST (?1)", nativeQuery = true)
	public List<Flight> searchByFlightNumber(String keyword);

	@Query(value = "SELECT * FROM flight f, gate g, gate_status gs WHERE f.gate_id = g.id AND g.gate_status_id = gs.id "
			+ "AND g.gate_opened >= :opened AND g.gate_closed <= :closed", nativeQuery = true)
	public List<Flight> searchByDateInterval(@Param("opened") String opened, @Param("closed") String closed);
}
