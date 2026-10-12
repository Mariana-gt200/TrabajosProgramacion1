package co.edu.uniquindio.poo.model;

public class Retiro {

    private Monedero monederoOrigen;

    //Constructor
    public Retiro(String idTransaccion, java.util.Date fecha, double monto, TipoTransaccion tipo, EstadoTransaccion estado, boolean esAtipica, Monedero monederoOrigen) {
        super();
        this.monederoOrigen = monederoOrigen;
    }

    //Get y set
    public Monedero getMonederoOrigen() {
        return monederoOrigen;
    }

    public void setMonederoOrigen(Monedero monederoOrigen) {
        this.monederoOrigen = monederoOrigen;
    }
}