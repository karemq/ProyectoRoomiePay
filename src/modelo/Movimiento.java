package modelo;

public abstract class Movimiento {
    protected int id;
    protected String descripcion;
    protected double monto;
    protected String fecha;

    public Movimiento(int id, String descripcion, double monto, String fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public abstract void aplicarBalance();

    public String mostrarInformacion() {
        return String.format("[#%d] %s | Monto: $%.2f | Fecha: %s", id, descripcion, monto, fecha);
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public double getMonto() { return monto; }
    public String getFecha() { return fecha; }
}