package co.edu.uniquindio.poo.model;

public class MantenimientoPreventivo extends Servicio {
    public static final double TARIFA_PORTATIL = 60000;
    public static final double TARIFA_ESCRITORIO = 70000;
    public static final double TARIFA_TELEFONO = 40000;

    public MantenimientoPreventivo() {
        // La tarifa base es 0 porque el valor se define según la categoría del equipo
        super("SRV-02", "Mantenimiento preventivo", "Limpieza, revisión y optimización", 0);
    }

    public double calcularTarifaParaEquipo(Dispositivo dispositivo) {
        if (dispositivo instanceof ComputadorPortatil) {
            return TARIFA_PORTATIL;
        } else if (dispositivo instanceof ComputadorEscritorio) {
            return TARIFA_ESCRITORIO;
        } else if (dispositivo instanceof TelefonoInteligente) {
            return TARIFA_TELEFONO;
        }
        throw new IllegalStateException("Categoría de equipo no reconocida.");
    }

    @Override
    public double calcularCosto(DetalleServicio detalle) {
        return calcularTarifaParaEquipo(detalle.getEquipo());  }
}
