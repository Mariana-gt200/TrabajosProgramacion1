package co.edu.uniquindio.poo.model;

public class Deposito {

    private Monedero monederoDestino;

    //Constructor
    public Deposito(Monedero monederoDestino) {
        super();
        this.monederoDestino = monederoDestino;
    }

    //Get y set
    public Monedero getMonederoDestino() {
        return monederoDestino;
    }

    public void setMonederoDestino(Monedero monederoDestino) {
        this.monederoDestino = monederoDestino;
    }

}

