package co.edu.uniquindio.poo.model;
import java.util.HashMap;
import java.util.Map;

public abstract class Envio {
    private byte codigo;
    private String destinatario;
    private double peso;

    public Envio(byte codigo, String destinatario, double peso) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
    }

    public byte getCodigo() {
        return codigo;
    }

    public void setCodigo(byte codigo) {
        this.codigo = codigo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    //Metodo para ingresar los datos del envio
    public void ingresarDatosEnvio(byte codigo, String destinatario, double peso){
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
    }

    public abstract double calcularCosto();
}
