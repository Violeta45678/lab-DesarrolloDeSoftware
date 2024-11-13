package modelos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private int rolId;
    private String correo;
    private String contrasena;

    public Empleado(int idEmpleado, String nombre, String apellido, int rolId, String correo, String contrasena) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rolId = rolId;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getIdEmpleado() { return idEmpleado; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getRolId() { return rolId; }
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }

    public static ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String query = "SELECT e.*, u.correo FROM Empleados e LEFT JOIN Usuarios u ON e.id_empleado = u.empleado_id";
        conexion conn = new conexion();
        
        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("rol_id"),
                    rs.getString("correo"),
                    null
                );
                lista.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Empleado buscarEmpleado(int id) {
        String query = "SELECT e.*, u.correo, u.contraseña FROM Empleados e LEFT JOIN Usuarios u ON e.id_empleado = u.empleado_id WHERE e.id_empleado = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("rol_id"),
                    rs.getString("correo"),
                    rs.getString("contraseña")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarEmpleado(String nombre, String apellido, int rolId, String correo, String contrasena) {
        conexion conn = new conexion();
        Connection connection = conn.conectarMYSQL();
        boolean resultado = false;

        try {
            connection.setAutoCommit(false);
            String queryEmpleado = "INSERT INTO Empleados (nombre, apellido, rol_id) VALUES (?, ?, ?)";
            try (PreparedStatement psEmpleado = connection.prepareStatement(queryEmpleado, Statement.RETURN_GENERATED_KEYS)) {
                psEmpleado.setString(1, nombre);
                psEmpleado.setString(2, apellido);
                psEmpleado.setInt(3, rolId);
                psEmpleado.executeUpdate();

                ResultSet generatedKeys = psEmpleado.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int empleadoId = generatedKeys.getInt(1);

                    String queryUsuario = "INSERT INTO Usuarios (correo, contraseña, rol_id, empleado_id) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement psUsuario = connection.prepareStatement(queryUsuario)) {
                        psUsuario.setString(1, correo);
                        psUsuario.setString(2, hashPassword(contrasena));
                        psUsuario.setInt(3, rolId);
                        psUsuario.setInt(4, empleadoId);
                        psUsuario.executeUpdate();
                        resultado = true;
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            conn.Desconectar();
        }
        return resultado;
    }

    public static boolean actualizarEmpleado(int id, String nombre, String apellido, int rolId, String correo, String contrasena) {
        conexion conn = new conexion();
        Connection connection = conn.conectarMYSQL();
        boolean resultado = false;

        try {
            connection.setAutoCommit(false);
            String queryEmpleado = "UPDATE Empleados SET nombre = ?, apellido = ?, rol_id = ? WHERE id_empleado = ?";
            try (PreparedStatement psEmpleado = connection.prepareStatement(queryEmpleado)) {
                psEmpleado.setString(1, nombre);
                psEmpleado.setString(2, apellido);
                psEmpleado.setInt(3, rolId);
                psEmpleado.setInt(4, id);
                psEmpleado.executeUpdate();
            }

            if (contrasena != null && !contrasena.isEmpty()) {
                String queryUsuario = "UPDATE Usuarios SET correo = ?, contraseña = ? WHERE empleado_id = ?";
                try (PreparedStatement psUsuario = connection.prepareStatement(queryUsuario)) {
                    psUsuario.setString(1, correo);
                    psUsuario.setString(2, hashPassword(contrasena));
                    psUsuario.setInt(3, id);
                    psUsuario.executeUpdate();
                }
            } else {
                String queryUsuario = "UPDATE Usuarios SET correo = ? WHERE empleado_id = ?";
                try (PreparedStatement psUsuario = connection.prepareStatement(queryUsuario)) {
                    psUsuario.setString(1, correo);
                    psUsuario.setInt(2, id);
                    psUsuario.executeUpdate();
                }
            }
            connection.commit();
            resultado = true;

        } catch (SQLException e) {
            e.printStackTrace();
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            conn.Desconectar();
        }
        return resultado;
    }

    public static boolean eliminarEmpleado(int id) {
        String query = "DELETE FROM Empleados WHERE id_empleado = ?";
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
