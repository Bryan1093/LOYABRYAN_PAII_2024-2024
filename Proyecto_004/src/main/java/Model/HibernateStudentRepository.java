package Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateStudentRepository implements StudentRepository {
    private final SessionFactory sessionFactory;

    public HibernateStudentRepository() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Student.class);
        sessionFactory = configuration.buildSessionFactory();
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
}