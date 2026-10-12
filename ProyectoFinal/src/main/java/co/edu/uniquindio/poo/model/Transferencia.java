package co.edu.uniquindio.poo.model;

public class Transferencia {

    private Monedero monederoOrigen;
    private Monedero monederoDestino;
    private double comision;


    public Transferencia(Monedero monederoOrigen, Monedero monederoDestino, double comision) {
        super();
        this.monederoOrigen = monederoOrigen;
        this.monederoDestino = monederoDestino;
        this.comision = comision;
    }

    //Getter y setter

    public Monedero getMonederoOrigen() {
        return monederoOrigen;
    }

    public void setMonederoOrigen(Monedero monederoOrigen) {
        this.monederoOrigen = monederoOrigen;
    }

    public Monedero getMonederoDestino() {
        return monederoDestino;
    }

    public void setMonederoDestino(Monedero monederoDestino) {
        this.monederoDestino = monederoDestino;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }
}