/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author macma
 */
package modelos;

import java.sql.*;
import java.util.ArrayList;

public class Inventario {
    private int idInventario;
    private int idZapato;
    private int idProveedor;
    private int stock;
    private Date fechaActualizacion;

    public Inventario(int idInventario, int idZapato, int idProveedor, int stock, Date fechaActualizacion) {
        this.idInventario = idInventario;
        this.idZapato = idZapato;
        this.idProveedor = idProveedor;
        this.stock = stock;
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getIdInventario() { return idInventario; }
    public int getIdZapato() { return idZapato; }
    public int getIdProveedor() { return idProveedor; }
    public int getStock() { return stock; }
    public Date getFechaActualizacion() { return fechaActualizacion; }

    public static ArrayList<Inventario> listarInventarios() {
        ArrayList<Inventario> lista = new ArrayList<>();
        String query = "SELECT * FROM Inventarios";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inventario inventario = new Inventario(
                    rs.getInt("id_inventario"),
                    rs.getInt("id_zapato"),
                    rs.getInt("id_proveedor"),
                    rs.getInt("stock"),
                    rs.getDate("fecha_actualizacion")
                );
                lista.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return lista;
    }

    public static Inventario buscarInventario(int id) {
        String query = "SELECT * FROM Inventarios WHERE id_inventario = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Inventario(
                    rs.getInt("id_inventario"),
                    rs.getInt("id_zapato"),
                    rs.getInt("id_proveedor"),
                    rs.getInt("stock"),
                    rs.getDate("fecha_actualizacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.Desconectar();
        }
        return null;
    }

    public static boolean insertarInventario(int idZapato, int idProveedor, int stock, Date fechaActualizacion) {
        String query = "INSERT INTO Inventarios (id_zapato, id_proveedor, stock, fecha_actualizacion) VALUES (?, ?, ?, ?)";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idZapato);
            ps.setInt(2, idProveedor);
            ps.setInt(3, stock);
            ps.setDate(4, fechaActualizacion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean actualizarInventario(int idInventario, int idZapato, int idProveedor, int stock, Date fechaActualizacion) {
        String query = "UPDATE Inventarios SET id_zapato = ?, id_proveedor = ?, stock = ?, fecha_actualizacion = ? WHERE id_inventario = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idZapato);
            ps.setInt(2, idProveedor);
            ps.setInt(3, stock);
            ps.setDate(4, fechaActualizacion);
            ps.setInt(5, idInventario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }

    public static boolean eliminarInventario(int idInventario) {
        String query = "DELETE FROM Inventarios WHERE id_inventario = ?";
        conexion conn = new conexion();

        try (Connection connection = conn.conectarMYSQL();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idInventario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.Desconectar();
        }
    }
}
