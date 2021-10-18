package com.daon.dev.domain;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Gate {
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String gateName;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "Gate opened date cannot be empty")
	private Date gateOpened;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "Gate closed date cannot be empty")
	private Date gateClosed;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;
	
	@OneToOne
	private GateStatus gateStatus;
	
	@OneToMany(mappedBy= "gate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GateStatus> gateStatusList = new ArrayList<GateStatus>();
	
	public Gate() {}
	
	public Gate(Integer id, String gateName, Date gateOpened, Date gateClosed, Flight flight, GateStatus gateStatus) {
		super();
		this.id = id;
		this.gateName = gateName;
		this.gateOpened = gateOpened;
		this.gateClosed = gateClosed;
		this.flight = flight;
		this.gateStatus = gateStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public Date getGateOpened() {
		return gateOpened;
	}

	public void setGateOpened(Date gateOpened) {
		this.gateOpened = gateOpened;
	}

	public Date getGateClosed() {
		return gateClosed;
	}

	public void setGateClosed(Date gateClosed) {
		this.gateClosed = gateClosed;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public GateStatus getGateStatus() {
		return gateStatus;
	}
	
	public void setGateStatus(GateStatus gateStatus) {
		this.gateStatus = gateStatus;
	}
	
	public List<GateStatus> getGateStatusList() {
		return gateStatusList;
	}
	
	public void setGateStatusList(List<GateStatus> gateStatusList) {
		this.gateStatusList = gateStatusList;
	}
	

}
