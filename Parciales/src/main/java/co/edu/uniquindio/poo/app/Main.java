package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Habitacion;
import co.edu.uniquindio.poo.model.Hotel;
import co.edu.uniquindio.poo.model.Huesped;
import co.edu.uniquindio.poo.model.Reserva;

import javax.swing.*;

public class Main {

    public static void main (String [] args) {
        Hotel hotel = new Hotel("StayPlus", 123456789, "Calle 123 #45-67", "5679037188");

        // Datos de prueba: habitaciones precargadas (distintos tipos, pisos y precios) ---
        hotel.agregarHabitacion(new Habitacion(101, "Individual", (byte) 1, (byte) 1, 80000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(102, "Doble", (byte) 1, (byte) 2, 120000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(201, "Suite", (byte) 2, (byte) 4, 250000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(101, "Individual", (byte) 1, (byte) 1, 80000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(102, "Doble", (byte) 1, (byte) 2, 120000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(103, "Individual", (byte) 1, (byte) 1, 75000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(201, "Suite", (byte) 2, (byte) 4, 250000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(202, "Doble", (byte) 2, (byte) 2, 130000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(203, "Doble", (byte) 2, (byte) 2, 115000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(301, "Suite", (byte) 3, (byte) 4, 280000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(302, "Individual", (byte) 3, (byte) 1, 70000, "Disponible", hotel));
        hotel.agregarHabitacion(new Habitacion(303, "Doble", (byte) 3, (byte) 2, 125000, "Mantenimiento", hotel));
        hotel.agregarHabitacion(new Habitacion(401, "Suite", (byte) 4, (byte) 5, 300000, "Disponible", hotel));

        //4 Huéspedes
        Huesped h1 = new Huesped("1001234567", "Laura Gómez", (byte) 28, "3001112233", "Armenia", 0);
        Huesped h2 = new Huesped("1002345678", "Carlos Restrepo", (byte) 35, "3002223344", "Pereira", 0);
        Huesped h3 = new Huesped("1003456789", "María Fernanda Ruiz", (byte) 41, "3003334455", "Manizales", 0);
        Huesped h4 = new Huesped("1004567890", "Andrés Torres", (byte) 22, "3004445566", "Bogotá", 0);
        hotel.agregarHuesped(h1);
        hotel.agregarHuesped(h2);
        hotel.agregarHuesped(h3);
        hotel.agregarHuesped(h4);

        //5 Reservas (fechas distintas, para ver la matriz y los ingresos variados)
        Reserva r1 = hotel.crearReserva(1221, "2026-09-21", (byte) 2, (byte) 2, "Efectivo", h1, new int[]{101});          // código capicúa, Lunes
        hotel.agregarReserva(r1);
        h1.setCantidadReservas(h1.getCantidadReservas() + 1);

        Reserva r2 = hotel.crearReserva(3456, "2026-09-22", (byte) 3, (byte) 4, "Tarjeta", h2, new int[]{201, 202});      // Martes, 2 habitaciones
        hotel.agregarReserva(r2);
        h2.setCantidadReservas(h2.getCantidadReservas() + 1);

        Reserva r3 = hotel.crearReserva(7887, "2026-09-22", (byte) 1, (byte) 1, "Transferencia", h3, new int[]{103});     // código capicúa, también Martes
        hotel.agregarReserva(r3);
        h3.setCantidadReservas(h3.getCantidadReservas() + 1);

        Reserva r4 = hotel.crearReserva(5590, "2026-09-24", (byte) 4, (byte) 5, "Efectivo", h4, new int[]{301});          // Jueves
        hotel.agregarReserva(r4);
        h4.setCantidadReservas(h4.getCantidadReservas() + 1);

        Reserva r5 = hotel.crearReserva(2002, "2026-09-22", (byte) 2, (byte) 2, "Tarjeta", h1, new int[]{203});           // código capicúa, también Martes
        hotel.agregarReserva(r5);
        h1.setCantidadReservas(h1.getCantidadReservas() + 1);

        //Fin de datos de prueba


        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de gestión del hotel StayPlus");

        int opcion;
        do {
            opcion = Integer.valueOf(JOptionPane.showInputDialog("Elija la opción a realizar:"+"\n"+
                    "=====Menú=====" +"\n"+
                    "1. Ingresar huésped" + "\n"+
                    "2. Realizar reserva" + "\n"+
                    "3. Consultar pago de reserva" + "\n"+
                    "4. Consultar huésped"+"\n"+
                    "5. Consultar disponibilidad de habitaciones"+"\n"+
                    "6. Mostrar ocupación semanal"+ "\n"+
                    "7. Mostrar reservas especiales" + "\n"+
                    "8. Imprimir ingresos del hotel"+ "\n"+
                    "9. Agregar habitación"+ "\n"+
                    "10. Cambiar estado de habitación"+ "\n"+
                    "0. Salir"));

            switch (opcion) {
                case 1:
                    //Ingresar huésped
                    String documento = JOptionPane.showInputDialog("Ingrese el Documento del huésped:");
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del huésped:");
                    byte edad = Byte.parseByte(JOptionPane.showInputDialog("Ingrese la edad del huésped:"));
                    String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del huésped:");
                    String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad de procedencia del huésped:");
                    int numeroReservas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de reservas del huesped:"));
                    Huesped huesped = new Huesped(documento, nombre, edad, telefono, ciudad, numeroReservas);
                    hotel.agregarHuesped(huesped);
                    JOptionPane.showMessageDialog(null, "Huésped ingresado correctamente");
                    break;

                case 2:
                    //Realizar reserva
                    String telBuscado = JOptionPane.showInputDialog("Ingrese el teléfono del huésped:");
                    Huesped huespedExistente = hotel.obtenerHuesped(telBuscado);

                    if (huespedExistente != null) {
                        int cantidadReservas = Integer.parseInt(
                                JOptionPane.showInputDialog("¿Cuántas reservas desea realizar?"));

                        for (int i = 0; i < cantidadReservas; i++) {
                            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código de reserva:"));
                            String fecha = JOptionPane.showInputDialog("Fecha (AAAA-MM-DD):");
                            byte noches = Byte.parseByte(JOptionPane.showInputDialog("Número de noches:"));
                            byte cantHuespedes = Byte.parseByte(JOptionPane.showInputDialog("Número de huéspedes:"));
                            String metodoPago = JOptionPane.showInputDialog("Método de pago:");
                            String habsTexto = JOptionPane.showInputDialog("Números de habitación separados por coma (ej: 101,102):");

                            String[] partes = habsTexto.split(",");
                            int[] numerosHabitaciones = new int[partes.length];
                            for (int k = 0; k < partes.length; k++) {
                                numerosHabitaciones[k] = Integer.parseInt(partes[k].trim());
                            }

                            Reserva nuevaReserva = hotel.crearReserva(codigo, fecha, noches, cantHuespedes,
                                    metodoPago, huespedExistente, numerosHabitaciones);

                            if (nuevaReserva.getHabitacionesReservadas().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Ninguna habitación indicada está disponible. Reserva no creada.");
                            } else if (hotel.agregarReserva(nuevaReserva)) {
                                huespedExistente.setCantidadReservas(huespedExistente.getCantidadReservas() + 1);
                                JOptionPane.showMessageDialog(null, "Reserva registrada, Total: $" + nuevaReserva.getPagoTotal());
                            } else {
                                JOptionPane.showMessageDialog(null, "El hotel está lleno. No se pudo agregar más reservas.");
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Huésped no encontrado. Debe registrarlo primero en la Opción 1.");
                    }
                    break;

                case 3:
                    // Consultar pago de reserva
                    int codigoBuscado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la reserva:"));
                    JOptionPane.showMessageDialog(null, hotel.consultarPagoReserva(codigoBuscado));
                    break;

                case 4:
                    // Consultar huésped
                    String telefonoBuscado = JOptionPane.showInputDialog("Ingrese el teléfono del huésped a buscar:");
                    JOptionPane.showMessageDialog(null, hotel.buscarHuesped(telefonoBuscado));
                    break;

                case 5:
                    //Consultar disponibilidad de habitaciones
                    JOptionPane.showMessageDialog(null, hotel.reporteDisponibilidadHabitaciones());
                    break;

                case 6:
                    // Mostrar ocupación semanal
                    JOptionPane.showMessageDialog(null, hotel.reporteOcupacionSemanal());
                    break;

                case 7:
                    // Mostrar reservas especiales
                    int codigoIngresado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la reserva a verificar:"));
                    JOptionPane.showMessageDialog(null, hotel.verificarReservaEspecial(codigoIngresado));
                    break;

                case 8:
                    //Mostrar Ingresos del Hotel
                    String fechaBuscada= JOptionPane.showInputDialog("Ingrese la fecha a buscar: ");
                    JOptionPane.showMessageDialog(null, hotel.buscarIngresos(fechaBuscada));
                    break;

                case 9:
                    // Agregar habitación
                    int numeroHab = Integer.parseInt(JOptionPane.showInputDialog("Número de habitación:"));
                    String tipoHab = JOptionPane.showInputDialog("Tipo (Individual/Doble/Suite):");
                    byte pisoHab = Byte.parseByte(JOptionPane.showInputDialog("Piso:"));
                    byte capacidadHab = Byte.parseByte(JOptionPane.showInputDialog("Capacidad máxima:"));
                    int precioHab = Integer.parseInt(JOptionPane.showInputDialog("Precio por noche:"));
                    hotel.agregarHabitacion(new Habitacion(numeroHab, tipoHab, pisoHab, capacidadHab, precioHab, "Disponible", hotel));
                    JOptionPane.showMessageDialog(null, "Habitación agregada correctamente");
                    break;

                case 10:
                    // Cambiar estado de habitación
                    int numHabCambiar = Integer.parseInt(JOptionPane.showInputDialog("Número de la habitación:"));
                    String nuevoEstado = JOptionPane.showInputDialog("Nuevo estado (Disponible / Ocupada / Mantenimiento):");
                    JOptionPane.showMessageDialog(null, hotel.cambiarEstadoHabitacion(numHabCambiar, nuevoEstado));
                    break;

                case 0:
                    // Salir
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema de gestión del hotel StayPlus");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 0);

    }
}