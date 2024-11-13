package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    // Configuración de la base de datos
    String servidor = "localhost:3306"; 
    String baseDatos = "zapateriabd"; 
    String usuario = "prueba-user"; 
    String clave = "raul0034.";
    // URL de conexión con configuración adicional
    String url = "jdbc:mysql://" + servidor + "/" + baseDatos + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

    private Connection conexion = null;

    public Connection conectarMYSQL() {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conectado a la base de datos");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos:");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conexion; 
    }

    public void Desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
