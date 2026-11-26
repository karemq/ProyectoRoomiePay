package modelo;

public class Transferencia {
    private Roommate deudor;
    private Roommate acreedor;
    private double monto;
    private String estado;
    private String comprobante;

    public Transferencia(Roommate deudor, Roommate acreedor, double monto) {
        this.deudor = deudor;
        this.acreedor = acreedor;
        this.monto = Math.round(monto * 100.0) / 100.0;
        this.estado = "PENDIENTE";
        this.comprobante = "Sin comprobante";
    }

    public void registrarComprobante(String comprobante) {
        this.comprobante = comprobante;
        this.estado = "EN_REVISION";
    }

    public void confirmarPago(String comprobante) {
        if (comprobante != null && !comprobante.isEmpty()) {
            this.comprobante = comprobante;
        }
        this.estado = "PAGADO";
    }

    @Override
    public String toString() {
        return String.format("%s -> %s : $%.2f | Estado: %s | Comprobante: %s",
                deudor.getNombre(), acreedor.getNombre(), monto, estado, comprobante);
    }

    public Roommate getDeudor() { return deudor; }
    public Roommate getAcreedor() { return acreedor; }
    public double getMonto() { return monto; }
    public String getEstado() { return estado; }
}