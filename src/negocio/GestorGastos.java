package negocio;

import modelo.FondoComun;
import modelo.Gasto;
import modelo.Roommate;
import java.util.ArrayList;

public class GestorGastos {
    private ArrayList<String> categorias = new ArrayList<>();
    private ArrayList<Gasto> gastos = new ArrayList<>();
    private int contadorId = 1;

    public GestorGastos() {
        categorias.add("Comida");
        categorias.add("Arriendo");
        categorias.add("Servicios Básicos");
        categorias.add("Limpieza");
    }

    public boolean agregarCategoria(String nombre) {
        for (String c : categorias) {
            if (c.equalsIgnoreCase(nombre)) return false;
        }
        categorias.add(nombre);
        return true;
    }

    public Gasto registrarGasto(String desc, double monto, String fecha, String categoria,
                                Roommate pagador, FondoComun fondo, ArrayList<Roommate> beneficiarios) {
        if (monto <= 0 || beneficiarios.isEmpty()) return null;

        if (fondo != null) {
            if (!fondo.descontar(monto)) {
                return null;
            }
        }

        Gasto nuevo = new Gasto(contadorId++, desc, monto, fecha, categoria, pagador);
        for (Roommate b : beneficiarios) {
            nuevo.agregarBeneficiario(b);
        }
        nuevo.aplicarBalance();
        gastos.add(nuevo);
        return nuevo;
    }

    public ArrayList<String> getCategorias() { return categorias; }
    public ArrayList<Gasto> getGastos() { return gastos; }
}