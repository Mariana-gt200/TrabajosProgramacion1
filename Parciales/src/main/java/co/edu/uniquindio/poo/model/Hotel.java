package co.edu.uniquindio.poo.model;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nombreComercial;
    private int nit;
    private String direccion;
    private String telefono;

    //Declarar Relaciones

    private ArrayList<Huesped> listaHuespedes;
    private ArrayList<Habitacion> listaHabitaciones;
    private int[] reservas;
    private int[][] ocupacion;


    //Constructor
    public Hotel(String nombreComercial, int nit, String direccion, String telefono) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;

        this.listaHuespedes = new ArrayList<>();
        this.listaHabitaciones = new ArrayList<>();
        reservas = new int[140];
        ocupacion = new int[20][7];

    }


    //Get y Set
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //get de las listas/arreglos
    public ArrayList<Huesped> getListaHuespedes() {
        return listaHuespedes;
    }

    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public int[] getReservas() {
        return reservas;
    }

    public int[][] getOcupacion() {
        return ocupacion;
    }

    //toString
    @Override
    public String toString() {
        return "Hotel{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nit=" + nit +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", listaHuespedes=" + listaHuespedes +
                ", listaHabitaciones=" + listaHabitaciones +
                ", reservas=" + java.util.Arrays.toString(reservas) +
                ", ocupacion=" + java.util.Arrays.deepToString(ocupacion) +
                '}';
    }


    //Administraciones / logica

    //Metodo que agrega huespedes a la lista de huespedes
    public void agregarHuesped(Huesped huesped) {
        listaHuespedes.add(huesped);
    }

    //Metodo que busca un huesped por su telefono
    public String buscarHuesped(String telefono) {
        for (Huesped aux: getListaHuespedes()) {
            if (aux.getTelefono().equals(telefono)){
                return "Huesped econtrado: "+"\n"+"Nombre: "+ aux.getNombre()+
                        "\n"+"Documento: "+ aux.getDocumento()+
                        "\n"+"Ciudad de procedencia: "+ aux.getCiudadProsedencia()+
                        "\n"+"Cantidad de reservas: "+ aux.getCantidadReservas();


            }
        } return null;
    }

    //Metodo que agrega habitaciones a la lista de habitaciones
    public void agregarHabitacion(Habitacion habitaciones){
        listaHabitaciones.add(habitaciones);
    }

    //Arreglo que realiza el pago total de la reserva
    public int calcularPagoTotal(Habitacion tipoHabitacion, Reserva numeroNoches, Reserva cantidadHabitaciones){
    int pagoTotal = tipoHabitacion.getPrecio() * numeroNoches.getNumeroNoches() * cantidadHabitaciones.getCantidadHabitaciones();
    return pagoTotal;
    }

    //Arreglo que cambia el estado de la habitacion
    public String confirmarReserva(int numeroHabitacion, Habitacion estado ){
    for(Habitacion aux : getListaHabitaciones())
     if(aux.getNumero() == numeroHabitacion){
        if(aux.getEstado().equals("Disponible")){
            aux.setEstado("Ocupado");
            return "Reserva confirmada";
        } else {
            return "La habitación no está disponible";
        }
     }
    return "Habitación no encontrada";
    }

    //Metodo que consulta la disponibilidad de una habitacion
    public String consultarDisponibilidad(Hotel listaHabitaciones, byte numeroHabitacion) {
        for (Habitacion aux : getListaHabitaciones()) {
            if (aux.getNumero() == numeroHabitacion) {
                if (aux.getEstado().equals("Disponible")) {
                    return "La habitación está disponible";
                } else {
                    return "La habitación no está disponible";
                }
            }
        }
        return "Habitación no encontrada";
    }
}
