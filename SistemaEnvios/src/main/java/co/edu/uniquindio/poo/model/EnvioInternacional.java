package co.edu.uniquindio.poo.model;

public class EnvioInternacional extends Envio{
    private String paisDestino;
    private boolean requiereSeguro;

    public EnvioInternacional(byte codigo, String destinatario, double peso, String paisDestino, boolean requiereSeguro) {
        super(codigo, destinatario, peso);
        this.paisDestino = paisDestino;
        this.requiereSeguro = requiereSeguro;
    }

    @Override
    public double calcularCosto(){
        double costoBase = 15000; // Costo base para envíos internacionales
        double costoPeso = getPeso() * 9000; // Costo adicional por peso
        double costoSeguro = 0;
        if(requiereSeguro == true){
            costoSeguro= 20000;
        }
        return costoBase + costoPeso + costoSeguro;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public boolean isRequiereSeguro() {
        return requiereSeguro;
    }

    public void setRequiereSeguro(boolean requiereSeguro) {
        this.requiereSeguro = requiereSeguro;
    }
}
