package  com.daon.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.daon.dev.domain.Person;
import  com.daon.dev.domain.Role;
import  com.daon.dev.exception.EmailTakenException;
import  com.daon.dev.exception.UsernameTakenException;
import  com.daon.dev.repository.PersonRepository;

import java.util.List;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        if (person.getPassword() != null && !person.getPassword().isEmpty()) {
            person.setEncryptedPassword(passwordEncoder.encode(person.getPassword()));
        }
        return personRepository.save(person);
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public List<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person findById(Integer id) {
        return personRepository.findOne(id);
    }

    public void removePerson(Person person) {
        personRepository.delete(person);
    }

    public void removePerson(Integer id) {
        personRepository.delete(id);
    }

    public Person registerNewPerson(Person person) throws EmailTakenException, UsernameTakenException {
        String username = person.getUsername();
        String email = person.getEmail();

        if (findByUsername(username) != null) {
            throw new UsernameTakenException("Username is already taken: " + username);
        }

        if (!findByEmail(email).isEmpty()) {
            throw new EmailTakenException("Email is already exists: " + email);
        }

        person.addRole(new Role("ROLE_USER"));

        return savePerson(person);
    }
}
