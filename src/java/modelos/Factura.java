import java.util.Date;

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

    // Getters y Setters
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
