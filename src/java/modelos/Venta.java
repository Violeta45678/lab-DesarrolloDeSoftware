package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Venta {
    private int idVenta;
    private int facturaId;
    private int zapatoId;
    private int cantidad;
    private double precioUnitario;

    public Venta(int idVenta, int facturaId, int zapatoId, int cantidad, double precioUnitario) {
        this.idVenta = idVenta;
        this.facturaId = facturaId;
        this.zapatoId = zapatoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdVenta() { return idVenta; }
    public int getFacturaId() { return facturaId; }
    public int getZapatoId() { return zapatoId; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }

    public static ArrayList<Venta> listarVentas() {
        ArrayList<Venta> lista = new ArrayList<>();
        String query = "SELECT * FROM Ventas";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta venta = new Venta(
                    rs.getInt("id_venta"),
                    rs.getInt("factura_id"),
                    rs.getInt("zapato_id"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_unitario")
                );
                lista.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Venta buscarVenta(int id) {
        String query = "SELECT * FROM Ventas WHERE id_venta = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Venta(
                    rs.getInt("id_venta"),
                    rs.getInt("factura_id"),
                    rs.getInt("zapato_id"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_unitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarVenta(int facturaId, int zapatoId, int cantidad, double precioUnitario) {
        String query = "INSERT INTO Ventas (factura_id, zapato_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, facturaId);
            ps.setInt(2, zapatoId);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precioUnitario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarVenta(int idVenta, int facturaId, int zapatoId, int cantidad, double precioUnitario) {
        String query = "UPDATE Ventas SET factura_id = ?, zapato_id = ?, cantidad = ?, precio_unitario = ? WHERE id_venta = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, facturaId);
            ps.setInt(2, zapatoId);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precioUnitario);
            ps.setInt(5, idVenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean eliminarVenta(int idVenta) {
        String query = "DELETE FROM Ventas WHERE id_venta = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idVenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }
}
