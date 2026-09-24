package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Reserva {
    private int codigo;
    private String fecha;
    private byte numeroNoches;
    private byte numeroHuespedes;
    private String estadoReserva;
    private String metodoPago;
    private int pagoTotal;
    private byte cantidadHabitaciones;
    private Huesped huesped;
    private ArrayList<Habitacion> habitacionesReservadas;

    private Hotel ownedByHotel;

    //Constructor


    public Reserva(int codigo, String fecha, byte numeroNoches, byte numeroHuespedes, String estadoReserva, String metodoPago,
                   int pagoTotal, byte cantidadHabitaciones, Huesped huesped, ArrayList<Habitacion> habitacionesReservadas, Hotel ownedByHotel) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.numeroNoches = numeroNoches;
        this.numeroHuespedes = numeroHuespedes;
        this.estadoReserva = estadoReserva;
        this.metodoPago = metodoPago;
        this.pagoTotal = pagoTotal;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.huesped = huesped;
        this.habitacionesReservadas = habitacionesReservadas;
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
    public int getPagoTotal() {
        return pagoTotal;
    }
    public void setPagoTotal(int pagoTotal) {
        this.pagoTotal = pagoTotal;
    }
    public byte getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }
    public void setCantidadHabitaciones(byte cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }
    public Huesped getHuesped(){return huesped;}
    public void setHuesped(Huesped huesped){this.huesped= huesped;}
    public Hotel getOwnedByHotel() {
        return ownedByHotel;
    }
    public void setOwnedByHotel(Hotel ownedByHotel) {
        this.ownedByHotel = ownedByHotel;
    }
    public ArrayList<Habitacion> getHabitacionesReservadas() {
        return habitacionesReservadas;
    }
    public void agregarHabitacion(Habitacion habitacion) {
        habitacionesReservadas.add(habitacion);
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
                ", huesped=" + huesped+
                ", ownedByHotel=" + ownedByHotel +
                '}';
    }
}