package modelo;

import java.util.ArrayList;

public class Gasto extends Movimiento {
    private String categoria;
    private Roommate pagador;
    private ArrayList<Roommate> beneficiarios;

    public Gasto(int id, String descripcion, double monto, String fecha, String categoria, Roommate pagador) {
        super(id, descripcion, monto, fecha);
        this.categoria = categoria;
        this.pagador = pagador;
        this.beneficiarios = new ArrayList<>();
    }

    public void agregarBeneficiario(Roommate r) {
        if (r != null && !beneficiarios.contains(r)) {
            beneficiarios.add(r);
        }
    }

    public double calcularPorPersona() {
        if (beneficiarios.isEmpty()) return 0.0;
        return monto / beneficiarios.size();
    }

    @Override
    public void aplicarBalance() {
        if (beneficiarios.isEmpty()) return;
        if (pagador != null) {
            pagador.setTotalPagado(pagador.getTotalPagado() + monto);
        }
        double cuota = calcularPorPersona();
        for (Roommate b : beneficiarios) {
            b.setTotalConsumido(b.getTotalConsumido() + cuota);
        }
    }

    @Override
    public String mostrarInformacion() {
        String nombrePagador = (pagador != null) ? pagador.getNombre() : "Fondo Común";
        return super.mostrarInformacion() + String.format(" | Cat: %s | Pagó: %s | Beneficiarios: %d ($%.2f c/u)",
                categoria, nombrePagador, beneficiarios.size(), calcularPorPersona());
    }
}