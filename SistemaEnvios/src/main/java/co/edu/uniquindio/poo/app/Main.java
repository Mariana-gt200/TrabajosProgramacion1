package co.edu.uniquindio.poo.app;
import co.edu.uniquindio.poo.model.EnvioExpress;
import co.edu.uniquindio.poo.model.EnvioInternacional;
import co.edu.uniquindio.poo.model.EnvioNacional;
import co.edu.uniquindio.poo.model.SistemaEnvio;

import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {
        SistemaEnvio sistemaEnvio = new SistemaEnvio();
        JOptionPane.showMessageDialog(null, "Bienvenido a nuestro sistema de envíos");
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("-------------Menu-------------\n" +
                    "Que tipo de envio desea realizar? Seleccione una opcion:" +
                    "\n1. EnvioNacional\n" +
                    "2. Envio Internacional" +
                    "\n3. Envio Express\n"+
                    "0. Salir"));
            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Ingrese los siguientes datos:\n");
                    //Ingreso de datos del envio nacional
                    byte codigo = Byte.parseByte(JOptionPane.showInputDialog("Ingrese el código del envío (Maximo 3 numeros): "));
                    String destinatario = JOptionPane.showInputDialog("Ingrese el nombre del destinatario: ");
                    double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso del envío (en kilogramos): "));
                    String ciudadDestino = JOptionPane.showInputDialog("Ingrese la ciudad de destino: ");
                    boolean requiereDomicilio = JOptionPane.showConfirmDialog(null, "¿Requiere domicilio?") == JOptionPane.YES_OPTION;
                    EnvioNacional envioNacional = new EnvioNacional(codigo, destinatario, peso, ciudadDestino, requiereDomicilio);
                    double costoEnvio = envioNacional.calcularCosto();

                    // Guardar el envío nacional en el sistema
                    sistemaEnvio.agregarEnvioNacional(envioNacional);
                    JOptionPane.showMessageDialog(null, "Envío nacional agregado correctamente.");
                    JOptionPane.showMessageDialog(null, "El costo del envío nacional es: " + costoEnvio);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Ingrese los siguientes datos:\n");
                    //Ingreso de datos del envio internacional
                    byte codigoInt = Byte.parseByte(JOptionPane.showInputDialog("Ingrese el código del envío (Maximo 3 numeros): "));
                    String destinatarioInt = JOptionPane.showInputDialog("Ingrese el nombre del destinatario: ");
                    double pesoInt = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso del envío (en kilogramos): "));
                    String paisDestino = JOptionPane.showInputDialog("Ingrese el país de destino: ");
                    boolean requiereSeguro = JOptionPane.showConfirmDialog(null, "¿Requiere seguro?") == JOptionPane.YES_OPTION;
                    EnvioInternacional envioInternacional = new EnvioInternacional(codigoInt, destinatarioInt, pesoInt, paisDestino, requiereSeguro);
                    double costoEnvioInt = envioInternacional.calcularCosto();

                    // Guardar el envío internacional en el sistema
                    sistemaEnvio.agregarEnvioInternacional(envioInternacional);
                    JOptionPane.showMessageDialog(null, "Envío internacional agregado correctamente.");
                    JOptionPane.showMessageDialog(null, "El costo del envío internacional es: " + costoEnvioInt);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Ingrese los siguientes datos:\n");
                    //Ingreso de datos del envio express
                    byte codigoExp = Byte.parseByte(JOptionPane.showInputDialog("Ingrese el código del envío (Maximo 3 numeros): "));
                    String destinatarioExp = JOptionPane.showInputDialog("Ingrese el nombre del destinatario: ");
                    double pesoExp = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso del envío (en kilogramos): "));
                    String ciudadDestinoExp = JOptionPane.showInputDialog("Ingrese la ciudad de destino: ");
                    boolean requiereDomicilioExp = JOptionPane.showConfirmDialog(null, "¿Requiere domicilio?") == JOptionPane.YES_OPTION;
                    double plazoMaximoEntrega = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el plazo máximo de entrega (en horas): "));
                    EnvioExpress envioExpress = new EnvioExpress(codigoExp, destinatarioExp, pesoExp, ciudadDestinoExp, requiereDomicilioExp, plazoMaximoEntrega);
                    double costoEnvioExp = envioExpress.calcularCosto();
                    // Guardar el envío express en el sistema
                    sistemaEnvio.agregarEnvioExpress(envioExpress);
                    JOptionPane.showMessageDialog(null, "Envío express agregado correctamente.");
                    JOptionPane.showMessageDialog(null, "El costo del envío express es: " + costoEnvioExp);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Gracias por usar nuestro sistema de envíos");
                    break;
            }
        } while (opcion != 0);
    }
}