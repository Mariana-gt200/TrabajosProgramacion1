package co.edu.uniquindio.poo.model;
import java.util.ArrayList;

public class Hotel {
    private String nombreComercial;
    private int nit;
    private String direccion;
    private String telefono;

    //Declarar Relaciones

    private ArrayList<Huesped> listaHuespedes;
    private ArrayList<Habitacion> listaHabitaciones;
    private Reserva [] listaReservas;
    private char[][] ocupacion;


    //Constructor
    public Hotel(String nombreComercial, int nit, String direccion, String telefono) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;

        this.listaHuespedes = new ArrayList<>();
        this.listaHabitaciones = new ArrayList<>();
        this.listaReservas = new Reserva[140];
        this.ocupacion = new char[20][7];

        //Matriz de ocupacion semanal (en el constructor al crearse el hotel
        // de una todas las habitaciones estaran disponibles)
        for(int i= 0; i<ocupacion.length; i++){
            for(int j=0; j<ocupacion[i].length; j++){
                ocupacion[i][j] = 'D';
            }
        }

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
    public Reserva[] getListaReservas() {
        return listaReservas;
    }
    public char[][] getOcupacion() {
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
                ", listaReservas=" + java.util.Arrays.toString(listaReservas) +
                ", ocupacion=" + java.util.Arrays.deepToString(ocupacion) +
                '}';
    }


    //Administraciones / logica

    //Metodo que agrega huespedes a la lista de huespedes
    public void agregarHuesped(Huesped huesped) {
        listaHuespedes.add(huesped);
    }

    //Metodo que busca un huesped por su telefono
    public String buscarHuesped(String telefonoBuscado) {
        for (Huesped aux : getListaHuespedes()) {
            if (aux.getTelefono().equals(telefonoBuscado)) {
                return "Huesped econtrado: " + "\n" + "Nombre: " + aux.getNombre() +
                        "\n" + "Documento: " + aux.getDocumento() +
                        "\n" + "Ciudad de procedencia: " + aux.getCiudadProsedencia() +
                        "\n" + "Cantidad de reservas: " + aux.getCantidadReservas();


            }
        }
        return null;
    }

    //Metodo que agrega habitaciones a la lista de habitaciones
    public void agregarHabitacion(Habitacion habitaciones) {
        listaHabitaciones.add(habitaciones);
    }

    //Arreglo que realiza el pago total de la reserva
    public int calcularPagoTotal(Habitacion tipoHabitacion, Reserva numeroNoches, Reserva cantidadHabitaciones) {
        int pagoTotal = tipoHabitacion.getPrecio() * numeroNoches.getNumeroNoches() * cantidadHabitaciones.getCantidadHabitaciones();
        return pagoTotal;
    }

    //Arreglo que cambia el estado de la habitacion
    public String confirmarReserva(int numeroHabitacion, Habitacion estado) {
        for (Habitacion aux : getListaHabitaciones())
            if (aux.getNumero() == numeroHabitacion) {
                if (aux.getEstado().equalsIgnoreCase("Disponible")) {
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
                if (aux.getEstado().equalsIgnoreCase("Disponible")) {
                    return "La habitación está disponible";
                } else {
                    return "La habitación no está disponible";
                }
            }
        }
        return "Habitación no encontrada";
    }

    //Metodos que retornan la cantidad de habitaciones disponibles, ocupadas y en mantenimiento
    public int cantidadHabitacionesDisponibles() {
        int cantidad = 0;
        for (Habitacion aux : getListaHabitaciones()) {
            if (aux.getEstado().equalsIgnoreCase("Disponible")) {
                cantidad++;
            }
        }
        return cantidad;
    }
    public int cantidadHabitacionesOcupadas() {
        int cantidad = 0;
        for (Habitacion aux : getListaHabitaciones()) {
            if (aux.getEstado().equalsIgnoreCase("Ocupado")) {
                cantidad++;
            }
        }
        return cantidad;
    }
    public int cantidadHabitacionesMantenimiento() {
        int cantidad = 0;
        for (Habitacion aux : getListaHabitaciones()) {
            if (aux.getEstado().equalsIgnoreCase("Mantenimiento")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    //Metodo para obtener habitacion mas cara y mas barata
    public Habitacion obtenerHabitacionMasCara() {
        Habitacion habitacionMasCara = null;
        int precioMaximo = 0;
        for (Habitacion aux : getListaHabitaciones()) {
            if (aux.getPrecio() > precioMaximo) {
                precioMaximo = aux.getPrecio();
                habitacionMasCara = aux;
            }
        }
        return habitacionMasCara;
    }
    public Habitacion obtenerHabitacionMasBarata() {
        Habitacion habitacionMasBarata = null;
        int precioMinimo = Integer.MAX_VALUE;
        for (Habitacion aux : getListaHabitaciones()) {
            if (aux.getPrecio() < precioMinimo) {
                precioMinimo = aux.getPrecio();
                habitacionMasBarata = aux;
            }
        }
        return habitacionMasBarata;
    }

    //Metodo que obtiene el dia mas ocupado y el menos ocupado de la semana
    public String obtenerDiaMasOcupado() {
        int maxOcupadas = -1;
        int diaMasOcupado = 0;

        // Se recorre cada día de la semana (columnas)
        for (int j = 0; j < 7; j++) {
            int ocupadasEnEsteDia = 0;
            // Se recorre cada habitación de ese día (filas)
            for (int i = 0; i < 20; i++) {
                if (ocupacion[i][j] == 'O') {
                    ocupadasEnEsteDia++;
                }
            }
            if (ocupadasEnEsteDia > maxOcupadas) {
                maxOcupadas = ocupadasEnEsteDia;
                diaMasOcupado = j; // Guardamos el índice del día (0 a 6)
            }
        }
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        return diasSemana[diaMasOcupado]; // Retornamos el número del día más ocupado
    }
    public String obtenerDiaMenosOcupado() {
        int minOcupadas = Integer.MAX_VALUE;
        int diaMenosOcupado = 0;

        // Se recorre cada día de la semana (columnas)
        for (int j = 0; j < 7; j++) {
            int ocupadasEnEsteDia = 0;
            // Se recorre cada habitación de ese día (filas)
            for (int i = 0; i < 20; i++) {
                if (ocupacion[i][j] == 'O') {
                    ocupadasEnEsteDia++;
                }
            }
            if (ocupadasEnEsteDia < minOcupadas) {
                minOcupadas = ocupadasEnEsteDia;
                diaMenosOcupado = j; // Guardamos el índice del día (0 a 6)
            }
        }
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        return diasSemana[diaMenosOcupado]; // Retornamos el número del día menos ocupado
    }

    //Metodo para calcular cantidad de habitaciones ocupadas durante la semana
    public int calcularHabitacionesOcupadasSemana() {
        int cantidadOcupadas = 0;
        for (int i = 0; i < ocupacion.length; i++) {
            for (int j = 0; j < ocupacion[i].length; j++) {
                if (ocupacion[i][j] == 'O') {
                    cantidadOcupadas++;

                }
            }
        }
        return cantidadOcupadas;
    }
    //Metodo que identifica habitación especial
    public String identificarHabitacionesEspeciales (){
        String mensaje ="Las habitaciones especiales, son: ";
        boolean hayEspeciales = false;
        for (Habitacion h : listaHabitaciones) {
            if (esCapicua(h.getNumero())) {
                mensaje += h.getNumero() + ", ";
                hayEspeciales = true;
            }
        }
        if (hayEspeciales==false){
            mensaje= "No hay habitaciones especiales";
        }
        return mensaje;
    }

    //Metodo que identifica número capicúa
    public boolean esCapicua(int numero) {
        int original = numero;
        int inverso = 0;
        while (numero > 0) {
            int digito = numero % 10;   // saca el último dígito
            inverso = inverso * 10 + digito; // lo va pegando al inverso
            numero = numero / 10;       // quita ese dígito del número
        }

        return inverso == original;
    }


    //Metodo que enseñe los ingresos obtenidos
    public String buscarIngresos(String fechaBuscada){
        int ingresoTotal=0;
        int numeroReservas=0;
        String nombresHuespedes ="";
        for (Reserva aux: getListaReservas()){
            if (aux != null && aux.getFecha().equals(fechaBuscada)){
               ingresoTotal += aux.getPagoTotal();
               numeroReservas++;
               nombresHuespedes+=aux.getHuesped().getNombre()+"\n";
            }
        }
        if (numeroReservas==0){
            return "Para la fecha buscada, no se realizaron reservas";
        }
        return "El número de reservas para la fecha "+fechaBuscada+" son: \n"+numeroReservas+"\n Huespedes del día: "+nombresHuespedes+"\n Los ingresos de la fecha, fueron: "+ ingresoTotal;
    }
}
