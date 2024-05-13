package Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateStudentRepository implements StudentRepository {
	private final SessionFactory sessionFactory;

	// Constructor que recibe un objeto SessionFactory para inicializar la sesión
	public HibernateStudentRepository(SessionFactory sessionFactory) {
		// Configuración de Hibernate
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Student.class);
		this.sessionFactory = configuration.buildSessionFactory();
	}

	// Constructor por defecto
	public HibernateStudentRepository() {
		this.sessionFactory = null;
	}

	@Override
	public void save(Student student) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
		}
	}

	@Override
	public void delete(long ci) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Student student = session.get(Student.class, ci);
			if (student != null) {
				session.delete(student);
			}
			transaction.commit();
		}
	}

	@Override
	public Student findByCI(long ci) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(Student.class, ci);
		}
	}

	// Método para obtener el último ID de estudiante almacenado en la base de datos
	@Override
	public int getLastId() {
		try (Session session = sessionFactory.openSession()) {
			// Consulta para obtener el máximo ID de estudiante
			Integer result = (Integer) session.createQuery("select max(id) from Student").uniqueResult();
			// Si no hay ningún estudiante en la base de datos, se devuelve 0
			return result != null ? result.intValue() : 0;
		}
	}
}