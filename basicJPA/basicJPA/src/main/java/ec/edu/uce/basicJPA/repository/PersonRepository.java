package ec.edu.uce.basicJPA.repository;

import ec.edu.uce.basicJPA.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByLastname(String lastname);
    List<Person> findByName(String name);
}