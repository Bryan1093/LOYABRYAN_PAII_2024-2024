package Model;

public interface StudentRepository {
    void save(Student student);
    void delete(long ci);
    Student findByCI(long ci);
}