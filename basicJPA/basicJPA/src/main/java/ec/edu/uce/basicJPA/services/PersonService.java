package ec.edu.uce.basicJPA.services;

import ec.edu.uce.basicJPA.models.Person;
import ec.edu.uce.basicJPA.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    /*public void save(Person person) {
        repository.save(person);
    }*/

    public Iterable<Person> getAllPeople() {
        return repository.findAll();
    }

    public Person getPersonById(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Person> getPeopleByLastName(String lastName) {
        return repository.findByLastname(lastName);
    }

    public List<Person> getPeopleByName(String Name) {
        return repository.findByName(Name);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void updatePerson(Person person) {
        if (repository.existsById(person.getId())) {
            repository.save(person);
        } else {
            throw new IllegalArgumentException("Person with id " + person.getId() + " does not exist.");
        }
    }

    @Autowired
    private PersonRepository personRepository;

    public void save(Person person) {
        personRepository.save(person);
    }
}