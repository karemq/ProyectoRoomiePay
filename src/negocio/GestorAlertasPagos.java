package negocio;

import modelo.Roommate;
import modelo.Transferencia;
import java.util.ArrayList;

public class GestorAlertasPagos {
    private ArrayList<Transferencia> transferencias = new ArrayList<>();

    // Carga el plan de liquidación generado en el Módulo 3
    public void cargarTransferencias(ArrayList<Transferencia> nuevas) {
        this.transferencias = nuevas;
    }

    // RF4.1: Evalúa el semáforo de presupuesto (Verde <75%, Amarillo 75-99%, Rojo >=100%)
    public ArrayList<String> obtenerReportesSemaforo(ArrayList<Roommate> roommates) {
        ArrayList<String> reportes = new ArrayList<>();
        for (Roommate r : roommates) {
            reportes.add(r.getNombre() + " -> " + r.verificarSemaforo());
        }
        return reportes;
    }

    // RF4.2: Flujo de estados (PENDIENTE -> EN_REVISION -> PAGADO)
    public boolean registrarComprobantePago(int indice, String comprobante) {
        if (indice < 0 || indice >= transferencias.size() || comprobante == null || comprobante.trim().isEmpty()) {
            return false;
        }
        Transferencia t = transferencias.get(indice);
        t.registrarComprobante(comprobante);
        return true;
    }

    public boolean aprobarTransferencia(int indice, String comprobante) {
        if (indice < 0 || indice >= transferencias.size()) {
            return false;
        }
        Transferencia t = transferencias.get(indice);
        if (t.getEstado().equals("PENDIENTE") && (comprobante == null || comprobante.trim().isEmpty())) {
            return false; // Exige comprobante antes de aprobar
        }
        t.confirmarPago(comprobante);
        return true;
    }

    // Verifica que todas las deudas estén en estado PAGADO para permitir cerrar el mes
    public boolean cerrarMes() {
        if (transferencias.isEmpty()) return true;
        for (Transferencia t : transferencias) {
            if (!t.getEstado().equals("PAGADO")) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Transferencia> getTransferencias() {
        return transferencias;
    }
}