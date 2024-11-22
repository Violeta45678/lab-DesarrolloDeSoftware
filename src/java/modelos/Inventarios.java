/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import java.util.Date;

/**
 *
 * @author macma
 */
public class Inventarios {
    private int idInventario;
    private int idZapato;
    private int idProveedor;
    private int stock;
    private Date fechaActualizacion;

    public Inventarios(int idInventario, int idZapato, int idProveedor, int stock, Date fechaActualizacion) {
        this.idInventario = idInventario;
        this.idZapato = idZapato;
        this.idProveedor = idProveedor;
        this.stock = stock;
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdZapato() {
        return idZapato;
    }

    public void setIdZapato(int idZapato) {
        this.idZapato = idZapato;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
}
