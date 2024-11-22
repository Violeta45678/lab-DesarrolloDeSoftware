package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String contacto;
    private String telefono;
    private String direccion;

    public Proveedor(int idProveedor, String nombreProveedor, String contacto, String telefono, String direccion) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getIdProveedor() { return idProveedor; }
    public String getNombreProveedor() { return nombreProveedor; }
    public String getContacto() { return contacto; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }

    public static ArrayList<Proveedor> listarProveedores() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        String query = "SELECT * FROM Proveedores";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre_proveedor"),
                    rs.getString("contacto"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                lista.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Proveedor buscarProveedor(int id) {
        String query = "SELECT * FROM Proveedores WHERE id_proveedor = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre_proveedor"),
                    rs.getString("contacto"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarProveedor(String nombreProveedor, String contacto, String telefono, String direccion) {
        String query = "INSERT INTO Proveedores (nombre_proveedor, contacto, telefono, direccion) VALUES (?, ?, ?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreProveedor);
            ps.setString(2, contacto);
            ps.setString(3, telefono);
            ps.setString(4, direccion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarProveedor(int idProveedor, String nombreProveedor, String contacto, String telefono, String direccion) {
        String query = "UPDATE Proveedores SET nombre_proveedor = ?, contacto = ?, telefono = ?, direccion = ? WHERE id_proveedor = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreProveedor);
            ps.setString(2, contacto);
            ps.setString(3, telefono);
            ps.setString(4, direccion);
            ps.setInt(5, idProveedor);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean eliminarProveedor(int idProveedor) {
        String query = "DELETE FROM Proveedores WHERE id_proveedor = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idProveedor);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }
}
