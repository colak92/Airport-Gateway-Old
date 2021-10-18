package com.daon.dev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GateStatus {
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String gateStatusName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gate_id")
    private Gate gate;
	
	public GateStatus() {}
	
	public GateStatus(Integer id, String gateStatusName, Gate gate) {
		super();
		this.id = id;
		this.gateStatusName = gateStatusName;
		this.gate = gate;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getGateStatusName() {
		return gateStatusName;
	}

	public void setGateStatusName(String gateStatusName) {
		this.gateStatusName = gateStatusName;
	}
	
	public Gate getGate() {
		return gate;
	}
	
	public void setGate(Gate gate) {
		this.gate = gate;
	}
	
}
