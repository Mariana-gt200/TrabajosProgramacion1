package co.edu.uniquindio.poo.model;
import java.util.ArrayList;

public class Hotel {
    private String nombreComercial;
    private int nit;
    private String direccion;
    private String telefono;

    //Declarar Relaciones
    private ArrayList<Huespedes>listaHuespedes;
   private ArrayList<Habitacion> listaHabitaciones;
    private int[] reservas;
    private int[][] ocupacion;


    //Constructor
    public Hotel(String nombreComercial, int nit, String direccion, String telefono) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaHuespedes = new ArrayList <>();
        this.listaHabitaciones= new Arraylist <>();
        reservas= new int[140];
        ocupacion= new int[20][7];

    }
    public void agregarHuesped(Huespedes huesped) {
        listaHuespedes.add(huesped);
    }
    public Huespedes buscarHuesped(String telefono) {
        for (Huesped aux: listaHuespedes) {
            if (aux.getIdentificacion().equals(telefono)){
                return aux;
            }
        } return null;
    }
    public void agregarHabitacion(Habitacion habitaciones){
        listaHabitacion.add(habitaciones);
    }
    public Arraylist <Habitacion> consultarDisponibilidad(){
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion h : listaHabitaciones) {
            if (h.estaDisponible()) {
                disponibles.add(h);
            }
    }

    //Get y Set
    public String getNombreComercial() { return nombreComercial;
    }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial;
    }

    public int getNit() { return nit;
    }
    public void setNit(int nit) { this.nit = nit;
    }

    public String getDireccion() { return direccion;
    }
    public void setDireccion(String direccion) { this.direccion = direccion;
    }

    public String getTelefono() { return telefono;
    }
    public void setTelefono(String telefono) { this.telefono = telefono;
    }

    //get de las listas/arreglos
    public ArrayList<Huespedes> getListaHuespedes() { return listaHuespedes;
    }

    public byte[] getHabitaciones() {
        return habitaciones;
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
                ", habitaciones=" + java.util.Arrays.toString(habitaciones) +
                ", ocupacion=" + java.util.Arrays.deepToString(ocupacion) +
                '}';
    }

    //Administraciones / logica

}
