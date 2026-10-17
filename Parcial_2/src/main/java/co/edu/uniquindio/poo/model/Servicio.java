package co.edu.uniquindio.poo.model;

public abstract class Servicio {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double tarifaBase;

    public Servicio(String codigo, String nombre, String descripcion, double tarifaBase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaBase = tarifaBase;
    }

    // Metodo abstracto para calcular el costo del servicio según el detalle proporcionado
    public abstract double calcularCosto(DetalleServicio detalle);

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getTarifaBase() { return tarifaBase; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " - " + descripcion;
    }
}
