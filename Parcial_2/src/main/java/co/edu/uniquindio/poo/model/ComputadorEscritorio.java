package co.edu.uniquindio.poo.model;

public class ComputadorEscritorio extends Dispositivo {
    private String tipoProcesador;
    private int memoriaRAM;               // GB
    private int capacidadAlmacenamiento;  // GB
    private boolean tarjetaGraficaDedicada;

    public ComputadorEscritorio(String codigoRegistro, String numeroSerie, String marca, String modelo,
                                int anioFabricacion, String descripcionEstadoFisico,
                                String tipoProcesador, int memoriaRAM,
                                int capacidadAlmacenamiento, boolean tarjetaGraficaDedicada) {
        super(codigoRegistro, numeroSerie, marca, modelo, anioFabricacion, descripcionEstadoFisico);
        this.tipoProcesador = tipoProcesador;
        this.memoriaRAM = memoriaRAM;
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.tarjetaGraficaDedicada = tarjetaGraficaDedicada;
    }

    @Override
    public String getCategoria() { return CAT_ESCRITORIO; }

    @Override
    public String getEspecialidadRequerida() { return Tecnico.COMPUTADORES; }

    public String getTipoProcesador() { return tipoProcesador; }
    public int getMemoriaRAM() { return memoriaRAM; }
    public int getCapacidadAlmacenamiento() { return capacidadAlmacenamiento; }
    public boolean isTarjetaGraficaDedicada() { return tarjetaGraficaDedicada; }

    public void setTipoProcesador(String v) { this.tipoProcesador = v; }
    public void setMemoriaRAM(int v) { this.memoriaRAM = v; }
    public void setCapacidadAlmacenamiento(int v) { this.capacidadAlmacenamiento = v; }
    public void setTarjetaGraficaDedicada(boolean v) { this.tarjetaGraficaDedicada = v; }

    @Override
    public String toString() {
        return super.toString() + " | Procesador " + tipoProcesador + ", RAM " + memoriaRAM
                + " GB, almacenamiento " + capacidadAlmacenamiento + " GB, gráfica dedicada: "
                + (tarjetaGraficaDedicada ? "sí" : "no");
    }
}
