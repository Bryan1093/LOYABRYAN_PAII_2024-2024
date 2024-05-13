package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Conexion;
import Clases.Teacher;
import Interfaces.TeacherDAO;

public class TeacherDAOImp extends Conexion implements TeacherDAO {

	private PreparedStatement sentencia;
	boolean estadoOP;

	// Método para crear un nuevo profesor en la base de datos
	@Override
	public boolean crear(Teacher teacher) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Teacher (name, lastname, age) VALUES (?, ?, ?)";
			sentencia = connection.prepareStatement(sql);
			sentencia.setString(1, teacher.getName());
			sentencia.setString(2, teacher.getLastname());
			sentencia.setInt(3, teacher.getAge());

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
	public Teacher leer(int id) throws SQLException {
		ResultSet resultados = null;
		Teacher teacher = new Teacher();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			sql = "SELECT * FROM Teacher WHERE id = ?";
			sentencia = connection.prepareStatement(sql);
			sentencia.setInt(1, id);

			resultados = sentencia.executeQuery();

			if (resultados.next()) {
				connection.setAutoCommit(false);

				teacher.setId(resultados.getInt(1));
				teacher.setName(resultados.getString(2));
				teacher.setLastname(resultados.getString(3));
				teacher.setAge(resultados.getInt(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}

	// Método para actualizar un profesor en la base de datos dado su ID y un nuevo objeto Teacher
	@Override
	public boolean actualizar(int id, Teacher teacher) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE Teacher SET name=?, lastname=?, age=? WHERE id = ?";
			sentencia = connection.prepareStatement(sql);

			sentencia.setString(1, teacher.getName());
			sentencia.setString(2, teacher.getLastname());
			sentencia.setInt(3, teacher.getAge());
			sentencia.setInt(4, id);

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

	// Método para eliminar un profesor de la base de datos dado su ID
	@Override
	public boolean eliminar(int id) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM Teacher WHERE id = ?";
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

	// Método para listar todos los profesores almacenados en la base de datos
	@Override
	public List<Teacher> listar() throws SQLException {
		ResultSet resultado = null;
		List<Teacher> listaTeachers = new ArrayList<>();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "SELECT * FROM Teacher";

			sentencia = connection.prepareStatement(sql);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Teacher teacher = new Teacher();

				teacher.setId(resultado.getInt(1));
				teacher.setName(resultado.getString(2));
				teacher.setLastname(resultado.getString(3));
				teacher.setAge(resultado.getInt(4));
				listaTeachers.add(teacher);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaTeachers;
	}

}
