package com.daon.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daon.dev.domain.Gate;

@Repository
public interface GateRepository extends JpaRepository<Gate, Integer> {

	@Query(value = "SELECT * FROM gate g, gate_status gs WHERE g.gate_opened >= :opened and g.gate_closed <= :closed and g.gate_status_id = gs.id and gs.gate_status_name = :status", nativeQuery = true)
	public List<Gate> search(@Param("opened") String opened, @Param("closed") String closed, @Param("status") String status);
	
	@Query(value = "SELECT * FROM gate g, gate_status gs WHERE g.gate_status_id = gs.id and gs.gate_status_name = :status", nativeQuery = true)
	public List<Gate> findAllActive(@Param("status") String status);

}
