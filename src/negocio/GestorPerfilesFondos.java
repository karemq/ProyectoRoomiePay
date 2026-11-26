package negocio;

import modelo.FondoComun;
import modelo.Roommate;
import java.util.ArrayList;

public class GestorPerfilesFondos {
    private ArrayList<Roommate> roommates = new ArrayList<>();
    private ArrayList<FondoComun> fondos = new ArrayList<>();

    public boolean registrarRoommate(String cedula, String nombre, String correo, double presupuesto) {
        if (presupuesto <= 0) return false;
        for (Roommate r : roommates) {
            if (r.getCedula().equals(cedula) || r.getCorreo().equalsIgnoreCase(correo)) {
                return false;
            }
        }
        roommates.add(new Roommate(cedula, nombre, correo, presupuesto));
        return true;
    }

    public Roommate buscarPorCedula(String cedula) {
        for (Roommate r : roommates) {
            if (r.getCedula().equals(cedula)) return r;
        }
        return null;
    }

    public FondoComun crearFondoComun(String nombre, double meta, ArrayList<Double> porcentajes) {
        double suma = 0;
        for (double p : porcentajes) suma += p;
        if (Math.abs(suma - 100.0) > 0.01 || meta <= 0) {
            return null;
        }
        FondoComun fondo = new FondoComun(nombre, meta);
        for (int i = 0; i < roommates.size() && i < porcentajes.size(); i++) {
            Roommate r = roommates.get(i);
            fondo.getIntegrantes().add(r);
            double cuota = fondo.calcularCuota(porcentajes.get(i));
            fondo.depositar(cuota);
        }
        fondos.add(fondo);
        return fondo;
    }

    public ArrayList<Roommate> getRoommates() { return roommates; }
    public ArrayList<FondoComun> getFondos() { return fondos; }
}