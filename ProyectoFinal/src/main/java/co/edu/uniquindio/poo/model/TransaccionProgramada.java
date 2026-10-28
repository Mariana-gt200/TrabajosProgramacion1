package co.edu.uniquindio.poo.model;

import java.time.LocalDate;

/*
 * Representa una transacción recurrente programada por un cliente
 * (depósito automático, retiro periódico o transferencia periódica).
 * Implementa Comparable para poder ordenarse por fecha de ejecución.
 */
public class TransaccionProgramada implements Comparable<TransaccionProgramada> {

    private String idProgramacion;
    private String idCliente;
    private LocalDate fechaInicio;
    private LocalDate fechaProximaEjecucion;
    private FrecuenciaProgramada frecuencia;
    private double monto;
    private TipoTransaccion tipo;
    private Monedero monederoOrigen;   // Retiros y transferencias
    private Monedero monederoDestino;  // Depósitos y transferencias
    private boolean activa = true; // Si la programación está activa o cancelada

    //Constructor
    public TransaccionProgramada(String idProgramacion, String idCliente, LocalDate fechaInicio,
                                 FrecuenciaProgramada frecuencia, double monto, TipoTransaccion tipo,
                                 Monedero monederoOrigen, Monedero monederoDestino)
            throws MontoInvalidoException, TransferenciaInvalidaException, ProgramacionInvalidaException {

        if (monto <= 0) {
            throw new MontoInvalidoException("El monto de la transacción programada debe ser mayor a cero.");
        }
        if (idProgramacion == null || idCliente == null || idCliente.isBlank()
                || fechaInicio == null || frecuencia == null || tipo == null) {
            throw new IllegalArgumentException("Los datos de la programación no pueden estar vacíos.");
        }
        validarMonederosSegunTipo(tipo, monederoOrigen, monederoDestino);

        this.idProgramacion = idProgramacion;
        this.idCliente = idCliente;
        this.fechaInicio = fechaInicio;
        this.fechaProximaEjecucion = fechaInicio; // La primera ejecución es en la fecha de inicio
        this.frecuencia = frecuencia;
        this.monto = monto;
        this.tipo = tipo;
        this.monederoOrigen = monederoOrigen;
        this.monederoDestino = monederoDestino;
        this.activa = true;
    }

    //Metodos

    //Valida que los monederos exigidos por el tipo de transacción estén presentes
    private static void validarMonederosSegunTipo(TipoTransaccion tipo, Monedero origen, Monedero destino)
            throws ProgramacionInvalidaException, TransferenciaInvalidaException {
        switch (tipo) {
            case DEPOSITO:
                if (destino == null) {
                    throw new ProgramacionInvalidaException("Un depósito programado requiere monedero destino.");
                }
                break;
            case RETIRO:
                if (origen == null) {
                    throw new ProgramacionInvalidaException("Un retiro programado requiere monedero origen.");
                }
                break;
            case TRANSFERENCIA:
                if (origen == null || destino == null) {
                    throw new ProgramacionInvalidaException("Una transferencia programada requiere monedero origen y destino.");
                }
                if (origen.getIdMonedero().equals(destino.getIdMonedero())
                        && origen.getIdentificacionCliente().equals(destino.getIdentificacionCliente())) {
                    throw new TransferenciaInvalidaException("El monedero origen y destino no pueden ser el mismo.");
                }
                break;
        }
    }

    //Indica si la programación debe ejecutarse en la fecha dada (activa y con fecha vencida o igual a hoy)
    public boolean debeEjecutarse(LocalDate hoy) {
        return activa && !fechaProximaEjecucion.isAfter(hoy);
    }

    //Calcula la siguiente fecha de ejecución según la frecuencia
    public LocalDate calcularSiguienteFecha() {
        return switch (frecuencia) {
            case DIARIA -> fechaProximaEjecucion.plusDays(1);
            case SEMANAL -> fechaProximaEjecucion.plusWeeks(1);
            case MENSUAL -> fechaProximaEjecucion.plusMonths(1);
        };
    }

    //Avanza la programación a su siguiente ejecución (se llama después de ejecutarla)
    public void avanzarProximaEjecucion() {
        this.fechaProximaEjecucion = calcularSiguienteFecha();
    }

    //Ordena por fecha de próxima ejecución (y por id para desempatar de forma estable)
    @Override
    public int compareTo(TransaccionProgramada otra) {
        int comparacion = this.fechaProximaEjecucion.compareTo(otra.fechaProximaEjecucion);
        return comparacion != 0 ? comparacion : this.idProgramacion.compareTo(otra.idProgramacion);
    }

    @Override
    public String toString() {
        return idProgramacion + " | " + tipo + " | $" + monto + " | " + frecuencia
                + " | próxima: " + fechaProximaEjecucion + (activa ? "" : " | CANCELADA");
    }

    // Getters y Setters
    public String getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(String idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaProximaEjecucion() {
        return fechaProximaEjecucion;
    }

    public void setFechaProximaEjecucion(LocalDate fechaProximaEjecucion) {
        this.fechaProximaEjecucion = fechaProximaEjecucion;
    }

    public FrecuenciaProgramada getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(FrecuenciaProgramada frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public Monedero getMonederoOrigen() {
        return monederoOrigen;
    }

    public void setMonederoOrigen(Monedero monederoOrigen) {
        this.monederoOrigen = monederoOrigen;
    }

    public Monedero getMonederoDestino() {
        return monederoDestino;
    }

    public void setMonederoDestino(Monedero monederoDestino) {
        this.monederoDestino = monederoDestino;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}