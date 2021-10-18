package com.daon.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daon.dev.domain.Gate;
import com.daon.dev.repository.GateRepository;

@Service
@Transactional
public class GateService {

	@Autowired
	private GateRepository gateRepository;

	@Autowired
	public GateService(GateRepository gateRepository) {
		this.gateRepository = gateRepository;
	}

	public List<Gate> getAllGates() {
		return gateRepository.findAll();
	}
	
	public List<Gate> getAllActiveGates(String status) {
		return gateRepository.findAllActive(status);
	}
	
	public Gate findById(Integer id) {
        return gateRepository.findOne(id);
    }
	
	public Gate saveGate(Gate gate) {
		
		if (gate.getGateOpened() != null) {
			gate.setGateOpened(gate.getGateOpened());
		}
		
		if (gate.getGateClosed() != null) {
			gate.setGateClosed(gate.getGateClosed());
		}
		
        return gateRepository.save(gate);
    }
	
	public void removeGate(Integer id) {
        gateRepository.delete(id);
    }
	
	public List<Gate> search(String opened, String closed, String status){
		return gateRepository.search(opened, closed, status);
	}
	

}
