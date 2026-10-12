package co.edu.uniquindio.poo.model;

public class Cliente {
    private String idCliente;
    private String nombre;
    private String email;
    private String telefono;
    private RangoCliente rango;

    public Cliente (String idCliente, String nombre, String email, String telefono, RangoCliente rango){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.rango = rango;
    }

    public RangoCliente getRango() {
        return rango;
    }

    public void setRango(RangoCliente rango) {
        this.rango = rango;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}