package co.edu.uniquindio.poo.model;
import java.util.ArrayList;

public class Hotel {
    private String nombreComercial;
    private int nit;
    private String direccion;
    private String telefono;

    //Declarar Relaciones
    private ArrayList<Huesped>listaHuespedes;
    private byte[] habitaciones;
    private int[] reservas;
    private int[][] ocupacion;


    //Constructor
    public Hotel(String nombreComercial, int nit, String direccion, String telefono) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        listaHuespedes = new ArrayList<>();
        habitaciones = new byte[20];
        reservas= new int[140];
        ocupacion= new int[20][7];

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
    public ArrayList<Huesped> getListaHuespedes() { return listaHuespedes;
    }
    public byte[] getHabitaciones() {
        return habitaciones;
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
                ", habitaciones=" + java.util.Arrays.toString(habitaciones) +
                ", reservas=" + java.util.Arrays.toString(reservas) +
                ", ocupacion=" + java.util.Arrays.deepToString(ocupacion) +
                '}';
    }

    //Administraciones / logica

    //Calcular valor total de la reserva
    public int calcularValorTotalReserva(int precioHabitacion, byte numeroNoches, byte numeroHuespedes){
        int valorTotal = precioHabitacion + (numeroNoches * numeroHuespedes);
        return valorTotal;
    }



}
