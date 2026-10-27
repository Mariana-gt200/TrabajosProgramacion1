package co.edu.uniquindio.poo.model;

public class ServicioNotificacion {

    // Constructor vacío porque no hay atributos
    public ServicioNotificacion() {
    }

     //Metodo para enviar una notificación a un cliente

    switch (cliente.getCanalPreferido()) {
        case WHATSAPP:
            System.out.println("[WhatsApp] Para " + cliente.getTelefono() + ": " + mensaje);
            break;
        case GMAIL:
            System.out.println("[Gmail] Para " + cliente.getEmail() + ": " + mensaje);
            break;
        case SMS:
            System.out.println("[SMS] Para " + cliente.getTelefono() + ": " + mensaje);
            break;
    }
return true;
}