package co.edu.uniquindio.poo.model;

public class RepuestoUtilizado {
    private Repuesto repuesto;
    private int cantidadRequerida;
    private double precioUnitarioAplicado;

    public RepuestoUtilizado(Repuesto repuesto, int cantidadRequerida) {
        repuesto.descontarInventario(cantidadRequerida); // Descuenta automáticamente al instanciar
        this.repuesto = repuesto;
        this.cantidadRequerida = cantidadRequerida;
        this.precioUnitarioAplicado = repuesto.getPrecioUnitario();
    }

    public double calcularSubtotal() {
        return this.cantidadRequerida * this.precioUnitarioAplicado;
    }

    public Repuesto getRepuesto() { return repuesto; }
    public int getCantidadRequerida() { return cantidadRequerida; }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public void setCantidadRequerida(int cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public void setPrecioUnitarioAplicado(double precioUnitarioAplicado) {
        this.precioUnitarioAplicado = precioUnitarioAplicado;
    }

    public double getPrecioUnitarioAplicado() { return precioUnitarioAplicado; }
}
