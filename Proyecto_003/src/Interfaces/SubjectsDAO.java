package Interfaces;

import java.sql.SQLException;
import java.util.List;
import Clases.Subjects;

public interface SubjectsDAO {

    // Método para crear una nueva asignatura en la base de datos
	boolean crear(Subjects subjects) throws SQLException;

    // Método para leer una asignatura de la base de datos dado su ID
	Subjects leer(int id) throws SQLException;

    // Método para actualizar una asignatura en la base de datos dado su ID y un nuevo objeto Subjects
	boolean actualizar(int id, Subjects subjects) throws SQLException;

    // Método para eliminar una asignatura de la base de datos dado su ID
	boolean eliminar(int id) throws SQLException;

    // Método para listar todas las asignaturas almacenadas en la base de datos
	List<Subjects> listar() throws SQLException;
}