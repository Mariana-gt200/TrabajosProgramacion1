package co.edu.uniquindio.poo.model;

public class Habitacion {
    private byte numero;
    private String tipo; //Individual, doble o suite
    private byte piso;
    private byte capacidad;
    private int precio;
    private String estado; //Disponible, ocupada o reservada

    //Constructor
    public Habitacion(byte numero, String tipo, byte piso, byte capacidad, int precio, String estado){
        this.numero= numero;
        this.tipo= tipo;
        this.piso= piso;
        this.capacidad= capacidad;
        this.precio= precio;
        this.estado= estado;
    }

    //Get y Set
    public byte getNumero() {
        return numero;
    }

    public void setNumero(byte numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte getPiso() {
        return piso;
    }

    public void setPiso(byte piso) {
        this.piso = piso;
    }

    public byte getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(byte capacidad) {
        this.capacidad = capacidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
