package Interfaces;

import java.sql.SQLException;
import java.util.List;
import Clases.Schedules;

public interface SchedulesDAO {

    // Método para crear un nuevo horario en la base de datos
	boolean crear(Schedules schedules) throws SQLException;

    // Método para leer un horario de la base de datos dado su ID
	Schedules leer(int id) throws SQLException;

    // Método para actualizar un horario en la base de datos dado su ID y un nuevo objeto Schedules
	boolean actualizar(int id, Schedules schedules) throws SQLException;

    // Método para eliminar un horario de la base de datos dado su ID
	boolean eliminar(int id) throws SQLException;

    // Método para listar todos los horarios almacenados en la base de datos
	List<Schedules> listar() throws SQLException;
}