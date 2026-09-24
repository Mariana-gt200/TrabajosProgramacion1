package co.edu.uniquindio.poo.app;

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
                    "1. Consultar huésped"+"\n"+
                    "2. Consultar disponibilidad de habitaciones"+"\n"+
                    "3. Mostrar ocupación semanal"+ "\n"+
                    "4. Mostrar reservas especiales" + "\n"+
                    "5. Imprimir ingresos del hotel"));

            switch (opcion) {
                case 1:
                    //Consulta a huesped por su telefono
                    String documentoBuscado = JOptionPane.showInputDialog("Ingrese el telefono del huésped a buscar:");
                    String resultadoBusqueda = hotel.buscarHuesped(documentoBuscado);
                    if (resultadoBusqueda != null) {
                        JOptionPane.showMessageDialog(null, resultadoBusqueda);
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

        } while (opcion != 0);

    }
}
