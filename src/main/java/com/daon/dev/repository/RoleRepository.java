package  com.daon.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.daon.dev.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	
	
}
