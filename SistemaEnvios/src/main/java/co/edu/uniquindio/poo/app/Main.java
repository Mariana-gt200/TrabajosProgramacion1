package co.edu.uniquindio.poo.app;
import co.edu.uniquindio.poo.model.*;

import javax.swing.JOptionPane;
import java.util.Optional;

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

                case 4:
                    // Eliminar un envío
                    byte codigoEliminar = Byte.parseByte(JOptionPane.showInputDialog("Ingrese el código del envío que desea eliminar: "));
                    sistemaEnvio.eliminarEnvioGeneral(codigoEliminar);
                    break;
                case 5:
                    // Actualizar un envío
                    byte codigoActualizar = Byte.parseByte(JOptionPane.showInputDialog("Ingrese el código del envío que desea actualizar: "));
                    Optional<Envio> envioActualizar = sistemaEnvio.buscarEnvio(codigoActualizar);
                    if (envioActualizar.isPresent()) {
                        if (envioActualizar.get() instanceof EnvioNacional) {
                            EnvioNacional envioNacionalActualizar = (EnvioNacional) envioActualizar.get();
                            String destinatarioNuevo = JOptionPane.showInputDialog("Ingrese el nuevo nombre del destinatario: ");
                            double pesoNuevo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo peso del envío (en kilogramos): "));
                            String ciudadDestinoNueva = JOptionPane.showInputDialog("Ingrese la nueva ciudad de destino: ");
                            boolean requiereDomicilioNuevo = JOptionPane.showConfirmDialog(null, "¿Requiere domicilio?") == JOptionPane.YES_OPTION;
                            envioNacionalActualizar.setDestinatario(destinatarioNuevo);
                            envioNacionalActualizar.setPeso(pesoNuevo);
                            envioNacionalActualizar.setCiudadDestino(ciudadDestinoNueva);
                            envioNacionalActualizar.setRequiereDomicilio(requiereDomicilioNuevo);
                            sistemaEnvio.actualizarEnvioNacional(envioNacionalActualizar);
                        } else if (envioActualizar.get() instanceof EnvioInternacional) {
                            EnvioInternacional envioInternacionalActualizar = (EnvioInternacional) envioActualizar.get();
                            String destinatarioNuevo = JOptionPane.showInputDialog("Ingrese el nuevo nombre del destinatario: ");
                            double pesoNuevo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo peso del envío (en kilogramos): "));
                            String paisDestinoNuevo = JOptionPane.showInputDialog("Ingrese el nuevo país de destino: ");
                            boolean requiereSeguroNuevo = JOptionPane.showConfirmDialog(null, "¿Requiere seguro?") == JOptionPane.YES_OPTION;
                            envioInternacionalActualizar.setDestinatario(destinatarioNuevo);
                            envioInternacionalActualizar.setPeso(pesoNuevo);
                            envioInternacionalActualizar.setPaisDestino(paisDestinoNuevo);
                            envioInternacionalActualizar.setRequiereSeguro(requiereSeguroNuevo);
                            sistemaEnvio.actualizarEnvioInternacional(envioInternacionalActualizar);
                        } else if (envioActualizar.get() instanceof EnvioExpress) {
                            EnvioExpress envioExpressActualizar = (EnvioExpress) envioActualizar.get();
                            String destinatarioNuevo = JOptionPane.showInputDialog("Ingrese el nuevo nombre del destinatario: ");
                            double pesoNuevo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo peso del envío (en kilogramos): "));
                            String ciudadDestinoNueva = JOptionPane.showInputDialog("Ingrese la nueva ciudad de destino: ");
                            boolean requiereDomicilioNuevo = JOptionPane.showConfirmDialog(null, "¿Requiere domicilio?") == JOptionPane.YES_OPTION;
                            double plazoMaximoEntregaNuevo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo plazo máximo de entrega (en horas): "));
                            envioExpressActualizar.setDestinatario(destinatarioNuevo);
                            envioExpressActualizar.setPeso(pesoNuevo);
                            envioExpressActualizar.setCiudadDestino(ciudadDestinoNueva);
                            envioExpressActualizar.setRequiereDomicilio(requiereDomicilioNuevo);
                            envioExpressActualizar.setPlazoMaximoEntrega(plazoMaximoEntregaNuevo);
                            sistemaEnvio.actualizarEnvioExpress(envioExpressActualizar);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El código de envío no existe. No se puede actualizar el envío.");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Gracias por usar nuestro sistema de envíos");
                    break;
            }
        } while (opcion != 0);
    }
}