package co.edu.uniquindio.poo.model;

public class Deposito extends Transaccion {

    private Monedero monederoDestino;

    //Constructor
    public Deposito(String idTransaccion, double monto, EstadoTransaccion estado, String idCliente, Monedero monederoDestino){
        super(idTransaccion, java.time.LocalDate.now(), monto, estado, idCliente);
        this.monederoDestino = monederoDestino;
    }

    @Override
    public String nombreTipo() {
        return "Depósito";
    }

    @Override
    public int calcularPuntosGenerados() {
        return (int) (getMonto() / 100) * 1;
    }

    private void revisarTransaccionAtipica(Deposito this, Cliente cliente) {
    }

    //Get y set
    public Monedero getMonederoDestino() {
        return monederoDestino;
    }

    public void setMonederoDestino(Monedero monederoDestino) {
        this.monederoDestino = monederoDestino;
    }



}

