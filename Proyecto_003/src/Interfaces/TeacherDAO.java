package Interfaces;

import java.sql.SQLException;
import java.util.List;
import Clases.Teacher;

public interface TeacherDAO {

    // Método para crear un nuevo profesor en la base de datos
	boolean crear(Teacher teacher) throws SQLException;

    // Método para leer un profesor de la base de datos dado su ID
	Teacher leer(int id) throws SQLException;

    // Método para actualizar un profesor en la base de datos dado su ID y un nuevo objeto Teacher
	boolean actualizar(int id, Teacher teacher) throws SQLException;

    // Método para eliminar un profesor de la base de datos dado su ID
	boolean eliminar(int id) throws SQLException;

    // Método para listar todos los profesores almacenados en la base de datos
	List<Teacher> listar() throws SQLException;
}