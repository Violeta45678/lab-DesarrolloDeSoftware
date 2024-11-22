package modelos;

import java.sql.*;
import java.util.ArrayList;

public class FormaDePago {
    private int idFormaPago;
    private String nombreFormaPago;

    public FormaDePago(int idFormaPago, String nombreFormaPago) {
        this.idFormaPago = idFormaPago;
        this.nombreFormaPago = nombreFormaPago;
    }

    public int getIdFormaPago() { return idFormaPago; }
    public String getNombreFormaPago() { return nombreFormaPago; }

    public static ArrayList<FormaDePago> listarFormasDePago() {
        ArrayList<FormaDePago> lista = new ArrayList<>();
        String query = "SELECT * FROM FormasDePago";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FormaDePago formaDePago = new FormaDePago(
                    rs.getInt("id_forma_pago"),
                    rs.getString("nombre_forma_pago")
                );
                lista.add(formaDePago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static FormaDePago buscarFormaDePago(int id) {
        String query = "SELECT * FROM FormasDePago WHERE id_forma_pago = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new FormaDePago(
                    rs.getInt("id_forma_pago"),
                    rs.getString("nombre_forma_pago")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarFormaDePago(String nombreFormaPago) {
        String query = "INSERT INTO FormasDePago (nombre_forma_pago) VALUES (?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreFormaPago);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarFormaDePago(int id, String nombreFormaPago) {
        String query = "UPDATE FormasDePago SET nombre_forma_pago = ? WHERE id_forma_pago = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreFormaPago);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean eliminarFormaDePago(int id) {
        String query = "DELETE FROM FormasDePago WHERE id_forma_pago = ?";
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
