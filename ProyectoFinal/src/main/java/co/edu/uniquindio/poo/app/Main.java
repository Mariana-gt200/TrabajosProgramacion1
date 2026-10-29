package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        MonederoVirtual monedero = new MonederoVirtual(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ServicioNotificacion(), new AnalizadorPatronesGastos());

        // Datos de prueba: clientes, monederos, historial y programaciones precargados ---
        // Clientes (uno por canal de notificación)
        Cliente ana = new Cliente("1001", "Ana Gómez", "ana@correo.com", "3001112233", RangoCliente.BRONCE);
        Cliente luis = new Cliente("1002", "Luis Restrepo", "luis@correo.com", "3002223344", RangoCliente.BRONCE);
        Cliente sofia = new Cliente("1003", "Sofía Ruiz", "sofia@correo.com", "3003334455", RangoCliente.BRONCE);
        ana.setCanalPreferido(CanalNotificacion.WHATSAPP);
        luis.setCanalPreferido(CanalNotificacion.GMAIL);
        sofia.setCanalPreferido(CanalNotificacion.SMS);
        monedero.registrarCliente(ana);
        monedero.registrarCliente(luis);
        monedero.registrarCliente(sofia);

        // Monederos (multimonedero: ahorros y gastos diarios)
        Monedero anaAhorros = new Monedero("M-01", TipoMonedero.AHORROS, 500000, "1001");
        Monedero anaGastos = new Monedero("M-02", TipoMonedero.GASTOS_DIARIOS, 80000, "1001");
        Monedero luisAhorros = new Monedero("M-03", TipoMonedero.AHORROS, 300000, "1002");
        Monedero sofiaGastos = new Monedero("M-04", TipoMonedero.GASTOS_DIARIOS, 12000, "1003");
        monedero.registrarMonedero("1001", anaAhorros);
        monedero.registrarMonedero("1001", anaGastos);
        monedero.registrarMonedero("1002", luisAhorros);
        monedero.registrarMonedero("1003", sofiaGastos);

        // Historial previo de Ana: 9 retiros habituales entre 90 y 120 (ids T-01 a T-09), para probar atípicas
        double[] habituales = {100, 120, 90, 110, 105, 95, 115, 100, 108};
        for (int i = 0; i < habituales.length; i++) {
            monedero.registrarTransaccion(new Retiro("T-0" + (i + 1), LocalDate.now().minusDays(10 - i),
                    habituales[i], EstadoTransaccion.COMPLETADA, "1001", anaGastos));
        }

        // Puntos para probar rangos y canjes: Luis 600 puntos (PLATA) y Sofía 1200 puntos (ORO)
        monedero.depositar("1002", "M-03", 60000);
        monedero.depositar("1003", "M-04", 120000);

        // Transacciones programadas de prueba (la fecha de inicio no puede estar en el pasado)
        LocalDate hoy = LocalDate.now();
        monedero.programarTransaccion("1001", "M-01", null, null, hoy.plusDays(7), FrecuenciaProgramada.MENSUAL, 200, TipoTransaccion.DEPOSITO);
        monedero.programarTransaccion("1001", "M-01", "1002", "M-03", hoy.plusDays(2), FrecuenciaProgramada.SEMANAL, 150, TipoTransaccion.TRANSFERENCIA);
        monedero.programarTransaccion("1003", "M-04", null, null, hoy.plusDays(1), FrecuenciaProgramada.DIARIA, 50, TipoTransaccion.RETIRO);
        //Fin de datos de prueba

        System.out.println("Bienvenido al Monedero Virtual con sistema de puntos");

        int opcion;
        do {
            System.out.println("\nElija la opción a realizar:\n" +
                    "=====Menú=====\n" +
                    "--- Clientes y monederos ---\n" +
                    "1. Registrar cliente\n" +
                    "2. Registrar monedero\n" +
                    "3. Consultar monederos, saldos y rango de un cliente\n" +
                    "--- Transacciones ---\n" +
                    "4. Depositar\n" +
                    "5. Retirar\n" +
                    "6. Transferir\n" +
                    "7. Consultar historial de transacciones\n" +
                    "8. Revertir transacción\n" +
                    "--- Puntos y rangos ---\n" +
                    "9. Consultar puntos y rango\n" +
                    "10. Canjear puntos\n" +
                    "--- Análisis ---\n" +
                    "11. Analizar una transacción (promedio, desviación y umbral)\n" +
                    "12. Reporte de gasto de un cliente\n" +
                    "--- Transacciones programadas ---\n" +
                    "13. Programar transacción\n" +
                    "14. Listar transacciones programadas de un cliente\n" +
                    "15. Cancelar transacción programada\n" +
                    "16. Ejecutar programadas hasta una fecha\n" +
                    "17. Enviar recordatorios de programadas\n" +
                    "--- Notificaciones ---\n" +
                    "18. Enviar notificación de prueba a un cliente\n" +
                    "0. Salir");
            opcion = leerEntero("Opción: ");

            try {
                switch (opcion) {
                    case 1: {
                        // Registrar cliente
                        String doc = leerTexto("Ingrese la identificación del cliente: ");
                        String nombre = leerTexto("Ingrese el nombre completo del cliente: ");
                        String correo = leerTexto("Ingrese el correo electrónico del cliente: ");
                        String tel = leerTexto("Ingrese el teléfono del cliente: ");
                        int canal = leerEntero("Canal de notificación:\n1. WhatsApp\n2. Gmail\n3. SMS\nOpción: ");
                        if (doc.isEmpty() || nombre.isEmpty()) {
                            System.out.println("La identificación y el nombre son obligatorios.");
                        } else if (canal < 1 || canal > 3) {
                            System.out.println("Canal de notificación inválido.");
                        } else {
                            Cliente cliente = new Cliente(doc, nombre, correo, tel, RangoCliente.BRONCE);
                            cliente.setCanalPreferido(CanalNotificacion.values()[canal - 1]);
                            System.out.println(monedero.registrarCliente(cliente));
                        }
                        break;
                    }

                    case 2: {
                        // Registrar monedero
                        String docDueno = leerTexto("Identificación del cliente dueño del monedero: ");
                        String idMonedero = leerTexto("Id del monedero: ");
                        int tipoMonedero = leerEntero("Tipo de monedero:\n1. Ahorros\n2. Gastos diarios\nOpción: ");
                        double saldoInicial = leerDecimal("Saldo inicial: ");
                        if (tipoMonedero < 1 || tipoMonedero > 2) {
                            System.out.println("Tipo de monedero inválido.");
                        } else if (saldoInicial < 0) {
                            System.out.println("El saldo inicial no puede ser negativo.");
                        } else {
                            TipoMonedero tipo = TipoMonedero.values()[tipoMonedero - 1];
                            System.out.println(monedero.registrarMonedero(docDueno, new Monedero(idMonedero, tipo, saldoInicial, docDueno)));
                        }
                        break;
                    }

                    case 3: {
                        // Consultar monederos, saldos y rango
                        String doc = leerTexto("Identificación del cliente: ");
                        Optional<Cliente> cliente = monedero.buscarCliente(doc);
                        if (cliente.isEmpty()) {
                            System.out.println("No existe el cliente con id " + doc);
                        } else {
                            System.out.println("Cliente: " + cliente.get().getNombre() + " | Rango: " + cliente.get().getRango());
                            monedero.obtenerMonederos(doc).forEach(m ->
                                    System.out.println("  " + m.getIdMonedero() + " (" + m.getTipo() + ") saldo: $" + m.getSaldo()));
                            System.out.println("Saldo total: $" + monedero.consultarSaldoTotal(doc));
                        }
                        break;
                    }

                    case 4: {
                        // Depositar
                        String doc = leerTexto("Identificación del cliente: ");
                        String idMonedero = leerTexto("Id del monedero: ");
                        double monto = leerDecimal("Monto a depositar: ");
                        System.out.println(monedero.depositar(doc, idMonedero, monto));
                        break;
                    }

                    case 5: {
                        // Retirar
                        String doc = leerTexto("Identificación del cliente: ");
                        String idMonedero = leerTexto("Id del monedero: ");
                        double monto = leerDecimal("Monto a retirar: ");
                        System.out.println(monedero.retirar(doc, idMonedero, monto));
                        break;
                    }

                    case 6: {
                        // Transferir
                        String docOrigen = leerTexto("Identificación del cliente origen: ");
                        String idOrigen = leerTexto("Id del monedero origen: ");
                        String docDestino = leerTexto("Identificación del cliente destino: ");
                        String idDestino = leerTexto("Id del monedero destino: ");
                        double monto = leerDecimal("Monto a transferir: ");
                        System.out.println(monedero.transferir(docOrigen, idOrigen, docDestino, idDestino, monto));
                        break;
                    }

                    case 7: {
                        // Consultar historial (propias y transferencias recibidas)
                        String doc = leerTexto("Identificación del cliente: ");
                        List<Transaccion> historial = monedero.consultarHistorial(doc);
                        if (historial.isEmpty()) {
                            System.out.println("El cliente no tiene transacciones registradas.");
                        } else {
                            historial.forEach(t -> System.out.println("  " + t.getIdTransaccion() + " | " + t.nombreTipo()
                                    + (t.getIdCliente().equals(doc) ? "" : " (recibida)")
                                    + " | $" + t.getMonto() + " | " + t.getFecha() + " | " + t.getEstado()));
                        }
                        break;
                    }

                    case 8: {
                        // Revertir transacción
                        String idTrx = leerTexto("Id de la transacción a revertir: ");
                        System.out.println(monedero.revertirTransaccion(idTrx));
                        break;
                    }

                    case 9: {
                        // Consultar puntos y rango
                        String doc = leerTexto("Identificación del cliente: ");
                        Cliente cliente = monedero.buscarCliente(doc)
                                .orElseThrow(() -> new ClienteNoEncontradoException("No existe el cliente con id " + doc));
                        System.out.println("Puntos acumulados: " + monedero.calcularPuntos(doc)
                                + " | Puntos disponibles: " + monedero.consultarPuntosDisponibles(doc)
                                + " | Rango: " + cliente.getRango());
                        System.out.println("Descuento en comisión: " + (cliente.isDescuentoComision() ? "activo" : "no")
                                + " | Sin cargos por retiros hasta: "
                                + (cliente.getSinCargosRetirosHasta() == null ? "no activo" : cliente.getSinCargosRetirosHasta()));
                        List<Transaccion> puntos = monedero.consultarHistorialPuntos(doc);
                        puntos.forEach(t -> System.out.println("  " + t.getIdTransaccion() + " | " + t.nombreTipo()
                                + " $" + t.getMonto() + " | +" + t.calcularPuntosGenerados() + " puntos"));
                        break;
                    }

                    case 10: {
                        // Canjear puntos
                        String doc = leerTexto("Identificación del cliente: ");
                        int beneficio = leerEntero("Beneficio a canjear:\n1. 100 puntos: 10% menos en la comisión de transferencias\n"
                                + "2. 500 puntos: un mes sin cargos por retiros\n3. 1000 puntos: bono de 50 unidades\nOpción: ");
                        switch (beneficio) {
                            case 1:
                                System.out.println(monedero.canjearDescuentoComision(doc));
                                break;
                            case 2:
                                System.out.println(monedero.canjearMesSinCargos(doc));
                                break;
                            case 3:
                                String idBono = leerTexto("Id del monedero donde recibir el bono: ");
                                System.out.println(monedero.canjearBonoSaldo(doc, idBono));
                                break;
                            default:
                                System.out.println("Beneficio inválido.");
                        }
                        break;
                    }

                    case 11: {
                        // Analizar una transacción (promedio, desviación y umbral de atípicas)
                        String idEvaluar = leerTexto("Id de la transacción a analizar: ");
                        Optional<Transaccion> aEvaluar = monedero.buscarTransaccion(idEvaluar);
                        if (aEvaluar.isEmpty()) {
                            System.out.println("No existe una transacción con ese id.");
                        } else {
                            Transaccion t = aEvaluar.get();
                            // Referencia: mismo cliente y mismo tipo, sin la evaluada, sin sospechosas ni revertidas
                            List<Transaccion> referencia = monedero.obtenerHistorialCliente(t.getIdCliente()).stream()
                                    .filter(x -> !x.getIdTransaccion().equals(t.getIdTransaccion()))
                                    .filter(x -> x.nombreTipo().equals(t.nombreTipo()))
                                    .filter(x -> x.getEstado() != EstadoTransaccion.SOSPECHOSA
                                            && x.getEstado() != EstadoTransaccion.REVERTIDA)
                                    .toList();
                            AnalizadorPatronesGastos analizador = monedero.getAnalizadorPatronesGastos();
                            double promedio = analizador.calcularPromedio(referencia);
                            double desviacion = analizador.calcularDesviacionEstandar(referencia);
                            System.out.println("Transacciones de referencia: " + referencia.size()
                                    + " | Promedio: " + promedio + " | Desviación estándar: " + desviacion
                                    + " | Umbral: " + (promedio + 2 * desviacion));
                            System.out.println(analizador.detectarTransaccionAtipica(t, referencia)
                                    ? "La transacción " + idEvaluar + " es ATÍPICA (estado actual: " + t.getEstado() + ")."
                                    : "La transacción " + idEvaluar + " es normal (estado actual: " + t.getEstado() + ").");
                        }
                        break;
                    }

                    case 12: {
                        // Reporte de gasto de un cliente
                        String doc = leerTexto("Identificación del cliente: ");
                        Optional<Cliente> cliente = monedero.buscarCliente(doc);
                        if (cliente.isEmpty()) {
                            System.out.println("No existe el cliente con id " + doc);
                        } else {
                            ReporteGasto reporte = monedero.getAnalizadorPatronesGastos()
                                    .generarReporte(cliente.get(), monedero.getListaTransacciones());
                            System.out.println("Reporte de gasto al " + reporte.getFecha());
                            reporte.getDesglosePorCategoria().forEach((tipo, total) -> System.out.println("  " + tipo + ": $" + total));
                            System.out.println("Total gastado (retiros + transferencias): $" + reporte.getTotalGasto());
                        }
                        break;
                    }

                    case 13: {
                        // Programar transacción
                        String doc = leerTexto("Identificación del cliente: ");
                        int tipoProg = leerEntero("Tipo de transacción:\n1. Depósito\n2. Retiro\n3. Transferencia\nOpción: ");
                        int frecProg = leerEntero("Frecuencia:\n1. Diaria\n2. Semanal\n3. Mensual\nOpción: ");
                        double monto = leerDecimal("Monto: ");
                        LocalDate fecha = leerFecha("Fecha de inicio (AAAA-MM-DD): ");
                        String idMonedero = leerTexto("Id del monedero del cliente: ");
                        String docDestino = null;
                        String idDestino = null;
                        if (tipoProg == 3) {
                            docDestino = leerTexto("Identificación del cliente destino: ");
                            idDestino = leerTexto("Id del monedero destino: ");
                        }
                        if (tipoProg < 1 || tipoProg > 3 || frecProg < 1 || frecProg > 3) {
                            System.out.println("Tipo o frecuencia inválidos.");
                            break;
                        }
                        System.out.println(monedero.programarTransaccion(doc, idMonedero, docDestino, idDestino,
                                fecha, FrecuenciaProgramada.values()[frecProg - 1], monto,
                                TipoTransaccion.values()[tipoProg - 1]));
                        break;
                    }

                    case 14: {
                        // Listar programadas activas de un cliente (ordenadas por fecha)
                        String doc = leerTexto("Identificación del cliente: ");
                        List<TransaccionProgramada> lista = monedero.consultarProgramadas(doc);
                        if (lista.isEmpty()) {
                            System.out.println("El cliente no tiene transacciones programadas activas.");
                        } else {
                            lista.forEach(p -> System.out.println("  " + p));
                        }
                        break;
                    }

                    case 15: {
                        // Cancelar programada
                        String idCancelar = leerTexto("Id de la programación a cancelar: ");
                        System.out.println(monedero.cancelarProgramada(idCancelar));
                        break;
                    }

                    case 16: {
                        // Ejecutar programadas vencidas hasta una fecha
                        LocalDate fecha = leerFecha("Ejecutar programaciones hasta la fecha (AAAA-MM-DD): ");
                        List<String> resultados = monedero.ejecutarProgramadas(fecha);
                        if (resultados.isEmpty()) {
                            System.out.println("No había programaciones para ejecutar.");
                        } else {
                            resultados.forEach(r -> System.out.println("  " + r));
                        }
                        break;
                    }

                    case 17: {
                        // Enviar recordatorios de programadas que se ejecutan mañana
                        LocalDate fecha = leerFecha("Fecha de hoy para el recordatorio (AAAA-MM-DD): ");
                        int enviados = monedero.enviarRecordatoriosProgramadas(fecha);
                        System.out.println("Recordatorios enviados: " + enviados);
                        break;
                    }

                    case 18: {
                        // Enviar notificación de prueba
                        String doc = leerTexto("Identificación del cliente: ");
                        monedero.buscarCliente(doc).ifPresentOrElse(
                                c -> monedero.getServicioNotificacion().enviarNotificacion("Notificación de prueba del Monedero Virtual", c),
                                () -> System.out.println("No existe el cliente con id " + doc));
                        break;
                    }

                    case 0:
                        // Salir
                        System.out.println("Gracias por usar el Monedero Virtual");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    //Utilidades de lectura
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private static LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(leerTexto(mensaje));
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida, use el formato AAAA-MM-DD.");
            }
        }
    }
}