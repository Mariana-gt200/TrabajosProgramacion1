package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String numeroDocumento;
    private String nombreCompleto;
    private String numeroTelefono;
    private String correoElectronico;
    private String direccionResidencia;
    private List<Dispositivo> equipos;   // 0..* equipos registrados

    public Cliente(String numeroDocumento, String nombreCompleto, String numeroTelefono,
                   String correoElectronico, String direccionResidencia) {
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        this.direccionResidencia = direccionResidencia;
        this.equipos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return nombreCompleto + " (Doc: " + numeroDocumento + ") - Tel: " + numeroTelefono
                + " - " + correoElectronico + " - " + direccionResidencia
                + " - Equipos: " + equipos.size();
    }

    public String getNumeroDocumento() { return numeroDocumento; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getNumeroTelefono() { return numeroTelefono; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getDireccionResidencia() { return direccionResidencia; }
    public List<Dispositivo> getEquipos() { return equipos; }

    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public void setDireccionResidencia(String direccionResidencia) { this.direccionResidencia = direccionResidencia; }

}
