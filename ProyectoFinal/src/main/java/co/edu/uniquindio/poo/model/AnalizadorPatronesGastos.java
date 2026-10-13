package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class AnalizadorPatronesGastos {

        // Constructor vacío poke no hay atributos
        public AnalizadorPatronesGastos() {
        }

        //Calcula el promedio de los montos a partir de un historial de transacciones
        public double calcularPromedio(ArrayList<Transaccion> historial) {
            if (historial == null || historial.isEmpty()) {
                return 0.0;
            }
            double suma = 0;
            for (Transaccion t : historial) {
                suma += t.getMonto();
            }
            return suma / historial.size();
        }

        //Calcular la desviación estándar de los montos del historial de transacciones
        public double calcularDesviacionEstandar(ArrayList<Transaccion> historial) {
            if (historial == null || historial.size() <= 1) {
                return 0.0;
            }
            double promedio = calcularPromedio(historial);
            double sumaDiferenciasCuadradas = 0;

            for (Transaccion t : historial) {
                sumaDiferenciasCuadradas += Math.pow(t.getMonto() - promedio, 2);
            }

            return Math.sqrt(sumaDiferenciasCuadradas / (historial.size() - 1));
        }

        //Detecta si una transacción es atípica basándose en la media y la desviación del historial
        public boolean detectarTransaccionAtipica(Transaccion transaccion, ArrayList<Transaccion> historial) {
            if (transaccion == null || historial == null || historial.isEmpty()) {
                return false;
            }

            double promedio = calcularPromedio(historial);
            double desviacion = calcularDesviacionEstandar(historial);

            if (desviacion == 0) {
                return false;
            }

            // Atípica si supera 2 desviaciones estándar del promedio
            double limite = 2 * desviacion;
            double monto = transaccion.getMonto();
        }

        // Genera un reporte de gasto asociado a un cliente específico.
        public ReporteGasto generarReporte(Cliente cliente) {
            ReporteGasto reporte = new ReporteGasto();
            reporte.setFechaGeneracion(LocalDate.now());

            return reporte;
        }
}

