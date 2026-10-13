package co.edu.uniquindio.poo.model;

public class Monedero {

    private String idMonedero;
    private TipoMonedero tipo;
    private double saldo;
    private String identificacionCliente;

    //Constructor
    public Monedero(String idMonedero, TipoMonedero tipo, double saldo, String identificacionCliente) {
        this.idMonedero = idMonedero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.identificacionCliente = identificacionCliente;
    }

    // Getters y Setters
    public String getIdMonedero() {
        return idMonedero;
    }

    public void setIdMonedero(String idMonedero) {
        this.idMonedero = idMonedero;
    }

    public TipoMonedero getTipo() {
        return tipo;
    }

    public void setTipo(TipoMonedero tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

}
