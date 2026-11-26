package modelo;

public class Roommate {
    private String cedula;
    private String nombre;
    private String correo;
    private double presupuestoMaximo;
    private double totalPagado;
    private double totalConsumido;

    public Roommate(String cedula, String nombre, String correo, double presupuestoMaximo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.presupuestoMaximo = presupuestoMaximo;
        this.totalPagado = 0.0;
        this.totalConsumido = 0.0;
    }

    public double calcularSaldoNeto() {
        return Math.round((totalPagado - totalConsumido) * 100.0) / 100.0;
    }

    public String verificarSemaforo() {
        if (presupuestoMaximo <= 0) return "SIN PRESUPUESTO";
        double porcentaje = (totalConsumido / presupuestoMaximo) * 100.0;
        if (porcentaje < 75.0) {
            return String.format("VERDE (%.1f%% utilizado)", porcentaje);
        } else if (porcentaje < 100.0) {
            return String.format("AMARILLO - Precaución (%.1f%% utilizado)", porcentaje);
        } else {
            double exceso = totalConsumido - presupuestoMaximo;
            return String.format("ROJO - Sobregasto de $%.2f (%.1f%%)", exceso, porcentaje);
        }
    }

    public String mostrarInformacion() {
        return String.format("Cédula: %s | Nombre: %s | Presupuesto: $%.2f | Pagado: $%.2f | Consumido: $%.2f | Saldo: $%.2f",
                cedula, nombre, presupuestoMaximo, totalPagado, totalConsumido, calcularSaldoNeto());
    }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public double getPresupuestoMaximo() { return presupuestoMaximo; }
    public double getTotalPagado() { return totalPagado; }
    public void setTotalPagado(double totalPagado) { this.totalPagado = totalPagado; }
    public double getTotalConsumido() { return totalConsumido; }
    public void setTotalConsumido(double totalConsumido) { this.totalConsumido = totalConsumido; }
}