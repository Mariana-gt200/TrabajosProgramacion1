package co.edu.uniquindio.poo.parcial_3.model;

public abstract class Empleado extends Persona {
    private String codigo;
    private int minutosDisponibles;

    public abstract String getTipo();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getMinutosDisponibles() {
        return minutosDisponibles;
    }

    public void setMinutosDisponibles(int minutosDisponibles) {
        this.minutosDisponibles = minutosDisponibles;
    }

    public Empleado(String nombre, String documento, String codigo, int minutosDisponibles) {
        super(nombre, documento);
        this.codigo = codigo;
        this.minutosDisponibles = minutosDisponibles;
    }
    @Override
    public String toString() {
        return getNombre() + " (" + getCodigo() + ") - " + getTipo() + " - " + minutosDisponibles + " min";
    }
}