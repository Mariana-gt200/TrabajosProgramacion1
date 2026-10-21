package co.edu.uniquindio.poo.model;

public class Repuesto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioUnitario;
    private int cantidadDisponible;
    private String categoriaCompatibilidad;

    public Repuesto(String codigo, String nombre, String descripcion, double precioUnitario, int cantidadDisponible, String categoriaCompatibilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidadDisponible = cantidadDisponible;
        this.categoriaCompatibilidad = categoriaCompatibilidad;
    }

    public boolean verificarDisponibilidad(int cantidadSolicitada) {
        return cantidadSolicitada > 0 && this.cantidadDisponible >= cantidadSolicitada;
    }

    // Repuesto.java
    public void descontarInventario(int cantidadADescontar) {
        if (verificarDisponibilidad(cantidadADescontar)) {
            this.cantidadDisponible -= cantidadADescontar;
        }
    }

    public boolean esCritico() {          // para la funcionalidad 18
        return cantidadDisponible < 5;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " - " + descripcion + " - Precio: " + precioUnitario
                + " - Disponibles: " + cantidadDisponible + " - Compatibilidad: " + categoriaCompatibilidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getCategoriaCompatibilidad() {
        return categoriaCompatibilidad;
    }

    public void setCategoriaCompatibilidad(String categoriaCompatibilidad) {
        this.categoriaCompatibilidad = categoriaCompatibilidad;
    }
}

