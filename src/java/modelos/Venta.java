/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author macma
 */
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

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getZapatoId() {
        return zapatoId;
    }

    public void setZapatoId(int zapatoId) {
        this.zapatoId = zapatoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    
}