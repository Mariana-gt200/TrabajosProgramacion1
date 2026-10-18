package co.edu.uniquindio.poo.model;

public class Diagnostico extends Servicio {
    public Diagnostico() {
        super("SRV-01", "Diagnóstico", "Identificación de fallas del equipo", 35000.0);
    }

    @Override
    public double calcularCosto(DetalleServicio detalle) {
        return this.tarifaBase; // Costo fijo de $35.000
    }
}
