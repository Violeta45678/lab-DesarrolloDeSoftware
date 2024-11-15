package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Cliente {

    
    private int idCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private int usuarioId;

    public Cliente(int idCliente, String nombre, String apellido, String direccion, int usuarioId) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.usuarioId = usuarioId;
    }

    public int getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDireccion() { return direccion; }
    public int getUsuarioId() { return usuarioId; }

    // Método para listar los clientes
    public static ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM Clientes";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getInt("usuario_id")
                );
                lista.add(cliente);
            }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    conn.Desconectar();
                }
                return lista;
    }

    // Método para insertar un cliente
    public static boolean insertarCliente(String nombre, String apellido, String direccion, int usuarioId) {
    conexion conn = new conexion();
    Connection connection = conn.conectarMYSQL();
    boolean resultado = false;

    try {
        connection.setAutoCommit(false);  
        String queryCliente = "INSERT INTO Clientes (nombre, apellido, direccion, usuario_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement psCliente = connection.prepareStatement(queryCliente, Statement.RETURN_GENERATED_KEYS)) {
            psCliente.setString(1, nombre);
            psCliente.setString(2, apellido);
            psCliente.setString(3, direccion);
            psCliente.setInt(4, usuarioId);  
            psCliente.executeUpdate();

            
            ResultSet generatedKeys = psCliente.getGeneratedKeys();
            if (generatedKeys.next()) {
                int clienteId = generatedKeys.getInt(1); 
                resultado = true;  
            }
        }
        connection.commit();  
    } catch (SQLException e) {
        e.printStackTrace();
        try { 
            connection.rollback();  
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
        }
    } finally {
        conn.Desconectar();  
    }
    return resultado;
}

        // Método para buscar un cliente por ID
    public static Cliente buscarCliente(int idCliente) {
        Cliente cliente = null;
        String query = "SELECT * FROM Clientes WHERE id_cliente = ?"; 
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getInt("usuario_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }

        return cliente;
    }

    // Método para actualizar los datos del cliente
public static boolean actualizarCliente(int id, String nombre, String apellido, String direccion, int usuarioId) {
    conexion conn = new conexion();
    Connection connection = conn.conectarMYSQL();
    boolean resultado = false;

    try {
        connection.setAutoCommit(false); 
        String queryCliente = "UPDATE Clientes SET nombre = ?, apellido = ?, direccion = ? WHERE id_cliente = ?";
        try (PreparedStatement psCliente = connection.prepareStatement(queryCliente)) {
            psCliente.setString(1, nombre);
            psCliente.setString(2, apellido);
            psCliente.setString(3, direccion);
            psCliente.setInt(4, id);
            psCliente.executeUpdate();  
        }
        connection.commit();  
        resultado = true;
    } catch (SQLException e) {
        e.printStackTrace();
        try { 
            connection.rollback();  
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
        }
    } finally {
        conn.Desconectar();  
    }
    return resultado;
}

    // Método para eliminar un cliente
    public static boolean eliminarCliente(int id) {
        String query = "DELETE FROM Clientes WHERE id_cliente = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }
}
