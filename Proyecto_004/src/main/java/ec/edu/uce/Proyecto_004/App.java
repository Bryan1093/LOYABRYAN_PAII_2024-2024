package ec.edu.uce.Proyecto_004;

import javax.swing.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import Model.HibernateStudentRepository;
import Model.Student;
import Model.StudentGUI;
import Model.StudentRepository;

import javax.swing.*;

public class App {

	private final StudentRepository studentRepository;

	public App(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {

		// Configurar la sesión de Hibernate
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Student.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// Crear el repositorio de estudiantes con la sesión de Hibernate
		StudentRepository studentRepository = new HibernateStudentRepository(sessionFactory);

		// Obtener el último ID de la base de datos
		int lastId = studentRepository.getLastId();

		// Crear y mostrar la interfaz gráfica
		SwingUtilities.invokeLater(() -> {
			new StudentGUI(studentRepository, lastId + 1); // Pasar el último ID + 1 a la interfaz gráfica
		});
	}

}
