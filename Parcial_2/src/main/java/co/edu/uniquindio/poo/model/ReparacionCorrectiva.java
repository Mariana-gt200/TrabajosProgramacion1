package co.edu.uniquindio.poo.model;

public class ReparacionCorrectiva extends Servicio {
    public static final double COSTO_MANO_OBRA_HORA = 45000;

    public ReparacionCorrectiva() {
        // La tarifa base es el valor de la hora de mano de obra
        super("SRV-03", "Reparación correctiva", "Solucionar fallas identificadas", COSTO_MANO_OBRA_HORA);
    }

    @Override
    public double calcularCosto(DetalleServicio detalle) {
        double manoDeObra = detalle.getHorasTrabajo() * tarifaBase;
        return manoDeObra + detalle.calcularCostoRepuestos();
    }
}

