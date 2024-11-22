package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Categoria {
    private int idCategoria;
    private String nombreCategoria;

    public Categoria(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public static ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String query = "SELECT * FROM Categorias";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre_categoria")
                );
                lista.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Categoria buscarCategoria(int id) {
        String query = "SELECT * FROM Categorias WHERE id_categoria = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre_categoria")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarCategoria(String nombreCategoria) {
        String query = "INSERT INTO Categorias (nombre_categoria) VALUES (?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreCategoria);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return false;
    }

    public static boolean actualizarCategoria(int id, String nombreCategoria) {
        String query = "UPDATE Categorias SET nombre_categoria = ? WHERE id_categoria = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreCategoria);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return false;
    }

    public static boolean eliminarCategoria(int id) {
        String query = "DELETE FROM Categorias WHERE id_categoria = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return false;
    }
}
