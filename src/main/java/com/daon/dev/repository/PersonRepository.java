package  com.daon.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.daon.dev.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	public Person findByUsername(String username);
	
	public List<Person> findByEmail(String email);
	
}
