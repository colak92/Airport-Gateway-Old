package com.daon.dev.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String flightNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gate_id", nullable = false)
	private Gate gate;
	
	@OneToMany(mappedBy= "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Gate> gateList = new ArrayList<Gate>();
	
	public Flight() {}
	
	public Flight(Integer id, String flightNumber, Gate gate) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.gate = gate;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public Gate getGate() {
		return gate;
	}
	
	public void setGate(Gate gate) {
		this.gate = gate;
	}
	
	public List<Gate> getGateList() {
		return gateList;
	}
	
	public void setGateList(List<Gate> gateList) {
		this.gateList = gateList;
	}
	

}
