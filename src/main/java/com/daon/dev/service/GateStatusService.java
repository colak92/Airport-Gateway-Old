package com.daon.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daon.dev.domain.GateStatus;
import com.daon.dev.repository.GateStatusRepository;

@Service
@Transactional
public class GateStatusService {
	
	@Autowired
	private GateStatusRepository gateStatusRepository;
	
	public GateStatusService(GateStatusRepository gateStatusRepository) {
		this.gateStatusRepository = gateStatusRepository;
	}
	
	public List<GateStatus> getAllGateStatuses() {
		return gateStatusRepository.findAll();
	}
	
	public GateStatus findById(Integer id) {
        return gateStatusRepository.findOne(id);
    }

}
