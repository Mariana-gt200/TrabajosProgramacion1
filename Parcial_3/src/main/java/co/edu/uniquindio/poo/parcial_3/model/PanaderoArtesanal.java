package co.edu.uniquindio.poo.parcial_3.model;

public class PanaderoArtesanal extends Panadero {
    private static final int CAPACIDAD_BANDEJA = 12;
    private static final int MINUTOS_BANDEJA_TRADICIONAL = 30;
    private static final int MINUTOS_BANDEJA_INTEGRAL = 40;

    public PanaderoArtesanal(String nombre, String documento, String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }

    @Override
    public String getTipo() {
        return "Panadero artesanal";
    }

    // Una bandeja incompleta toma el mismo tiempo que una llena
    @Override
    public int calcularMinutos(TipoPan tipoPan, int cantidad) {
        int bandejas = cantidad / CAPACIDAD_BANDEJA;
        if (cantidad % CAPACIDAD_BANDEJA != 0) {
            bandejas++;
        }
        int minutosPorBandeja = MINUTOS_BANDEJA_TRADICIONAL;
        if (tipoPan == TipoPan.INTEGRAL) {
            minutosPorBandeja = MINUTOS_BANDEJA_INTEGRAL;
        }
        return bandejas * minutosPorBandeja;
    }
}