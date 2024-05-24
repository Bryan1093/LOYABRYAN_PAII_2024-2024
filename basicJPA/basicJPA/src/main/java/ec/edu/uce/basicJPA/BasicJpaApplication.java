package ec.edu.uce.basicJPA;

import ec.edu.uce.basicJPA.models.Person;
import ec.edu.uce.basicJPA.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class BasicJpaApplication implements CommandLineRunner {

	@Autowired
	PersonService service;

	public static void main(String[] args) {
		SpringApplication.run(BasicJpaApplication.class, args);
		//System.out.println("Hello World!");

		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		PersonService personService = ctx.getBean(PersonService.class);
		personService.save(new Person(1,"Mai",21,"Shiranui"));*/


	}

	@Override
	public void run(String... args) throws Exception {
		service.save(new Person(1,"Mai","Shiranui",21));
		service.save(new Person(2,"Are","Kuruou",23));
		service.save(new Person(3,"Pepe","El grillo",120));
		service.save(new Person(4,"Pepe","La Anguila",30));
		service.save(new Person(5,"Jackie","Chan",50));

		// Busca e imprime personas por su apellido
		List<Person> peopleByLastName = service.getPeopleByLastName("El grillo");
		System.out.println(" ");
		System.out.println("Personas encontradas por apellido:");
		for (Person person : peopleByLastName) {
			System.out.println(person);
		}

		// Busca e imprime personas por su apellido
		List<Person> peopleByName = service.getPeopleByName("Mai");
		System.out.println(" ");
		System.out.println("Personas encontradas por nombre:");

		for (Person person : peopleByName) {
			System.out.println(person);
		}

		// Busca e imprime una persona por su ID
		Person person = service.getPersonById(3);

		if (person != null) {
			System.out.println(" ");
			System.out.println("Persona encontrada por ID:");
			System.out.println(person);
		} else {
			System.out.println(" ");
			System.out.println("No se encontró ninguna persona con el ID especificado.");
		}

		// Recupera todos los datos de la base de datos
		Iterable<Person> people = service.getAllPeople();

		// Imprime los datos en la consola
		System.out.println("Lista de personas:");
		for (Person p : people) {
			System.out.println(" ");
			System.out.println(p);
		}
		System.exit(0);
	}
}