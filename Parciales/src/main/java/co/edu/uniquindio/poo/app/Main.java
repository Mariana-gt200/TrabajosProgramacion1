package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Habitacion;
import co.edu.uniquindio.poo.model.Hotel;
import co.edu.uniquindio.poo.model.Huesped;
import co.edu.uniquindio.poo.model.Reserva;

import javax.swing.*;

public class Main {

    public static void main (String [] args) {
        Hotel hotel = new Hotel("StayPlus", 123456789, "Calle 123 #45-67", "5679037188");

        //Estructura de control para el menú
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de gestión del hotel StayPlus");

        int opcion;
        do {
            opcion = Integer.valueOf(JOptionPane.showInputDialog("Elija la opción a realizar:"+"\n"+
                    "=====Menú=====" +"\n"+
                    "1. Ingresar huésped" + "\n"+
                    "2. Realizar reserva" + "\n"+
                    "3. Realizar Pago de reserva" + "\n"+
                    "4. Consultar huésped"+"\n"+
                    "5. Consultar disponibilidad de habitaciones"+"\n"+
                    "6. Mostrar ocupación semanal"+ "\n"+
                    "7. Mostrar reservas especiales" + "\n"+
                    "8. Imprimir ingresos del hotel"+ "\n"+
                    "0. Salir"));

            switch (opcion) {
                case 1:
                    // Ingresar huésped
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del huésped:");
                    String documento = JOptionPane.showInputDialog("Ingrese el documento del huésped:");
                    String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del huésped:");
                    String edad = JOptionPane.showInputDialog("Ingrese la edad del huésped:");
                    String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad de procedencia del huésped:");
                    int numeroReservas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de reservas del huesped:"));
                    Huesped huesped = new Huesped(nombre, documento, telefono, edad, ciudad, numeroReservas);
                    hotel.agregarHuesped(huesped);
                    JOptionPane.showMessageDialog(null, "Huésped ingresado correctamente");
                    break;

                case 2:
                    String telBuscado = JOptionPane.showInputDialog("Ingrese el teléfono del huésped:");
                    Huesped huespedExistente = hotel.obtenerHuesped(telBuscado);

                    if (huespedExistente != null) {
                        int cantidadReservas = Integer.parseInt(
                                JOptionPane.showInputDialog("¿Cuántas reservas desea realizar?")
                        );

                        for (int i = 0; i < cantidadReservas; i++) {
                            JOptionPane.showMessageDialog(null, "Ingresando reserva " + (i + 1) + " de " + cantidadReservas);

                            // 1. Entradas que ingresa el usuario
                            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código de reserva:"));
                            String fecha = JOptionPane.showInputDialog("Fecha (AAAA-MM-DD):");
                            byte noches = Byte.parseByte(JOptionPane.showInputDialog("Número de noches:"));
                            byte cantHuespedes = Byte.parseByte(JOptionPane.showInputDialog("Número de huéspedes:"));
                            byte habitaciones = Byte.parseByte(JOptionPane.showInputDialog("Cantidad de habitaciones:"));
                            String metodoPago = JOptionPane.showInputDialog("Método de pago:");

                            // 2. Asignaciones automáticas del sistema
                            String estado = "Confirmada";
                            int precioBase = 50000;
                            short pagoCalculado = (short) (precioBase * noches * habitaciones);

                            // 3. Creación del objeto Reserva
                            Reserva nuevaReserva = new Reserva(codigo, fecha, noches, cantHuespedes, habitaciones, estado, metodoPago,
                                    huespedExistente, pagoCalculado, hotel);

                            // 4. Guardar reserva e incrementar contador
                            if (hotel.agregarReserva(nuevaReserva)) {
                                huespedExistente.setCantidadReservas(huespedExistente.getCantidadReservas() + 1);
                                JOptionPane.showMessageDialog(null, "Reserva " + (i + 1) + " registrada, Total: $" + pagoCalculado);
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
                    // Se pide el codigo de reserva
                    int codigoBuscado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la reserva:"));
                    boolean encontrada = false;

                    // Se recorre la lista de reservas
                    for (Reserva r : hotel.getListaReservas()) {
                        if (r != null && r.getCodigo() == codigoBuscado) {
                            encontrada = true;

                            // Se muestra el pago total
                            JOptionPane.showMessageDialog(null,
                                    "=== RESUMEN DE PAGO ===\n" +
                                            "Código de Reserva: " + r.getCodigo() + "\n" +
                                            "Cliente: " + r.getHuesped().getNombre() + "\n" +
                                            "Noches: " + r.getNumeroNoches() + "\n" +
                                            "Pago Total: $" + r.getPagoTotal()
                            );
                            break;
                        }
                    }
                    if (!encontrada) {
                        JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva con el código " + codigoBuscado);
                    }
                    break;

                case 4:
                    // Consultar huésped
                    String telefonoBuscado = JOptionPane.showInputDialog("Ingrese el teléfono del huésped a buscar:");
                    String resultadoHuesped = hotel.buscarHuesped(telefonoBuscado);
                    JOptionPane.showMessageDialog(null, resultadoHuesped);
                    break;

                case 5:
                    // Consultar disponibilidad de habitaciones
                    String mensaje = "==== Disponibilidad de habitaciones ===="+"\n"+
                            "Disponibles: " + hotel.cantidadHabitacionesDisponibles()+"\n"+
                            "Ocupadas: " + hotel.cantidadHabitacionesOcupadas()+"\n"+
                            "En mantenimiento: " + hotel.cantidadHabitacionesMantenimiento()+"\n"+
                            "==== Precio de habitaciones ===="+"\n"+
                            "Habitacion más barata: "+ hotel.obtenerHabitacionMasBarata().getNumero()+" - "+ hotel.obtenerHabitacionMasBarata().getPrecio()+"\n"+
                            "Habitacion más cara: "+ hotel.obtenerHabitacionMasCara().getNumero()+ " - "+ hotel.obtenerHabitacionMasCara().getPrecio()+"\n";
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;

                case 6:
                    // Mostrar ocupación semanal
                    String diaMasOcupado = hotel.obtenerDiaMasOcupado();
                    String diaMenosOcupado = hotel.obtenerDiaMenosOcupado();
                    int totalOcupadasSemana = hotel.calcularHabitacionesOcupadasSemana();
                    String mensaje5 = "Día más ocupado: " + diaMasOcupado +
                            "\nDía menos ocupado: " + diaMenosOcupado +
                            "\nHabitaciones ocupadas en la semana: " + totalOcupadasSemana;

                    JOptionPane.showMessageDialog(null, mensaje5);
                    break;

                case 7:
                    // Pedir el código al usuario
                    int codigoIngresado = Integer.parseInt(
                            JOptionPane.showInputDialog("Ingrese el código de la reserva a verificar:")
                    );
                    boolean reservaEspecialEncontrada = false;
                    //Verificar si el codigo existe en la lista de reservas del hotel
                    for (Reserva r : hotel.getListaReservas()) {
                        if (r != null && r.getCodigo() == codigoIngresado) {
                            reservaEspecialEncontrada = true;

                            // Se evalua si es capicua
                            if (hotel.esCapicua(codigoIngresado)) {
                                JOptionPane.showMessageDialog(null, "¡La reserva " + codigoIngresado + " es ESPECIAL (capicúa)! 🎯");
                            } else {
                                JOptionPane.showMessageDialog(null, "La reserva " + codigoIngresado + " existe pero no es especial ❌");
                            }
                            break;
                        }
                    }
                    if (!reservaEspecialEncontrada) {
                        JOptionPane.showMessageDialog(null, "No existe ninguna reserva registrada con el código " + codigoIngresado);
                    }
                    break;

                case 8:
                    //Mostrar ingresos por fecha
                    String fechaBuscada= JOptionPane.showInputDialog("Ingrese la fecha a buscar: ");
                    String ingresos = hotel.buscarIngresos(fechaBuscada);
                    JOptionPane.showMessageDialog(null, ingresos);
                    break;

                default:
                    // Opción inválida
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
                }

            } while (opcion != 0);

    }
}
