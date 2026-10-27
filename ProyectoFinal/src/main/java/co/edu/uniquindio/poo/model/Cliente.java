package co.edu.uniquindio.poo.model;

public class Cliente {
    private String identificacion;
    private String nombre;
    private String email;
    private String telefono;
    private RangoCliente rango;
    private CanalNotificacion canalPreferido = CanalNotificacion.SMS;

    public Cliente (String identificacion, String nombre, String email, String telefono, RangoCliente rango){
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.rango = rango;
    }

    // Getters y Setters
    public CanalNotificacion getCanalPreferido() { return canalPreferido; }

    public void setCanalPreferido(CanalNotificacion canalPreferido) { this.canalPreferido = canalPreferido; }

    public RangoCliente getRango() {
        return rango;
    }

    public void setRango(RangoCliente rango) {
        this.rango = rango;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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