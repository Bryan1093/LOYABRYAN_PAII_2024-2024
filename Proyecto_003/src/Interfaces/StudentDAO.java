package Interfaces;

import java.sql.SQLException;
import java.util.List;
import Clases.Student;

public interface StudentDAO {

    // Método para crear un nuevo estudiante en la base de datos
	boolean crear(Student student) throws SQLException;

    // Método para leer un estudiante de la base de datos dado su ID
	Student leer(int id) throws SQLException;

    // Método para actualizar un estudiante en la base de datos dado su ID y un nuevo objeto Student
	boolean actualizar(int id, Student student) throws SQLException;

    // Método para eliminar un estudiante de la base de datos dado su ID
	boolean eliminar(int id) throws SQLException;

    // Método para listar todos los estudiantes almacenados en la base de datos
	List<Student> listar() throws SQLException;
}