package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Conexion;
import Clases.Student;
import Interfaces.StudentDAO;

public class StudentDAOImp extends Conexion implements StudentDAO {

	private PreparedStatement sentencia;
	boolean estadoOP;

	// Método para crear un nuevo estudiante en la base de datos
	@Override
	public boolean crear(Student student) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Student (name, lastname, age) VALUES (?, ?, ?)";
			sentencia = connection.prepareStatement(sql);
			sentencia.setString(1, student.getName());
			sentencia.setString(2, student.getLastname());
			sentencia.setInt(3, student.getAge());

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

	// Método para leer un estudiante de la base de datos dado su ID
	@Override
	public Student leer(int id) throws SQLException {
		ResultSet resultados = null;
		Student student = new Student();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			sql = "SELECT * FROM Student WHERE id = ?";
			sentencia = connection.prepareStatement(sql);
			sentencia.setInt(1, id);

			resultados = sentencia.executeQuery();

			if (resultados.next()) {
				connection.setAutoCommit(false);

				student.setId(resultados.getInt(1));
				student.setName(resultados.getString(2));
				student.setLastname(resultados.getString(3));
				student.setAge(resultados.getInt(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	// Método para actualizar un estudiante en la base de datos dado su ID y un nuevo objeto Student
	@Override
	public boolean actualizar(int id, Student student) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE Student SET name=?, lastname=?, age=? WHERE id = ?";
			sentencia = connection.prepareStatement(sql);

			sentencia.setString(1, student.getName());
			sentencia.setString(2, student.getLastname());
			sentencia.setInt(3, student.getAge());
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

	// Método para eliminar un estudiante de la base de datos dado su ID
	@Override
	public boolean eliminar(int id) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM Student WHERE id = ?";
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

	// Método para listar todos los estudiantes almacenados en la base de datos
	@Override
	public List<Student> listar() throws SQLException {
		ResultSet resultado = null;
		List<Student> listaStudents = new ArrayList<>();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "SELECT * FROM Student";

			sentencia = connection.prepareStatement(sql);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Student student = new Student();

				student.setId(resultado.getInt(1));
				student.setName(resultado.getString(2));
				student.setLastname(resultado.getString(3));
				student.setAge(resultado.getInt(4));
				listaStudents.add(student);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaStudents;
	}

}
