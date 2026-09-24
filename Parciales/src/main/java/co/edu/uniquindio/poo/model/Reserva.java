package co.edu.uniquindio.poo.model;

public class Reserva {
    private int codigo;
    private String fecha;
    private byte numeroNoches;
    private byte numeroHuespedes;
    private String estadoReserva;
    private String metodoPago;
    private short pagoTotal;
    private byte cantidadHabitaciones;

    private Hotel ownedByHotel;

    //Constructor
    public Reserva(int codigo, String fecha, byte numeroNoches, byte numeroHuespedes,
                   byte cantidadHabitaciones, String estadoReserva, String metodoPago, short pagoTotal, Hotel ownedByHotel) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.numeroNoches = numeroNoches;
        this.numeroHuespedes = numeroHuespedes;
        this.estadoReserva = estadoReserva;
        this.metodoPago = metodoPago;
        this.pagoTotal = pagoTotal;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ownedByHotel = ownedByHotel;

    }

    //Get y Set
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public byte getNumeroNoches() {
        return numeroNoches;
    }
    public void setNumeroNoches(byte numeroNoches) {
        this.numeroNoches = numeroNoches;
    }
    public byte getNumeroHuespedes() {
        return numeroHuespedes;
    }
    public void setNumeroHuespedes(byte numeroHuespedes) {
        this.numeroHuespedes = numeroHuespedes;
    }
    public String getEstadoReserva() {
        return estadoReserva;
    }
    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public short getPagoTotal() {
        return pagoTotal;
    }
    public void setPagoTotal(short pagoTotal) {
        this.pagoTotal = pagoTotal;
    }
    public byte getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }
    public void setCantidadHabitaciones(byte cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
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
        return "Reserva{" +
                "codigo=" + codigo +
                ", fecha='" + fecha + '\'' +
                ", numeroNoches=" + numeroNoches +
                ", numeroHuespedes=" + numeroHuespedes +
                ", estadoReserva='" + estadoReserva + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", pagoTotal=" + pagoTotal +
                ", ownedByHotel=" + ownedByHotel +
                '}';
    }
}