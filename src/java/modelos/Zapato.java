package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Zapato {
    private int idZapato;
    private String nombre;
    private double precio;
    private int stock;
    private int categoriaId;

    public Zapato(int idZapato, String nombre, double precio, int stock, int categoriaId) {
        this.idZapato = idZapato;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoriaId = categoriaId;
    }

    public int getIdZapato() { return idZapato; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getCategoriaId() { return categoriaId; }

    public static ArrayList<Zapato> listarZapatos() {
        ArrayList<Zapato> lista = new ArrayList<>();
        String query = "SELECT * FROM Zapatos";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Zapato zapato = new Zapato(
                    rs.getInt("id_zapato"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("categoria_id")
                );
                lista.add(zapato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Zapato buscarZapato(int id) {
        String query = "SELECT * FROM Zapatos WHERE id_zapato = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Zapato(
                    rs.getInt("id_zapato"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("categoria_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarZapato(String nombre, double precio, int stock, int categoriaId) {
        String query = "INSERT INTO Zapatos (nombre, precio, stock, categoria_id) VALUES (?, ?, ?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, categoriaId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarZapato(int idZapato, String nombre, double precio, int stock, int categoriaId) {
        String query = "UPDATE Zapatos SET nombre = ?, precio = ?, stock = ?, categoria_id = ? WHERE id_zapato = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, categoriaId);
            ps.setInt(5, idZapato);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean eliminarZapato(int idZapato) {
        String query = "DELETE FROM Zapatos WHERE id_zapato = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idZapato);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }
}
