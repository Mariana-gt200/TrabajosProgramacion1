package co.edu.uniquindio.poo.model;
import java.util.HashMap;
import java.util.Optional;

public class SistemaEnvio {
    private HashMap<Byte, Envio> envios;

    public SistemaEnvio() {
        envios = new HashMap<>();
    }

    //CRUD de envios
    //Agregar envio Nacional------------------------------------------------------------
    public void agregarEnvioNacional(EnvioNacional envioNacional) {
        byte codigo = envioNacional.getCodigo();
        if(envios.containsKey(codigo)){
            System.out.println("El código de envío ya existe. No se puede agregar el envío nacional.");
        }else{
            envios.put(codigo, envioNacional);
            System.out.println("Envío nacional agregado correctamente.");
        }
    }

    //Actualizar envio Nacional
    public void actualizarEnvioNacional(EnvioNacional envioNacional) {
        EnvioNacional envioExistente = (EnvioNacional) envios.get(envioNacional.getCodigo());
        if (envioNacional != null) {
            envioNacional.ingresarDatosEnvio(envioNacional.getCodigo(), envioNacional.getDestinatario(), envioNacional.getPeso());
            envioNacional.setCiudadDestino(envioNacional.getCiudadDestino());
            envioNacional.setRequiereDomicilio(envioNacional.isRequiereDomicilio());
        }
    }

    //Agregar envio Internacional--------------------------------------------------------------------
    public void agregarEnvioInternacional(EnvioInternacional envioInternacional) {
        byte codigo = envioInternacional.getCodigo();
        if(envios.containsKey(codigo)){
            System.out.println("El código de envío ya existe. No se puede agregar el envío internacional.");
        }else{
            envios.put(envioInternacional.getCodigo(), envioInternacional);
            System.out.println("Envío internacional agregado correctamente.");
        }
    }

    //Actualizar envio Internacional
    public void actualizarEnvioInternacional(EnvioInternacional envioInternacional) {
        EnvioInternacional envioExistente = (EnvioInternacional) envios.get(envioInternacional.getCodigo());
        if (envioInternacional != null) {
            envioInternacional.ingresarDatosEnvio(envioInternacional.getCodigo(), envioInternacional.getDestinatario(), envioInternacional.getPeso());
            envioInternacional.setPaisDestino(envioInternacional.getPaisDestino());
            envioInternacional.setRequiereSeguro(envioInternacional.isRequiereSeguro());
        }else{
            System.out.println("El código de envío no existe. No se puede actualizar el envío internacional.");
        }
    }

    //Agregar envio Express--------------------------------------------------------------------
    public void agregarEnvioExpress(EnvioExpress envioExpress) {
        if ( envios.containsKey(envioExpress.getCodigo())) {
            System.out.println("El código de envío ya existe. No se puede agregar el envío express.");
        } else {
            envios.put(envioExpress.getCodigo(), envioExpress);
            System.out.println("Envío express agregado correctamente.");
        }
    }

    //Actualizar envio Express
    public void actualizarEnvioExpress(EnvioExpress envioExpress) {
        EnvioExpress envioExistente = (EnvioExpress) envios.get(envioExpress.getCodigo());
        if (envioExpress != null) {
            envioExpress.ingresarDatosEnvio(envioExpress.getCodigo(), envioExpress.getDestinatario(), envioExpress.getPeso());
            envioExpress.setCiudadDestino(envioExpress.getCiudadDestino());
            envioExpress.setRequiereDomicilio(envioExpress.isRequiereDomicilio());
            envioExpress.setPlazoMaximoEntrega(envioExpress.getPlazoMaximoEntrega());
        }else{
            System.out.println("El código de envío no existe. No se puede actualizar el envío express.");
        }
    }

    //Buscar envio General--------------------------------------------------------------------
    public Optional<Envio> buscarEnvio(byte codigo) {
        return Optional.ofNullable(envios.get(codigo));
    }

    //Eliminar envio General ------------------------------------------------------------------
    public void eliminarEnvioGeneral(byte codigo) {
        envios.remove(codigo);
    }
}
