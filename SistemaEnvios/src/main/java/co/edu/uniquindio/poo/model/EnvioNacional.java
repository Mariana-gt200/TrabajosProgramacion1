package co.edu.uniquindio.poo.model;

import java.util.ArrayList;


public class EnvioNacional extends Envio {
    private String ciudadDestino;
    private boolean requiereDomicilio;

    public EnvioNacional(byte codigo, String destinatario, double peso, String ciudadDestino, boolean requiereDomicilio) {
        super(codigo, destinatario, peso);
        this.ciudadDestino = ciudadDestino;
        this.requiereDomicilio = requiereDomicilio;
    }

    @Override
    public double calcularCosto() {
        double costoBase = 6000; // Costo base para envíos nacionales
        double costoPeso = getPeso() * 2000; // Costo adicional por peso
        double costoDomicilio = 0;
        if(requiereDomicilio == true){
            costoDomicilio= 3000;
        }
        return costoBase + costoPeso + costoDomicilio;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public boolean isRequiereDomicilio() {
        return requiereDomicilio;
    }

    public void setRequiereDomicilio(boolean requiereDomicilio) {
        this.requiereDomicilio = requiereDomicilio;
    }
}
