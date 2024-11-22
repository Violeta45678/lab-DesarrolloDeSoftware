package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Factura {
    private int idFactura;
    private Date fecha;
    private int clienteId;
    private int empleadoId;
    private double total;

    public Factura(int idFactura, Date fecha, int clienteId, int empleadoId, double total) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.total = total;
    }

    public int getIdFactura() { return idFactura; }
    public Date getFecha() { return fecha; }
    public int getClienteId() { return clienteId; }
    public int getEmpleadoId() { return empleadoId; }
    public double getTotal() { return total; }

    public static ArrayList<Factura> listarFacturas() {
        ArrayList<Factura> lista = new ArrayList<>();
        String query = "SELECT * FROM Facturas";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Factura factura = new Factura(
                    rs.getInt("id_factura"),
                    rs.getDate("fecha"),
                    rs.getInt("cliente_id"),
                    rs.getInt("empleado_id"),
                    rs.getDouble("total")
                );
                lista.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Factura buscarFactura(int id) {
        String query = "SELECT * FROM Facturas WHERE id_factura = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Factura(
                    rs.getInt("id_factura"),
                    rs.getDate("fecha"),
                    rs.getInt("cliente_id"),
                    rs.getInt("empleado_id"),
                    rs.getDouble("total")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarFactura(Date fecha, int clienteId, int empleadoId, double total) {
        String query = "INSERT INTO Facturas (fecha, cliente_id, empleado_id, total) VALUES (?, ?, ?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDate(1, new java.sql.Date(fecha.getTime()));
            ps.setInt(2, clienteId);
            ps.setInt(3, empleadoId);
            ps.setDouble(4, total);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarFactura(int id, Date fecha, int clienteId, int empleadoId, double total) {
        String query = "UPDATE Facturas SET fecha = ?, cliente_id = ?, empleado_id = ?, total = ? WHERE id_factura = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDate(1, new java.sql.Date(fecha.getTime()));
            ps.setInt(2, clienteId);
            ps.setInt(3, empleadoId);
            ps.setDouble(4, total);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean eliminarFactura(int id) {
        String query = "DELETE FROM Facturas WHERE id_factura = ?";
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
