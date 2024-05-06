package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Instancia única de la conexión
    private static Conexion instance = null;
    private final String url = "jdbc:postgresql://localhost:5432/institute";
    private final String usuario = "postgres";
    private final String password = "1998";
    protected Connection connection = null;

    // Constructor público para permitir instanciación externa
    public Conexion() {
    }

    // Método estático para obtener la instancia única de la conexión
    public static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, usuario, password);
                connection.setAutoCommit(false); // Establecer autocommit a false al obtener la conexión
                System.out.println("Conexión realizada");
            } catch (ClassNotFoundException e) {
                System.out.println("Error al cargar el driver: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base: " + e.getMessage());
            }
        }
        return connection;
    }
}