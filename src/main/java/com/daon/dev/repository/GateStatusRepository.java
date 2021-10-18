package com.daon.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daon.dev.domain.GateStatus;

@Repository
public interface GateStatusRepository extends JpaRepository<GateStatus, Integer> {

}
