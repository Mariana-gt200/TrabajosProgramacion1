package co.edu.uniquindio.poo.model;

public class Monedero {

    private String idMonedero;
    private TipoMonedero tipo;
    private double saldo;

    //Constructor
    public Monedero(String idMonedero, TipoMonedero tipo, double saldo) {
        this.idMonedero = idMonedero;
        this.tipo = tipo;
        this.saldo = saldo;
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

}
