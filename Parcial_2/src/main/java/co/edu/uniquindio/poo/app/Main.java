package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Empresa empresa = new Empresa("TecnoFix", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Datos de prueba: clientes, equipos, técnicos y repuestos precargados ---
        empresa.registrarCliente(new Cliente("1001", "Laura Gómez", "3001112233", "laura@correo.com", "Calle 10 #5-20"));
        empresa.registrarCliente(new Cliente("1002", "Carlos Restrepo", "3002223344", "carlos@correo.com", "Carrera 14 #8-31"));

        empresa.registrarEquipo("1001", new ComputadorPortatil("EQ-01", "SN-001", "Lenovo", "IdeaPad 3", 2022, "Pantalla con rayones", 15.6, 8, 512, false));
        empresa.registrarEquipo("1001", new TelefonoInteligente("EQ-02", "SN-002", "Samsung", "A54", 2023, "Buen estado", "Android", 128, 5000, true));
        empresa.registrarEquipo("1002", new ComputadorEscritorio("EQ-03", "SN-003", "HP", "Pavilion", 2020, "Carcasa golpeada", "Intel i5", 16, 1000, false));

        empresa.registrarTecnico(new Tecnico("T-01", "Andrés Torres", "2001", Tecnico.COMPUTADORES, true));
        empresa.registrarTecnico(new Tecnico("T-02", "María Ruiz", "2002", Tecnico.DISPOSITIVOS_MOVILES, true));
        empresa.registrarTecnico(new Tecnico("T-03", "Julián Pérez", "2003", Tecnico.SOPORTE_GENERAL, false));

        empresa.registrarRepuesto(new Repuesto("R-01", "Pantalla 15.6", "Pantalla LED para portátil", 250000, 10, Dispositivo.CATEGORIA_PORTATIL));
        empresa.registrarRepuesto(new Repuesto("R-02", "Batería 5000mAh", "Batería para teléfono", 80000, 3, Dispositivo.CATEGORIA_TELEFONO));
        empresa.registrarRepuesto(new Repuesto("R-03", "Fuente de poder", "Fuente 500W", 120000, 6, Dispositivo.CATEGORIA_ESCRITORIO));

        //Fin de datos de prueba

        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de gestión de la empresa " + empresa.getNombre());

        int opcion;
        do {
            opcion = Integer.valueOf(JOptionPane.showInputDialog("Elija la opción a realizar:" + "\n" +
                    "=====Menú=====" + "\n" +
                    "1. Registrar cliente" + "\n" +
                    "2. Registrar equipo" + "\n" +
                    "3. Registrar técnico" + "\n" +
                    "4. Registrar repuesto" + "\n" +
                    "5. Crear orden de servicio" + "\n" +
                    "6. Agregar detalle a una orden" + "\n" +
                    "7. Asignar técnico a un detalle" + "\n" +
                    "8. Registrar horas de trabajo o repuestos utilizados" + "\n" +
                    "9. Calcular valor de un detalle" + "\n" +
                    "10. Calcular valor total de una orden" + "\n" +
                    "11. Actualizar estado de un detalle" + "\n" +
                    "12. Finalizar orden" + "\n" +
                    "13. Consultar órdenes de un cliente" + "\n" +
                    "14. Consultar trabajos de un técnico" + "\n" +
                    "15. Consultar equipos con intervenciones pendientes" + "\n" +
                    "16. Reporte de servicios" + "\n" +
                    "17. Reporte de técnicos" + "\n" +
                    "18. Consultar inventario crítico" + "\n" +
                    "0. Salir"));

            switch (opcion) {
                case 1:
                    // Registrar cliente
                    String docCliente = JOptionPane.showInputDialog("Ingrese el documento del cliente:");
                    String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre completo del cliente:");
                    String telCliente = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
                    String correoCliente = JOptionPane.showInputDialog("Ingrese el correo electrónico del cliente:");
                    String dirCliente = JOptionPane.showInputDialog("Ingrese la dirección de residencia del cliente:");
                    Cliente cliente = new Cliente(docCliente, nombreCliente, telCliente, correoCliente, dirCliente);
                    JOptionPane.showMessageDialog(null, empresa.registrarCliente(cliente));
                    break;

                case 2:
                    // Registrar equipo
                    String docDueno = JOptionPane.showInputDialog("Ingrese el documento del cliente dueño del equipo:");
                    int categoriaEquipo = Integer.parseInt(JOptionPane.showInputDialog("Categoría del equipo:" + "\n" +
                            "1. Computador portátil" + "\n" +
                            "2. Computador de escritorio" + "\n" +
                            "3. Teléfono inteligente"));
                    String codigoEquipo = JOptionPane.showInputDialog("Código de registro del equipo:");
                    String serieEquipo = JOptionPane.showInputDialog("Número de serie:");
                    String marcaEquipo = JOptionPane.showInputDialog("Marca:");
                    String modeloEquipo = JOptionPane.showInputDialog("Modelo:");
                    int anioEquipo = Integer.parseInt(JOptionPane.showInputDialog("Año de fabricación:"));
                    String estadoFisicoEquipo = JOptionPane.showInputDialog("Descripción del estado físico:");

                    switch (categoriaEquipo) {
                        case 1:
                            double pantallaPortatil = Double.parseDouble(JOptionPane.showInputDialog("Tamaño de pantalla (pulgadas):"));
                            int ramPortatil = Integer.parseInt(JOptionPane.showInputDialog("Memoria RAM (GB):"));
                            int almacPortatil = Integer.parseInt(JOptionPane.showInputDialog("Almacenamiento (GB):"));
                            boolean bateriaExtraible = JOptionPane.showConfirmDialog(null, "¿La batería es extraíble?", "Batería", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                            ComputadorPortatil portatil = new ComputadorPortatil(codigoEquipo, serieEquipo, marcaEquipo, modeloEquipo, anioEquipo, estadoFisicoEquipo, pantallaPortatil, ramPortatil, almacPortatil, bateriaExtraible);
                            JOptionPane.showMessageDialog(null, empresa.registrarEquipo(docDueno, portatil));
                            break;

                        case 2:
                            String procesadorEscritorio = JOptionPane.showInputDialog("Tipo de procesador:");
                            int ramEscritorio = Integer.parseInt(JOptionPane.showInputDialog("Memoria RAM (GB):"));
                            int almacEscritorio = Integer.parseInt(JOptionPane.showInputDialog("Almacenamiento (GB):"));
                            boolean graficaDedicada = JOptionPane.showConfirmDialog(null, "¿Tiene tarjeta gráfica dedicada?", "Gráfica", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                            ComputadorEscritorio escritorio = new ComputadorEscritorio(codigoEquipo, serieEquipo, marcaEquipo, modeloEquipo, anioEquipo, estadoFisicoEquipo, procesadorEscritorio, ramEscritorio, almacEscritorio, graficaDedicada);
                            JOptionPane.showMessageDialog(null, empresa.registrarEquipo(docDueno, escritorio));
                            break;

                        case 3:
                            String sistemaTelefono = JOptionPane.showInputDialog("Sistema operativo:");
                            int almacTelefono = Integer.parseInt(JOptionPane.showInputDialog("Almacenamiento (GB):"));
                            int bateriaTelefono = Integer.parseInt(JOptionPane.showInputDialog("Capacidad de la batería (mAh):"));
                            boolean es5G = JOptionPane.showConfirmDialog(null, "¿Es compatible con 5G?", "5G", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                            TelefonoInteligente telefono = new TelefonoInteligente(codigoEquipo, serieEquipo, marcaEquipo, modeloEquipo, anioEquipo, estadoFisicoEquipo, sistemaTelefono, almacTelefono, bateriaTelefono, es5G);
                            JOptionPane.showMessageDialog(null, empresa.registrarEquipo(docDueno, telefono));
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Categoría de equipo inválida.");
                    }
                    break;

                case 3:
                    // Registrar técnico
                    String codigoTecnico = JOptionPane.showInputDialog("Código del técnico:");
                    String nombreTecnico = JOptionPane.showInputDialog("Nombre del técnico:");
                    String docTecnico = JOptionPane.showInputDialog("Documento de identificación del técnico:");
                    int opcionEspecialidad = Integer.parseInt(JOptionPane.showInputDialog("Especialidad:" + "\n" +
                            "1. Computadores" + "\n" +
                            "2. Dispositivos móviles" + "\n" +
                            "3. Soporte general"));
                    String especialidadTecnico = "";
                    switch (opcionEspecialidad) {
                        case 1:
                            especialidadTecnico = Tecnico.COMPUTADORES;
                            break;
                        case 2:
                            especialidadTecnico = Tecnico.DISPOSITIVOS_MOVILES;
                            break;
                        case 3:
                            especialidadTecnico = Tecnico.SOPORTE_GENERAL;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Especialidad inválida.");
                    }
                    boolean tecnicoActivo = JOptionPane.showConfirmDialog(null, "¿El técnico está activo?", "Estado laboral", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    Tecnico tecnico = new Tecnico(codigoTecnico, nombreTecnico, docTecnico, especialidadTecnico, tecnicoActivo);
                    JOptionPane.showMessageDialog(null, empresa.registrarTecnico(tecnico));
                    break;

                case 4:
                    // Registrar repuesto
                    String codigoRepuesto = JOptionPane.showInputDialog("Código del repuesto:");
                    String nombreRepuesto = JOptionPane.showInputDialog("Nombre del repuesto:");
                    String descripcionRepuesto = JOptionPane.showInputDialog("Descripción del repuesto:");
                    double precioRepuesto = Double.parseDouble(JOptionPane.showInputDialog("Precio unitario:"));
                    int cantidadRepuesto = Integer.parseInt(JOptionPane.showInputDialog("Cantidad disponible:"));
                    int opcionCompatibilidad = Integer.parseInt(JOptionPane.showInputDialog("Categoría de compatibilidad:" + "\n" +
                            "1. Computador portátil" + "\n" +
                            "2. Computador de escritorio" + "\n" +
                            "3. Teléfono inteligente"));
                    String compatibilidadRepuesto = "";
                    switch (opcionCompatibilidad) {
                        case 1:
                            compatibilidadRepuesto = Dispositivo.CATEGORIA_PORTATIL;
                            break;
                        case 2:
                            compatibilidadRepuesto = Dispositivo.CATEGORIA_ESCRITORIO;
                            break;
                        case 3:
                            compatibilidadRepuesto = Dispositivo.CATEGORIA_TELEFONO;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Categoría inválida.");
                    }
                    Repuesto repuesto = new Repuesto(codigoRepuesto, nombreRepuesto, descripcionRepuesto, precioRepuesto, cantidadRepuesto, compatibilidadRepuesto);
                    JOptionPane.showMessageDialog(null, empresa.registrarRepuesto(repuesto));
                    break;

                case 5:
                    // Crear orden de servicio
                    String codigoOrdenNueva = JOptionPane.showInputDialog("Código único de la orden:");
                    String docClienteOrden = JOptionPane.showInputDialog("Documento del cliente:");
                    String observacionesOrden = JOptionPane.showInputDialog("Observaciones generales:");
                    JOptionPane.showMessageDialog(null, empresa.crearOrdenServicio(codigoOrdenNueva, docClienteOrden, observacionesOrden));
                    break;

                case 6:
                    // Agregar detalle a una orden
                    String codigoOrdenDetalle = JOptionPane.showInputDialog("Código de la orden:");
                    String codigoDetalleNuevo = JOptionPane.showInputDialog("Código del detalle:");
                    String codigoEquipoDetalle = JOptionPane.showInputDialog("Código de registro del equipo:");
                    int tipoServicio = Integer.parseInt(JOptionPane.showInputDialog("Tipo de servicio:" + "\n" +
                            "1. Diagnóstico" + "\n" +
                            "2. Mantenimiento preventivo" + "\n" +
                            "3. Reparación correctiva"));
                    String fallaReportada = JOptionPane.showInputDialog("Descripción de la falla reportada:");

                    switch (tipoServicio) {
                        case 1:
                            JOptionPane.showMessageDialog(null, empresa.agregarDetalleOrden(codigoOrdenDetalle, codigoDetalleNuevo, codigoEquipoDetalle, new Diagnostico(), fallaReportada));
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, empresa.agregarDetalleOrden(codigoOrdenDetalle, codigoDetalleNuevo, codigoEquipoDetalle, new MantenimientoPreventivo(), fallaReportada));
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, empresa.agregarDetalleOrden(codigoOrdenDetalle, codigoDetalleNuevo, codigoEquipoDetalle, new ReparacionCorrectiva(), fallaReportada));
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Tipo de servicio inválido.");
                    }
                    break;

                case 7:
                    // Asignar técnico a un detalle
                    String codigoOrdenAsignar = JOptionPane.showInputDialog("Código de la orden:");
                    String codigoDetalleAsignar = JOptionPane.showInputDialog("Código del detalle:");
                    String docTecnicoAsignar = JOptionPane.showInputDialog("Documento del técnico:");
                    JOptionPane.showMessageDialog(null, empresa.asignarTecnico(codigoOrdenAsignar, codigoDetalleAsignar, docTecnicoAsignar));
                    break;

                case 8:
                    // Registrar horas de trabajo o repuestos utilizados
                    String codigoOrdenTrabajo = JOptionPane.showInputDialog("Código de la orden:");
                    String codigoDetalleTrabajo = JOptionPane.showInputDialog("Código del detalle:");
                    int queRegistrar = Integer.parseInt(JOptionPane.showInputDialog("¿Qué desea registrar?" + "\n" +
                            "1. Horas de trabajo" + "\n" +
                            "2. Repuesto utilizado"));

                    switch (queRegistrar) {
                        case 1:
                            double horasTrabajo = Double.parseDouble(JOptionPane.showInputDialog("Horas de trabajo:"));
                            JOptionPane.showMessageDialog(null, empresa.registrarHorasTrabajo(codigoOrdenTrabajo, codigoDetalleTrabajo, horasTrabajo));
                            break;
                        case 2:
                            String codigoRepuestoUsado = JOptionPane.showInputDialog("Código del repuesto:");
                            int cantidadUsada = Integer.parseInt(JOptionPane.showInputDialog("Cantidad utilizada:"));
                            JOptionPane.showMessageDialog(null, empresa.registrarRepuestoUtilizado(codigoOrdenTrabajo, codigoDetalleTrabajo, codigoRepuestoUsado, cantidadUsada));
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                    }
                    break;

                case 9:
                    // Calcular valor de un detalle
                    String codigoOrdenValor = JOptionPane.showInputDialog("Código de la orden:");
                    String codigoDetalleValor = JOptionPane.showInputDialog("Código del detalle:");
                    JOptionPane.showMessageDialog(null, empresa.calcularValorDetalle(codigoOrdenValor, codigoDetalleValor));
                    break;

                case 10:
                    // Calcular valor total de una orden
                    String codigoOrdenTotal = JOptionPane.showInputDialog("Código de la orden:");
                    JOptionPane.showMessageDialog(null, empresa.calcularValorTotalOrden(codigoOrdenTotal));
                    break;

                case 11:
                    // Actualizar estado de un detalle
                    String codigoOrdenEstado = JOptionPane.showInputDialog("Código de la orden:");
                    String codigoDetalleEstado = JOptionPane.showInputDialog("Código del detalle:");
                    int opcionEstado = Integer.parseInt(JOptionPane.showInputDialog("Nuevo estado:" + "\n" +
                            "1. Abierta" + "\n" +
                            "2. En proceso" + "\n" +
                            "3. Finalizada" + "\n" +
                            "4. Entregada" + "\n" +
                            "5. Cancelada"));

                    switch (opcionEstado) {
                        case 1:
                            JOptionPane.showMessageDialog(null, empresa.actualizarEstadoDetalle(codigoOrdenEstado, codigoDetalleEstado, EstadoOrden.ABIERTA));
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, empresa.actualizarEstadoDetalle(codigoOrdenEstado, codigoDetalleEstado, EstadoOrden.EN_PROCESO));
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, empresa.actualizarEstadoDetalle(codigoOrdenEstado, codigoDetalleEstado, EstadoOrden.FINALIZADA));
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, empresa.actualizarEstadoDetalle(codigoOrdenEstado, codigoDetalleEstado, EstadoOrden.ENTREGADA));
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, empresa.actualizarEstadoDetalle(codigoOrdenEstado, codigoDetalleEstado, EstadoOrden.CANCELADA));
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Estado inválido.");
                    }
                    break;

                case 12:
                    // Finalizar orden
                    String codigoOrdenFinalizar = JOptionPane.showInputDialog("Código de la orden a finalizar:");
                    JOptionPane.showMessageDialog(null, empresa.finalizarOrden(codigoOrdenFinalizar));
                    break;

                case 13:
                    // Consultar órdenes de un cliente
                    String docClienteConsulta = JOptionPane.showInputDialog("Documento del cliente:");
                    JOptionPane.showMessageDialog(null, empresa.consultarOrdenesCliente(docClienteConsulta));
                    break;

                case 14:
                    // Consultar trabajos de un técnico
                    String docTecnicoConsulta = JOptionPane.showInputDialog("Documento del técnico:");
                    JOptionPane.showMessageDialog(null, empresa.consultarTrabajosTecnico(docTecnicoConsulta));
                    break;

                case 15:
                    // Consultar equipos con intervenciones pendientes
                    JOptionPane.showMessageDialog(null, empresa.consultarEquiposPendientes());
                    break;

                case 16:
                    // Reporte de servicios
                    JOptionPane.showMessageDialog(null, empresa.generarReporteServicios());
                    break;

                case 17:
                    // Reporte de técnicos
                    JOptionPane.showMessageDialog(null, empresa.generarReporteTecnicos());
                    break;

                case 18:
                    // Consultar inventario crítico
                    JOptionPane.showMessageDialog(null, empresa.consultarInventarioCritico());
                    break;

                case 0:
                    // Salir
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema de gestión de la empresa " + empresa.getNombre());
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 0);

    }
}