package co.edu.uniquindio.poo.model;

public class ServicioNotificacion {

    // Constructor vacío porque no hay atributos
    public ServicioNotificacion() {
    }

     //Método para enviar una notificación a un cliente

    public boolean enviarNotificacion(String mensaje, Cliente cliente) {
        if (cliente == null || mensaje == null || mensaje.isEmpty()) {
            return false;
        }

        // FALTA VALIDAR EL CANAL DE ENVÍO DE NOTIFICACIÓN SEGÚN EL CLIENTE ESCOJA

        System.out.println("Enviando notificación a: " + cliente.getNombre());
        System.out.println("Mensaje: " + mensaje);

        return true;
    }
}