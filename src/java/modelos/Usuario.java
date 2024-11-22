package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Usuario {
    private int idUsuario;
    private String correo;
    private String contraseña;
    private int rolId;

    public Usuario(int idUsuario, String correo, String contraseña, int rolId) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rolId = rolId;
    }

    public int getIdUsuario() { return idUsuario; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }
    public int getRolId() { return rolId; }

    public static ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String query = "SELECT * FROM Usuarios";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("correo"),
                    rs.getString("contraseña"),
                    rs.getInt("rol_id")
                );
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Usuario buscarUsuario(int id) {
        String query = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("correo"),
                    rs.getString("contraseña"),
                    rs.getInt("rol_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarUsuario(String correo, String contraseña, int rolId) {
        String query = "INSERT INTO Usuarios (correo, contraseña, rol_id) VALUES (?, ?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, correo);
            ps.setString(2, contraseña);
            ps.setInt(3, rolId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarUsuario(int idUsuario, String correo, String contraseña, int rolId) {
        String query = "UPDATE Usuarios SET correo = ?, contraseña = ?, rol_id = ? WHERE id_usuario = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, correo);
            ps.setString(2, contraseña);
            ps.setInt(3, rolId);
            ps.setInt(4, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean eliminarUsuario(int idUsuario) {
        String query = "DELETE FROM Usuarios WHERE id_usuario = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }
}
