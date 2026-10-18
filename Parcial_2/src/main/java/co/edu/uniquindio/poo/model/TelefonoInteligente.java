package co.edu.uniquindio.poo.model;

public class TelefonoInteligente extends Dispositivo {
    private String sistemaOperativo;
    private int capacidadAlmacenamiento;  // GB
    private int capacidadBateriaMah;      // mAh
    private boolean compatible5G;

    public TelefonoInteligente(String codigoRegistro, String numeroSerie, String marca, String modelo,
                               int anioFabricacion, String descripcionEstadoFisico,
                               String sistemaOperativo, int capacidadAlmacenamiento,
                               int capacidadBateriaMah, boolean compatible5G) {
        super(codigoRegistro, numeroSerie, marca, modelo, anioFabricacion, descripcionEstadoFisico);
        this.sistemaOperativo = sistemaOperativo;
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.capacidadBateriaMah = capacidadBateriaMah;
        this.compatible5G = compatible5G;
    }

    @Override
    public String getCategoria() { return CATEGORIA_TELEFONO; }

    @Override
    public String getEspecialidadRequerida() { return Tecnico.DISPOSITIVOS_MOVILES; }

    public String getSistemaOperativo() { return sistemaOperativo; }
    public int getCapacidadAlmacenamiento() { return capacidadAlmacenamiento; }
    public int getCapacidadBateriaMah() { return capacidadBateriaMah; }
    public boolean isCompatible5G() { return compatible5G; }

    public void setSistemaOperativo(String v) { this.sistemaOperativo = v; }
    public void setCapacidadAlmacenamiento(int v) { this.capacidadAlmacenamiento = v; }
    public void setCapacidadBateriaMah(int v) { this.capacidadBateriaMah = v; }
    public void setCompatible5G(boolean v) { this.compatible5G = v; }

    @Override
    public String toString() {
        return super.toString() + " | " + sistemaOperativo + ", almacenamiento " + capacidadAlmacenamiento
                + " GB, batería " + capacidadBateriaMah + " mAh, 5G: " + (compatible5G ? "sí" : "no");
    }
}
