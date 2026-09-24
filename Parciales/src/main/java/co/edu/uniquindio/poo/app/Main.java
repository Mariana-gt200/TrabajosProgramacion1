package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Habitacion;
import co.edu.uniquindio.poo.model.Hotel;
import co.edu.uniquindio.poo.model.Huesped;
import javax.swing.*;

public class Main {

    public static void main () {
        Hotel hotel = new Hotel("StayPlus", 123456789, "Calle 123 #45-67", "5679037188");

        //Estructura de control para el menú
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de gestión del hotel StayPlus");

        int opcion;
        do {
            opcion = Integer.valueOf(JOptionPane.showInputDialog("Elija la opción a realizar:"+"\n"+
                    "=====Menú=====" +"\n"+
                    "1. Ingresar huésped" + "\n"+
                    "2. Realizar reserva" + "\n"+
                    "3. Consultar huésped"+"\n"+
                    "4. Consultar disponibilidad de habitaciones"+"\n"+
                    "5. Mostrar ocupación semanal"+ "\n"+
                    "6. Mostrar reservas especiales" + "\n"+
                    "7. Imprimir ingresos del hotel"+ "\n"+
                    "0. Salir"));

            switch (opcion) {
                case 1:
                    // Ingresar huésped
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del huésped:");
                    String documento = JOptionPane.showInputDialog("Ingrese el documento del huésped:");
                    String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del huésped:");
                    String edad = JOptionPane.showInputDialog("Ingrese la edad del huésped:");
                    String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad de procedencia del huésped:");
                    Huesped huesped = new Huesped(nombre, documento, telefono, edad, ciudad, 0);
                    hotel.getListaHuespedes().add(huesped);
                    JOptionPane.showMessageDialog(null, "Huésped ingresado correctamente");
                    break;

                case 2:
                    // Realizar reserva
                    break;

                case 3:
                    // Consultar huésped
                    String telefonoBuscado = JOptionPane.showInputDialog("Ingrese el teléfono del huésped a buscar:");
                    String resultadoHuesped = hotel.buscarHuesped(telefonoBuscado);
                    JOptionPane.showMessageDialog(null, resultadoHuesped);
                    break;

                case 4:
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

                case 5:
                    // Mostrar ocupación semanal
                    String diaMasOcupado = hotel.obtenerDiaMasOcupado();
                    String diaMenosOcupado = hotel.obtenerDiaMenosOcupado();
                    int totalOcupadasSemana = hotel.calcularHabitacionesOcupadasSemana();
                    String mensaje5 = "Día más ocupado: " + diaMasOcupado +
                            "\nDía menos ocupado: " + diaMenosOcupado +
                            "\nHabitaciones ocupadas en la semana: " + totalOcupadasSemana;

                    JOptionPane.showMessageDialog(null, mensaje5);
                    break;

                case 6:
                    // Mostrar reservas especiales
                    break;
                case 7:
                    //Mostrar ingresos por fecha
                    JOptionPane.showInputDialog("Ingrese la fecha a buscar: ");
                    break;
                case 0:}
                    // Salir
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema de gestión del hotel StayPlus");
                    break;
            } while (opcion != 0);

    }
}
