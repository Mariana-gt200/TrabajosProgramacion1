package co.edu.uniquindio.poo.parcial_3.model;

public class PanaderoIndustrial extends Panadero {
    private static final int MINUTOS_PREPARACION = 20;
    private static final int CAPACIDAD_LOTE = 30;
    private static final int MINUTOS_LOTE_TRADICIONAL = 25;
    private static final int MINUTOS_LOTE_INTEGRAL = 35;

    public PanaderoIndustrial(String nombre, String documento, String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }

    @Override
    public String getTipo() {
        return "Panadero industrial";
    }

    // Un lote incompleto toma el mismo tiempo que uno lleno. La preparación se cuenta una vez por pedido
    @Override
    public int calcularMinutos(TipoPan tipoPan, int cantidad) {
        int lotes = cantidad / CAPACIDAD_LOTE;
        if (cantidad % CAPACIDAD_LOTE != 0) {
            lotes++;
        }
        int minutosPorLote = MINUTOS_LOTE_TRADICIONAL;
        if (tipoPan == TipoPan.INTEGRAL) {
            minutosPorLote = MINUTOS_LOTE_INTEGRAL;
        }
        return MINUTOS_PREPARACION + lotes * minutosPorLote;
    }
}