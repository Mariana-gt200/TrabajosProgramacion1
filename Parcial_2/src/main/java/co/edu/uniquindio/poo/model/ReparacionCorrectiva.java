package co.edu.uniquindio.poo.model;

public class ReparacionCorrectiva extends Servicio {
    private double costoManoObraPorHora = 45000.0;
    private double horasTrabajo;
    private List<RepuestoUtilizado> repuestosUtilizados;

    public ReparacionCorrectiva(double horasTrabajo) {
        super("SRV-03", "Reparación Correctiva", "Solución de fallas específicas", 0.0);
        this.horasTrabajo = horasTrabajo;
        this.repuestosUtilizados = new ArrayList<>();
    }

    public void agregarRepuesto(Repuesto repuesto, int cantidad) {
        RepuestoUtilizado ru = new RepuestoUtilizado(repuesto, cantidad);
        this.repuestosUtilizados.add(ru);
    }

    public double calcularCostoRepuestos() {
        double totalRepuestos = 0;
        for (RepuestoUtilizado ru : repuestosUtilizados) {
            totalRepuestos += ru.calcularSubtotal();
        }
        return totalRepuestos;
    }

    @Override
    public double calcularCosto() {
        double costoManoObra = this.horasTrabajo * this.costoManoObraPorHora;
        return costoManoObra + calcularCostoRepuestos();
    }

    public double getCostoManoObraPorHora() {
        return costoManoObraPorHora;
    }

    public void setCostoManoObraPorHora(double costoManoObraPorHora) {
        this.costoManoObraPorHora = costoManoObraPorHora;
    }

    public double getHorasTrabajo() {
        return horasTrabajo;
    }

    public void setHorasTrabajo(double horasTrabajo) {
        this.horasTrabajo = horasTrabajo;
    }

    public List<RepuestoUtilizado> getRepuestosUtilizados() {
        return repuestosUtilizados;
    }

    public void setRepuestosUtilizados(List<RepuestoUtilizado> repuestosUtilizados) {
        this.repuestosUtilizados = repuestosUtilizados;
    }
}

