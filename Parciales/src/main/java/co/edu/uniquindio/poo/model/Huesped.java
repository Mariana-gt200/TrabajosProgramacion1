package co.edu.uniquindio.poo.model;

public class Huesped {
    private String documento;
    private String nombre;
    private byte edad;
    private String telefono;
    private String ciudadProcedencia;


    //Constructor
    public Huesped(String documento, String nombre, byte edad, String telefono, String ciudadProsedencia) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudadProcedencia = ciudadProsedencia;
    }

    //Get y Set
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public byte getEdad() {
        return edad;
    }
    public void setEdad(byte edad) {
        this.edad = edad;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCiudadProsedencia() {
        return ciudadProcedencia;
    }
    public void setCiudadProsedencia(String ciudadProsedencia) {
        this.ciudadProcedencia = ciudadProsedencia;
    }

    //toString
    @Override
    public String toString() {
        return "Huesped{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", ciudadProsedencia='" + ciudadProcedencia + '\'' +
                '}';
    }

}
