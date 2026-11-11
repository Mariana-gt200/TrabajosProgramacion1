package co.edu.uniquindio.poo.model;

public class ServicioNotificacion {

    // Constructor vacío porque no hay atributos
    public ServicioNotificacion() {
    }

     //Metodo para enviar una notificación a un cliente
    public boolean enviarNotificacion(String mensaje, Notificable destinatario) {
        if (destinatario == null || mensaje == null || mensaje.isEmpty()) {
            return false;
        }

        switch (destinatario.getCanalPreferido()) {
            case WHATSAPP:
                System.out.println("[WhatsApp] Para " + destinatario.getTelefono() + ": " + mensaje);
                break;
            case GMAIL:
                System.out.println("[Gmail] Para " + destinatario.getEmail() + ": " + mensaje);
                break;
            case SMS:
                System.out.println("[SMS] Para " + destinatario.getTelefono() + ": " + mensaje);
                break;
        }
        return true;
    }
}