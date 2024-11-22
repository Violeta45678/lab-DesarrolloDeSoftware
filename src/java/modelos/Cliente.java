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

    public static Cliente buscarCliente(int id) {
        String query = "SELECT * FROM Clientes WHERE id_cliente = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getInt("usuario_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarCliente(String nombre, String apellido, String direccion, int usuarioId) {
        String query = "INSERT INTO Clientes (nombre, apellido, direccion, usuario_id) VALUES (?, ?, ?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, direccion);
            ps.setInt(4, usuarioId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarCliente(int id, String nombre, String apellido, String direccion, int usuarioId) {
        String query = "UPDATE Clientes SET nombre = ?, apellido = ?, direccion = ?, usuario_id = ? WHERE id_cliente = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, direccion);
            ps.setInt(4, usuarioId);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

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
