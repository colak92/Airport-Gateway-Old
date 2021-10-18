package  com.daon.dev.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Person> persons = new HashSet<Person>();
	
	public Role() {}
	
	public Role(String role){
		this.role = role;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	
	
}
