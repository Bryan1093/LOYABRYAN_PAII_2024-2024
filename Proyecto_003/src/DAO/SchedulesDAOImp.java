package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import Clases.Conexion;
import Clases.Schedules;
import Interfaces.SchedulesDAO;

public class SchedulesDAOImp extends Conexion implements SchedulesDAO {

	private PreparedStatement sentencia;
	boolean estadoOP;

	// Método para crear un nuevo horario en la base de datos
	@Override
	public boolean crear(Schedules schedules) throws SQLException {

		String sql = null;
		estadoOP = false;
		connection = Conexion.getInstance().getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Schedules (Id, Id_sub, Id_stu, Id_tea, start_time, end_time, day) VALUES (?, ?, ?, ?, ?, ?, ?)";
			sentencia = connection.prepareStatement(sql);
			sentencia.setInt(1, schedules.getId());
			sentencia.setInt(2, schedules.getId_sub());
			sentencia.setInt(3, schedules.getId_stu());
			sentencia.setInt(4, schedules.getId_tea());
			sentencia.setTime(5, schedules.getStart_time());
			sentencia.setTime(6, schedules.getEnd_time());
			sentencia.setString(7, schedules.getDay());

			estadoOP = sentencia.executeUpdate() > 0;
			connection.commit();
			sentencia.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		return estadoOP;
	}

	// Método para leer un horario de la base de datos dado su ID
	@Override
	public Schedules leer(int id) throws SQLException {
		ResultSet resultados = null;
		Schedules schedules = new Schedules();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			sql = "SELECT * FROM Schedules WHERE id = ?";
			sentencia = connection.prepareStatement(sql);
			sentencia.setInt(1, id);

			resultados = sentencia.executeQuery();

			if (resultados.next()) {
				connection.setAutoCommit(false);

				schedules.setId(resultados.getInt(1));
				schedules.setId_sub(resultados.getInt(2));
				schedules.setId_stu(resultados.getInt(3));
				schedules.setId_tea(resultados.getInt(4));

				// Convertir los valores de tiempo a objetos Time
				Time startTime = resultados.getTime(5);
				Time endTime = resultados.getTime(6);

				schedules.setStart_time(startTime);
				schedules.setEnd_time(endTime);
				schedules.setDay(resultados.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return schedules;
	}

	// Método para actualizar un horario en la base de datos dado su ID y un nuevo
	// objeto Schedules
	@Override
	public boolean actualizar(int id, Schedules schedules) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE Schedules SET Id_sub=?, Id_stu=?, Id_tea=?, start_time=?, end_time=?, day=? WHERE id = ?";
			sentencia = connection.prepareStatement(sql);

			sentencia.setInt(1, schedules.getId());
			sentencia.setInt(2, schedules.getId_stu());
			sentencia.setInt(3, schedules.getId_tea());
			sentencia.setTime(4, schedules.getStart_time());
			sentencia.setTime(5, schedules.getEnd_time());
			sentencia.setString(6, schedules.getDay());
			sentencia.setInt(7, id);

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		estadoOP = sentencia.executeUpdate() > 0;
		connection.commit();
		sentencia.close();
		connection.close();

		return true;
	}

	// Método para eliminar un horario de la base de datos dado su ID
	@Override
	public boolean eliminar(int id) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM Schedules WHERE id = ?";
			sentencia = connection.prepareStatement(sql);

			sentencia.setInt(1, id);
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		estadoOP = sentencia.executeUpdate() > 0;
		connection.commit();
		sentencia.close();
		connection.close();

		return estadoOP;
	}

	// Método para listar todos los horarios almacenados en la base de datos
	@Override
	public List<Schedules> listar() throws SQLException {
		ResultSet resultado = null;
		List<Schedules> listaSchedules = new ArrayList<>();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "SELECT * FROM Schedules";

			sentencia = connection.prepareStatement(sql);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Schedules schedules = new Schedules();

				schedules.setId(resultado.getInt(1));
				schedules.setId_sub(resultado.getInt(2));
				schedules.setId_stu(resultado.getInt(3));
				schedules.setId_tea(resultado.getInt(4));
				schedules.setStart_time(resultado.getTime(5));
				schedules.setEnd_time(resultado.getTime(6));
				schedules.setDay(resultado.getString(7));
				listaSchedules.add(schedules);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaSchedules;
	}

}
