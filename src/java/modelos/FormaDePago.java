public class FormaDePago {
    private int idFormaPago;
    private String nombreFormaPago;

  
    public FormaDePago(int idFormaPago, String nombreFormaPago) {
        this.idFormaPago = idFormaPago;
        this.nombreFormaPago = nombreFormaPago;
    }

    // Getters y Setters
    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombreFormaPago() {
        return nombreFormaPago;
    }

    public void setNombreFormaPago(String nombreFormaPago) {
        this.nombreFormaPago = nombreFormaPago;
    }
}
