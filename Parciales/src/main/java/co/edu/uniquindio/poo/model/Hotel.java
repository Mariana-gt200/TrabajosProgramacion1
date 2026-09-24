package co.edu.uniquindio.poo.model;
import java.util.ArrayList;
import java.time.LocalDate;

public class Hotel {
    private String nombreComercial;
    private int nit;
    private String direccion;
    private String telefono;

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

        for(int i= 0; i<ocupacion.length; i++){
            for(int j=0; j<ocupacion[i].length; j++){
                ocupacion[i][j] = 'D';
            }
        }
    }

    //Get y Set
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    public int getNit() { return nit; }
    public void setNit(int nit) { this.nit = nit; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public ArrayList<Huesped> getListaHuespedes() { return listaHuespedes; }
    public ArrayList<Habitacion> getListaHabitaciones() { return listaHabitaciones; }
    public Reserva[] getListaReservas() { return listaReservas; }
    public char[][] getOcupacion() { return ocupacion; }

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

    // Huéspedes ========================================

    public void agregarHuesped(Huesped huesped) {
        listaHuespedes.add(huesped);
    }

    public Huesped obtenerHuesped(String telefono) {
        for (Huesped h : listaHuespedes) {
            if (h.getTelefono().equals(telefono)) {
                return h;
            }
        }
        return null;
    }

    public String buscarHuesped(String telefonoBuscado) {
        Huesped aux = obtenerHuesped(telefonoBuscado);
        if (aux == null) {
            return "No se encontró ningún huésped con ese teléfono";
        }
        return "Huesped encontrado: " + "\n" + "Nombre: " + aux.getNombre() +
                "\n" + "Documento: " + aux.getDocumento() +
                "\n" + "Ciudad de procedencia: " + aux.getCiudadProsedencia() +
                "\n" + "Cantidad de reservas: " + aux.getCantidadReservas();
    }

    // Habitaciones =================================

    public void agregarHabitacion(Habitacion habitacion) {
        listaHabitaciones.add(habitacion);
    }

    public Habitacion buscarHabitacionPorNumero(int numero) {
        for (Habitacion h : listaHabitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }

    public int cantidadHabitacionesDisponibles() {
        int cantidad = 0;
        for (Habitacion aux : listaHabitaciones) {
            if (aux.getEstado().equalsIgnoreCase("Disponible")) cantidad++;
        }
        return cantidad;
    }

    public int cantidadHabitacionesOcupadas() {
        int cantidad = 0;
        for (Habitacion aux : listaHabitaciones) {
            if (aux.getEstado().equalsIgnoreCase("Ocupada")) cantidad++;
        }
        return cantidad;
    }

    public int cantidadHabitacionesMantenimiento() {
        int cantidad = 0;
        for (Habitacion aux : listaHabitaciones) {
            if (aux.getEstado().equalsIgnoreCase("Mantenimiento")) cantidad++;
        }
        return cantidad;
    }

    public String cambiarEstadoHabitacion(int numeroHabitacion, String nuevoEstado) {
        Habitacion habitacion = buscarHabitacionPorNumero(numeroHabitacion);
        if (habitacion == null) {
            return "Habitación no encontrada";
        }
        habitacion.setEstado(nuevoEstado);
        return "La habitación " + numeroHabitacion + " ahora está en estado: " + nuevoEstado;
    }

    public Habitacion obtenerHabitacionMasCara() {
        Habitacion habitacionMasCara = null;
        int precioMaximo = -1;
        for (Habitacion aux : listaHabitaciones) {
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
        for (Habitacion aux : listaHabitaciones) {
            if (aux.getPrecio() < precioMinimo) {
                precioMinimo = aux.getPrecio();
                habitacionMasBarata = aux;
            }
        }
        return habitacionMasBarata;
    }

    //Reporte completo de disponibilidad, listo para mostrar en el menú
    public String reporteDisponibilidadHabitaciones() {
        Habitacion masBarata = obtenerHabitacionMasBarata();
        Habitacion masCara = obtenerHabitacionMasCara();

        String reporte = "==== Disponibilidad de habitaciones ====\n" +
                "Disponibles: " + cantidadHabitacionesDisponibles() + "\n" +
                "Ocupadas: " + cantidadHabitacionesOcupadas() + "\n" +
                "En mantenimiento: " + cantidadHabitacionesMantenimiento() + "\n" +
                "==== Precio de habitaciones ====\n";

        if (masBarata != null && masCara != null) {
            reporte += "Habitación más barata: " + masBarata.getNumero() + " - $" + masBarata.getPrecio() + "\n" +
                    "Habitación más cara: " + masCara.getNumero() + " - $" + masCara.getPrecio() + "\n";
        } else {
            reporte += "No hay habitaciones registradas.\n";
        }
        return reporte;
    }

    // Ocupacion semanal ==============================================

    private void marcarOcupacion(Habitacion habitacion, int diaIndex) {
        int fila = listaHabitaciones.indexOf(habitacion);
        if (fila != -1 && diaIndex >= 0 && diaIndex < 7) {
            ocupacion[fila][diaIndex] = 'O';
        }
    }

    public String obtenerDiaMasOcupado() {
        int maxOcupadas = -1;
        int diaMasOcupado = 0;
        for (int j = 0; j < 7; j++) {
            int ocupadasEnEsteDia = 0;
            for (int i = 0; i < 20; i++) {
                if (ocupacion[i][j] == 'O') ocupadasEnEsteDia++;
            }
            if (ocupadasEnEsteDia > maxOcupadas) {
                maxOcupadas = ocupadasEnEsteDia;
                diaMasOcupado = j;
            }
        }
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        return diasSemana[diaMasOcupado];
    }

    public String obtenerDiaMenosOcupado() {
        int minOcupadas = Integer.MAX_VALUE;
        int diaMenosOcupado = 0;
        for (int j = 0; j < 7; j++) {
            int ocupadasEnEsteDia = 0;
            for (int i = 0; i < 20; i++) {
                if (ocupacion[i][j] == 'O') ocupadasEnEsteDia++;
            }
            if (ocupadasEnEsteDia < minOcupadas) {
                minOcupadas = ocupadasEnEsteDia;
                diaMenosOcupado = j;
            }
        }
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        return diasSemana[diaMenosOcupado];
    }

    public int calcularHabitacionesOcupadasSemana() {
        int cantidadOcupadas = 0;
        for (int i = 0; i < ocupacion.length; i++) {
            for (int j = 0; j < ocupacion[i].length; j++) {
                if (ocupacion[i][j] == 'O') cantidadOcupadas++;
            }
        }
        return cantidadOcupadas;
    }

    //Reporte completo de ocupación semanal, listo para mostrar en el menú
    public String reporteOcupacionSemanal() {
        return "Día más ocupado: " + obtenerDiaMasOcupado() +
                "\nDía menos ocupado: " + obtenerDiaMenosOcupado() +
                "\nHabitaciones ocupadas en la semana: " + calcularHabitacionesOcupadasSemana();
    }

    // Reservas ================================================================0

    public boolean agregarReserva(Reserva reserva) {
        for (int i = 0; i < listaReservas.length; i++) {
            if (listaReservas[i] == null) {
                listaReservas[i] = reserva;
                return true;
            }
        }
        return false;
    }

    public Reserva buscarReservaPorCodigo(int codigo) {
        for (Reserva r : listaReservas) {
            if (r != null && r.getCodigo() == codigo) {
                return r;
            }
        }
        return null;
    }

    //Metodo para crear la reserva completa
    //cambia el estado de cada habitación y marca la matriz de ocupación.
    public Reserva crearReserva(int codigo, String fecha, byte numeroNoches, byte numeroHuespedes,
                                String metodoPago, Huesped huesped, int[] numerosHabitaciones) {

        ArrayList<Habitacion> habitacionesSeleccionadas = new ArrayList<>();
        int pagoTotal = 0;
        int diaIndex = LocalDate.parse(fecha).getDayOfWeek().getValue() - 1;

        for (int numHab : numerosHabitaciones) {
            Habitacion habitacion = buscarHabitacionPorNumero(numHab);

            if (habitacion == null) {
                System.out.println("Aviso: la habitación " + numHab + " no existe.");
                continue;
            }
            if (!habitacion.getEstado().equalsIgnoreCase("Disponible")) {
                System.out.println("Aviso: la habitación " + numHab + " existe pero no está disponible (" + habitacion.getEstado() + ").");
                continue;
            }

            habitacionesSeleccionadas.add(habitacion);
            pagoTotal += habitacion.getPrecio() * numeroNoches;
            habitacion.setEstado("Ocupada");
            marcarOcupacion(habitacion, diaIndex);
        }

        byte cantidadHabitaciones = (byte) habitacionesSeleccionadas.size();

        return new Reserva(codigo, fecha, numeroNoches, numeroHuespedes, "Confirmada", metodoPago,
                pagoTotal, cantidadHabitaciones, huesped, habitacionesSeleccionadas, this);
    }
    public String consultarPagoReserva(int codigo) {
        Reserva reserva = buscarReservaPorCodigo(codigo);
        if (reserva == null) {
            return "No se encontró ninguna reserva con el código " + codigo;
        }
        return "=== RESUMEN DE PAGO ===\n" +
                "Código de Reserva: " + reserva.getCodigo() + "\n" +
                "Cliente: " + reserva.getHuesped().getNombre() + "\n" +
                "Noches: " + reserva.getNumeroNoches() + "\n" +
                "Pago Total: $" + reserva.getPagoTotal();
    }

    public String verificarReservaEspecial(int codigo) {
        Reserva reserva = buscarReservaPorCodigo(codigo);
        if (reserva == null) {
            return "No existe ninguna reserva registrada con el código " + codigo;
        }
        return esCapicua(codigo)
                ? "¡La reserva " + codigo + " es especial (capicúa)"
                : "La reserva " + codigo + " existe pero no es especial";
    }

    public boolean esCapicua(int numero) {
        int original = numero;
        int inverso = 0;
        while (numero > 0) {
            int digito = numero % 10;
            inverso = inverso * 10 + digito;
            numero = numero / 10;
        }
        return inverso == original;
    }

    // ================= INGRESOS =================

    public String buscarIngresos(String fechaBuscada){
        int ingresoTotal=0;
        int numeroReservas=0;
        String nombresHuespedes ="";
        for (Reserva aux: listaReservas){
            if (aux != null && aux.getFecha().equals(fechaBuscada)){
                ingresoTotal += aux.getPagoTotal();
                numeroReservas++;
                nombresHuespedes+=aux.getHuesped().getNombre()+"\n";
            }
        }
        if (numeroReservas==0){
            return "Para la fecha buscada, no se realizaron reservas";
        }
        return "El número de reservas para la fecha "+fechaBuscada+" son: \n"+numeroReservas+"\n Huespedes del día: "+
                nombresHuespedes+"\n Los ingresos de la fecha, fueron: "+ ingresoTotal;
    }
}