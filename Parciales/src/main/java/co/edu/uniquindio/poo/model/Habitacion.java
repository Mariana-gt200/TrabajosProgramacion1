package co.edu.uniquindio.poo.model;

public class Habitacion {
    private byte numero;
    private String tipo; //Individual, doble o suite
    private byte piso;
    private byte capacidad;
    private int precio;
    private String estado; //Disponible, ocupada o reservada
    private Hotel ownedByHotel;

    //Constructor
    public Habitacion(byte numero, String tipo, byte piso, byte capacidad, int precio,
                      String estado, Hotel ownedByHotel) {
        this.numero= numero;
        this.tipo= tipo;
        this.piso= piso;
        this.capacidad= capacidad;
        this.precio= precio;
        this.estado= estado;
        this.ownedByHotel= ownedByHotel;
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
    public Hotel getOwnedByHotel() {
        return ownedByHotel;
    }
    public void setOwnedByHotel(Hotel ownedByHotel) {
        this.ownedByHotel = ownedByHotel;
    }

    //toString
    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", piso=" + piso +
                ", capacidad=" + capacidad +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                ", ownedByHotel=" + ownedByHotel +
                '}';
    }
}
