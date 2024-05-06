package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Clases.Conexion;
import Clases.Subjects;
import Interfaces.SubjectsDAO;

public class SubjectsDAOImp extends Conexion implements SubjectsDAO {

	private PreparedStatement sentencia;
	boolean estadoOP;

	// Método para crear una nueva materia en la base de datos
	@Override
	public boolean crear(Subjects subjects) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Subjects (name, description, level) VALUES (?, ?, ?)";
			sentencia = connection.prepareStatement(sql);
			sentencia.setString(1, subjects.getName());
			sentencia.setString(2, subjects.getDescription());
			sentencia.setString(3, subjects.getLevel());

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

	// Método para leer una materia de la base de datos dado su ID
	@Override
	public Subjects leer(int id) throws SQLException {
		ResultSet resultados = null;
		Subjects subjects = new Subjects();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			sql = "SELECT * FROM Subjects WHERE id = ?";
			sentencia = connection.prepareStatement(sql);
			sentencia.setInt(1, id);

			resultados = sentencia.executeQuery();

			if (resultados.next()) {
				connection.setAutoCommit(false);

				subjects.setId(resultados.getInt(1));
				subjects.setName(resultados.getString(2));
				subjects.setDescription(resultados.getString(3));
				subjects.setLevel(resultados.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

	// Método para actualizar un estudiante en la base de datos dado su ID y un nuevo objeto Subject
	@Override
	public boolean actualizar(int id, Subjects subjects) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE Subjects SET name=?, description=?, level=? WHERE id = ?";
			sentencia = connection.prepareStatement(sql);

			sentencia.setString(1, subjects.getName());
			sentencia.setString(2, subjects.getDescription());
			sentencia.setString(3, subjects.getLevel());
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

	// Método para eliminar una materia de la base de datos dado su ID
	@Override
	public boolean eliminar(int id) throws SQLException {
		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM Subjects WHERE id = ?";
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

	// Método para listar todas las materias almacenadas en la base de datos
	@Override
	public List<Subjects> listar() throws SQLException {
		ResultSet resultado = null;
		List<Subjects> listaSubjects = new ArrayList<>();

		String sql = null;
		estadoOP = false;
		connection = getConnection();

		try {
			connection.setAutoCommit(false);
			sql = "SELECT * FROM Subjects";

			sentencia = connection.prepareStatement(sql);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Subjects subjects = new Subjects();

				subjects.setId(resultado.getInt(1));
				subjects.setName(resultado.getString(2));
				subjects.setDescription(resultado.getString(3));
				subjects.setLevel(resultado.getString(4));
				listaSubjects.add(subjects);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaSubjects;
	}

}
