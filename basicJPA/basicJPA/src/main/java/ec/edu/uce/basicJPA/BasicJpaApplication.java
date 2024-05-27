/*package ec.edu.uce.basicJPA;

import ec.edu.uce.basicJPA.models.Person;
import ec.edu.uce.basicJPA.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BasicJpaApplication implements CommandLineRunner {

	@Autowired
	PersonService service;

	public static void main(String[] args) {
		SpringApplication.run(BasicJpaApplication.class, args);
		//System.out.println("Hello World!");

		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		PersonService personService = ctx.getBean(PersonService.class);
		personService.save(new Person(1,"Mai",21,"Shiranui"));


	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("\nMENU");
			System.out.println("1. Agregar usuario");
			System.out.println("2. Buscar usuario por apellido");
			System.out.println("3. Actualizar usuario");
			System.out.println("4. Listar todos los usuarios");
			System.out.println("5. Salir");
			System.out.print("Seleccione una opción: ");

			int option = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (option) {
				case 1:
					System.out.print("Ingrese el nombre: ");
					String name = scanner.nextLine();
					System.out.print("Ingrese el apellido: ");
					String lastName = scanner.nextLine();
					System.out.print("Ingrese la edad: ");
					int age = scanner.nextInt();
					service.save(new Person(name, lastName, age));
					break;
				case 2:
					switch (option){
						case 1:
							System.out.print("Ingrese el apellido a buscar: ");
							String searchLastName = scanner.nextLine();
							List<Person> peopleByLastName = service.getPeopleByLastName(searchLastName);
							System.out.println("\nPersonas encontradas por apellido:");
							for (Person person : peopleByLastName) {
								System.out.println(person);
							}
							break;
						case 2:
							System.out.print("Ingrese el nombre a buscar: ");
							String searchName = scanner.nextLine();
							List<Person> peopleByName = service.getPeopleByName(searchName);
							System.out.println("\nPersonas encontradas por nombre:");
							for (Person person : peopleByName) {
								System.out.println(person);
							}
							break;
					}

				case 3:
					System.out.print("Ingrese el ID del usuario a actualizar: ");
					int idToUpdate = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character
					Person personToUpdate = service.getPersonById(idToUpdate);
					if (personToUpdate != null) {
						boolean continueUpdating = true;
						while (continueUpdating) {
							System.out.println("\nSeleccione el dato a actualizar:");
							System.out.println("1. Nombre");
							System.out.println("2. Apellido");
							System.out.println("3. Edad");
							System.out.println("4. Salir");
							System.out.print("Seleccione una opción: ");
							int updateOption = scanner.nextInt();
							scanner.nextLine(); // Consume the newline character
							switch (updateOption) {
								case 1:
									System.out.print("Ingrese el nuevo nombre: ");
									String newName = scanner.nextLine();
									personToUpdate.setName(newName);
									break;
								case 2:
									System.out.print("Ingrese el nuevo apellido: ");
									String newLastName = scanner.nextLine();
									personToUpdate.setLastname(newLastName);
									break;
								case 3:
									System.out.print("Ingrese la nueva edad: ");
									int newAge = scanner.nextInt();
									scanner.nextLine(); // Consume the newline character
									personToUpdate.setAge(newAge);
									break;
								case 4:
									continueUpdating = false;
									break;
								default:
									System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
							}
						}
						service.updatePerson(personToUpdate);
						System.out.println("Usuario actualizado: " + personToUpdate);
					} else {
						System.out.println("No se encontró ninguna persona con el ID especificado.");
					}
					break;
				case 4:
					Iterable<Person> allPeople = service.getAllPeople();
					System.out.println("\nLista de todos los usuarios:");
					for (Person p : allPeople) {
						System.out.println(p);
					}
					break;
				case 5:
					exit = true;
					break;
				default:
					System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
			}
		}
		System.out.println("Saliendo del programa...");
		System.exit(0);
	}
}*/