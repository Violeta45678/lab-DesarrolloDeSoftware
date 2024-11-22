package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Rol {
    private int idRol;
    private String nombreRol;
    private String descripcion;

    public Rol(int idRol, String nombreRol, String descripcion) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
    }

    public int getIdRol() { return idRol; }
    public String getNombreRol() { return nombreRol; }
    public String getDescripcion() { return descripcion; }

    // Método para listar todos los roles
    public static ArrayList<Rol> listarRoles() {
        ArrayList<Rol> lista = new ArrayList<>();
        String query = "SELECT * FROM Roles";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Rol rol = new Rol(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol"),
                    rs.getString("descripcion")
                );
                lista.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    // Método para buscar un rol por su id
    public static Rol buscarRol(int id) {
        String query = "SELECT * FROM Roles WHERE id_rol = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Rol(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    // Método para insertar un nuevo rol
    public static boolean insertarRol(String nombreRol, String descripcion) {
        String query = "INSERT INTO Roles (nombre_rol, descripcion) VALUES (?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreRol);
            ps.setString(2, descripcion);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    // Método para actualizar un rol
    public static boolean actualizarRol(int idRol, String nombreRol, String descripcion) {
        String query = "UPDATE Roles SET nombre_rol = ?, descripcion = ? WHERE id_rol = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreRol);
            ps.setString(2, descripcion);
            ps.setInt(3, idRol);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    // Método para eliminar un rol
    public static boolean eliminarRol(int idRol) {
        String query = "DELETE FROM Roles WHERE id_rol = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idRol);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }
}
