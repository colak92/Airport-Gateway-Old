package com.daon.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daon.dev.domain.Flight;
import com.daon.dev.repository.FlightRepository;

@Service
@Transactional
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	public FlightService(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	public Flight findById(Integer id) {
		return flightRepository.findOne(id);
	}

	public Flight saveFlight(Flight flight) {

		if (flight.getFlightNumber() != null && !flight.getFlightNumber().isEmpty()) {
			flight.setFlightNumber(flight.getFlightNumber());
		}

		return flightRepository.save(flight);
	}

	public void removeFlight(Integer id) {
		flightRepository.delete(id);
	}
	
	public List<Flight> searchByFlightNumber(String keyword){
		return flightRepository.searchByFlightNumber(keyword);
	}

	public List<Flight> searchByDateInterval(String opened, String closed){
		return flightRepository.searchByDateInterval(opened, closed);
	}
	
}
