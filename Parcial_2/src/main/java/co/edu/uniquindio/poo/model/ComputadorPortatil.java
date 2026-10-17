package co.edu.uniquindio.poo.model;

public class ComputadorPortatil extends Dispositivo{
    private double tamanoPantallaPulgadas;
    private int memoriaRAM;               // GB
    private int capacidadAlmacenamiento;  // GB
    private boolean bateriaExtraible;

    public ComputadorPortatil(String codigoRegistro, String numeroSerie, String marca, String modelo,
                              int anioFabricacion, String descripcionEstadoFisico,
                              double tamanoPantallaPulgadas, int memoriaRAM,
                              int capacidadAlmacenamiento, boolean bateriaExtraible) {
        super(codigoRegistro, numeroSerie, marca, modelo, anioFabricacion, descripcionEstadoFisico);
        this.tamanoPantallaPulgadas = tamanoPantallaPulgadas;
        this.memoriaRAM = memoriaRAM;
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.bateriaExtraible = bateriaExtraible;
    }

    @Override
    public String getCategoria() { return CAT_PORTATIL; }

    @Override
    public String getEspecialidadRequerida() { return Tecnico.COMPUTADORES; }

    public double getTamanoPantallaPulgadas() { return tamanoPantallaPulgadas; }
    public int getMemoriaRAM() { return memoriaRAM; }
    public int getCapacidadAlmacenamiento() { return capacidadAlmacenamiento; }
    public boolean isBateriaExtraible() { return bateriaExtraible; }

    public void setTamanoPantallaPulgadas(double v) { this.tamanoPantallaPulgadas = v; }
    public void setMemoriaRAM(int v) { this.memoriaRAM = v; }
    public void setCapacidadAlmacenamiento(int v) { this.capacidadAlmacenamiento = v; }
    public void setBateriaExtraible(boolean v) { this.bateriaExtraible = v; }

    @Override
    public String toString() {
        return super.toString() + " | " + tamanoPantallaPulgadas + "\", RAM " + memoriaRAM
                + " GB, almacenamiento " + capacidadAlmacenamiento + " GB, batería extraíble: "
                + (bateriaExtraible ? "sí" : "no");
    }
}
