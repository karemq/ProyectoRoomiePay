package negocio;

import modelo.Roommate;
import modelo.Transferencia;
import java.util.ArrayList;

public class MotorBalances {

    public double verificarSumaCero(ArrayList<Roommate> roommates) {
        double suma = 0.0;
        for (Roommate r : roommates) {
            suma += r.calcularSaldoNeto();
        }
        return Math.round(suma * 100.0) / 100.0;
    }

    // Heurística voraz (Greedy) que empareja al mayor deudor con el mayor acreedor
    public ArrayList<Transferencia> generarPlanLiquidacion(ArrayList<Roommate> roommates) {
        ArrayList<Transferencia> plan = new ArrayList<>();
        ArrayList<Roommate> deudores = new ArrayList<>();
        ArrayList<Roommate> acreedores = new ArrayList<>();
        ArrayList<Double> saldosDeudores = new ArrayList<>();
        ArrayList<Double> saldosAcreedores = new ArrayList<>();

        for (Roommate r : roommates) {
            double s = r.calcularSaldoNeto();
            if (s < -0.01) {
                deudores.add(r);
                saldosDeudores.add(Math.abs(s));
            } else if (s > 0.01) {
                acreedores.add(r);
                saldosAcreedores.add(s);
            }
        }

        while (!deudores.isEmpty() && !acreedores.isEmpty()) {
            int idxMaxDeudor = 0;
            for (int i = 1; i < saldosDeudores.size(); i++) {
                if (saldosDeudores.get(i) > saldosDeudores.get(idxMaxDeudor)) idxMaxDeudor = i;
            }

            int idxMaxAcreedor = 0;
            for (int i = 1; i < saldosAcreedores.size(); i++) {
                if (saldosAcreedores.get(i) > saldosAcreedores.get(idxMaxAcreedor)) idxMaxAcreedor = i;
            }

            double deuda = saldosDeudores.get(idxMaxDeudor);
            double credito = saldosAcreedores.get(idxMaxAcreedor);
            double montoTransferir = Math.min(deuda, credito);

            plan.add(new Transferencia(deudores.get(idxMaxDeudor), acreedores.get(idxMaxAcreedor), montoTransferir));

            double nuevaDeuda = Math.round((deuda - montoTransferir) * 100.0) / 100.0;
            double nuevoCredito = Math.round((credito - montoTransferir) * 100.0) / 100.0;

            if (nuevaDeuda <= 0.01) {
                deudores.remove(idxMaxDeudor);
                saldosDeudores.remove(idxMaxDeudor);
            } else {
                saldosDeudores.set(idxMaxDeudor, nuevaDeuda);
            }

            if (nuevoCredito <= 0.01) {
                acreedores.remove(idxMaxAcreedor);
                saldosAcreedores.remove(idxMaxAcreedor);
            } else {
                saldosAcreedores.set(idxMaxAcreedor, nuevoCredito);
            }
        }
        return plan;
    }
}