package modelo;

import java.util.ArrayList;

public class FondoComun {
    private String nombre;
    private double montoMeta;
    private double saldoDisponible;
    private ArrayList<Roommate> integrantes;

    public FondoComun(String nombre, double montoMeta) {
        this.nombre = nombre;
        this.montoMeta = montoMeta;
        this.saldoDisponible = 0.0;
        this.integrantes = new ArrayList<>();
    }

    public double calcularCuota(double porcentaje) {
        return Math.round((montoMeta * (porcentaje / 100.0)) * 100.0) / 100.0;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldoDisponible += monto;
        }
    }

    public boolean descontar(double monto) {
        if (monto > 0 && saldoDisponible >= monto) {
            saldoDisponible -= monto;
            return true;
        }
        return false;
    }

    public String getNombre() { return nombre; }
    public double getMontoMeta() { return montoMeta; }
    public double getSaldoDisponible() { return saldoDisponible; }
    public ArrayList<Roommate> getIntegrantes() { return integrantes; }
}