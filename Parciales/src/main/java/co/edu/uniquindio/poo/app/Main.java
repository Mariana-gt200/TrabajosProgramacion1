package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Hotel;
import co.edu.uniquindio.poo.model.Huespedes;

public class Main {
    String buscarHuesped = JOptionPane.showMessageDialog("Bienvenido al sistema de gestión del hotel StayPlus");

    public static void main (){
        Hotel hotel = new Hotel(nombreComercial, nit, direccion, telefono);
        Huespedes huespedes = new Huespedes(documento, nombre, edad, telefono, ciudadProsedencia);
    }
    int opcion;
    do{
        opcion = integer.valueOf(JOptionPane.showInputDialog("Elija la opción a realizar+\n+===Menú==="+
                "1. Consultar huésped+\n+2. Consultar disponibilidad de habitaciones+\n+3. Mostrar ocupación semanal+\n+4. Mostrar reservas especiales" +
                "\n+Imprimir ingresos del hotel" +
                ""));
        Switch (opcion){
            case 1:
                String documentoBuscado = JOptionPane.showInputDialog("Ingrese el documento del huésped a buscar:");
                Huespedes huespedEncontrado = hotel.buscarHuesped;
                if (huespedEncontrado != null) {
                    JOptionPane.showMessageDialog(null, huespedEncontrado.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Huésped no encontrado");
                }
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:
                break;
        }

    } while (opcion!=0);
}
