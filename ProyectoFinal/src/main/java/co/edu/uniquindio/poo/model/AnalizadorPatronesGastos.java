package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalizadorPatronesGastos {

        // Constructor vacío poke no hay atributos
        public AnalizadorPatronesGastos() {
        }

        //Calcula el promedio de los montos a partir de un historial de transacciones
        public double calcularPromedio(List<Transaccion> historial) {
            if (historial == null || historial.isEmpty()) {
                return 0.0;
            }
            return historial.stream()
                    .mapToDouble(t -> t.getMonto())
                    .average()
                    .orElse(0.0);
        }

        //Calcular la desviación estándar de los montos del historial de transacciones
        public double calcularDesviacionEstandar(List<Transaccion> historial) {
            if (historial == null || historial.size() <= 1) {
                return 0.0;
            }
            double promedio = calcularPromedio(historial);
            double suma = historial.stream()
                    .mapToDouble(t -> Math.pow(t.getMonto() - promedio, 2))
                    .sum();
            return Math.sqrt(suma / (historial.size() - 1));
        }

        //Detecta si una transacción es atípica basándose en la media y la desviación del historial
        public boolean detectarTransaccionAtipica(Transaccion transaccion, List<Transaccion> historial) {
            if (transaccion == null || historial == null || historial.isEmpty()) {
                return false;
            }
            double promedio = calcularPromedio(historial);
            double desviacion = calcularDesviacionEstandar(historial);

            if (desviacion == 0) {
                return false;
            }

            // Atípica si supera 2 desviaciones estándar del promedio
            double umbral = promedio + 2 * desviacion;
            return transaccion.getMonto() > umbral;
        }

        // Genera un reporte de gasto asociado a un cliente específico.
        public ReporteGasto generarReporte(Cliente cliente, List<Transaccion> transacciones) {
            List<Transaccion> delCliente = transacciones.stream()
                    .filter(t -> t.getIdCliente().equals(cliente.getIdentificacion()))
                    .filter(t -> t.getEstado() != EstadoTransaccion.REVERTIDA)
                    .toList();
            Map<String, Double> desglose = delCliente.stream()
                    .collect(Collectors.groupingBy(
                            t -> t.nombreTipo(),
                            Collectors.summingDouble(t -> t.getMonto())));
            double totalGasto = delCliente.stream()
                    .filter(t -> t instanceof Retiro || t instanceof Transferencia)
                    .mapToDouble(t -> t.getMonto())
                    .sum();

            return new ReporteGasto(LocalDate.now(), desglose, totalGasto);
        }

}

