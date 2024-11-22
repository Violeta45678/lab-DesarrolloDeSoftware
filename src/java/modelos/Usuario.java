package modelos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Usuario {
    private int idUsuario;
    private String correo;
    private String contraseña;
    private int rolId;
    private int empleadoId;

    public Usuario(int idUsuario, String correo, String contraseña, int rolId, int empleadoId) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rolId = rolId;
        this.empleadoId = empleadoId;
    }

    public int getIdUsuario() { return idUsuario; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }
    public int getRolId() { return rolId; }
    public int getEmpleadoId() { return empleadoId; }

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
                    rs.getInt("rol_id"),
                    rs.getInt("empleado_id")
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
                    rs.getInt("rol_id"),
                    rs.getInt("empleado_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarUsuario(String correo, String contrasena, int rolId, int empleadoId) {
        conexion conn = new conexion();
        Connection connection = conn.conectarMYSQL();
        boolean resultado = false;

        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Usuarios (correo, contraseña, rol_id, empleado_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, correo);
                ps.setString(2, hashPassword(contrasena));
                ps.setInt(3, rolId);
                ps.setInt(4, empleadoId);
                ps.executeUpdate();

                connection.commit();
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            conn.Desconectar();
        }
        return resultado;
    }

    public static boolean actualizarUsuario(int id, String correo, String contrasena, int rolId) {
        conexion conn = new conexion();
        Connection connection = conn.conectarMYSQL();
        boolean resultado = false;

        try {
            connection.setAutoCommit(false);
            String query = "UPDATE Usuarios SET correo = ?, rol_id = ? WHERE id_usuario = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, correo);
                ps.setInt(2, rolId);
                ps.setInt(3, id);
                ps.executeUpdate();

                if (contrasena != null && !contrasena.isEmpty()) {
                    String queryContrasena = "UPDATE Usuarios SET contraseña = ? WHERE id_usuario = ?";
                    try (PreparedStatement psContrasena = connection.prepareStatement(queryContrasena)) {
                        psContrasena.setString(1, hashPassword(contrasena));
                        psContrasena.setInt(2, id);
                        psContrasena.executeUpdate();
                    }
                }

                connection.commit();
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            conn.Desconectar();
        }
        return resultado;
    }

    public static boolean eliminarUsuario(int id) {
        String query = "DELETE FROM Usuarios WHERE id_usuario = ?";
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

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
}
