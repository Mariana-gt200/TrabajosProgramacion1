package co.edu.uniquindio.poo.model;
import java.time.LocalDate;
import java.util.Map;

public class ReporteGasto {
    private LocalDate fecha;
    private Map<String, Double> desglosePorCategoria;
    private double totalGasto;

    //Constructor

    public ReporteGasto(LocalDate fecha, Map<String, Double> desglosePorCategoria, double totalGasto) {
        this.fecha = fecha;
        this.desglosePorCategoria = desglosePorCategoria;
        this.totalGasto = totalGasto;
    }


    //Get y set

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Map<String, Double> getDesglosePorCategoria() {
        return desglosePorCategoria;
    }

    public void setDesglosePorCategoria(Map<String, Double> desglosePorCategoria) {
        this.desglosePorCategoria = desglosePorCategoria;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }
}
