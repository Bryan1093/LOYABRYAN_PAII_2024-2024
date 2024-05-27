/*package ec.edu.uce.basicJPA.repository;

import ec.edu.uce.basicJPA.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByLastname(String lastname);
    List<Person> findByName(String name);
    @Override
    <S extends Person> S save(S entity);
}*/