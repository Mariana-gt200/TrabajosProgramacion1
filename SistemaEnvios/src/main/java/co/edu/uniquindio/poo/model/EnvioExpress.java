package co.edu.uniquindio.poo.model;

public class EnvioExpress extends EnvioNacional {
    private double plazoMaximoEntrega; // en horas

    public EnvioExpress(byte codigo, String destinatario, double peso, String ciudadDestino, boolean requiereDomicilio, double plazoMaximoEntrega) {
        super(codigo, destinatario, peso, ciudadDestino, requiereDomicilio);
        this.plazoMaximoEntrega = plazoMaximoEntrega;
    }

    @Override
    public double calcularCosto(){
        double costoTotal = (super.calcularCosto() + (super.calcularCosto() * 0.3));
        if(plazoMaximoEntrega <= 24){
            costoTotal += 5000;
        }
        return costoTotal;
    }

    public double getPlazoMaximoEntrega() {
        return plazoMaximoEntrega;
    }

    public void setPlazoMaximoEntrega(double plazoMaximoEntrega) {
        this.plazoMaximoEntrega = plazoMaximoEntrega;
    }
}
