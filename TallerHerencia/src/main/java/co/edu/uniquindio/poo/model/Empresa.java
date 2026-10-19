package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Optional;

public class Empresa {

    private String nombre;
    private ArrayList<Empleado> empleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }
    public Optional<Empleado> registrarEmpleadoPlanta(String nombre, String documento, int edad,
                                                      float salarioBase, CategoriaEmpleado categoria,
                                                      float descuentoSalud, float descuentoPension,
                                                      String cargo, int horasExtra, float valorHoraExtra,
                                                      float auxilioTransporte) {
        EmpleadoPlanta empleado = new EmpleadoPlanta(nombre, documento, edad, salarioBase, categoria,
                descuentoSalud, descuentoPension, cargo, horasExtra, valorHoraExtra, auxilioTransporte);
        if (agregarEmpleado(empleado)) {
            return Optional.of(empleado);
        }
        return Optional.empty();
    }

    public Optional<Empleado> registrarEmpleadoVentas(String nombre, String documento, int edad,
                                                      float salarioBase, CategoriaEmpleado categoria,
                                                      float descuentoSalud, float descuentoPension,
                                                      float totalVentas, float porcentajeComision) {
        EmpleadoVentas empleado = new EmpleadoVentas(nombre, documento, edad, salarioBase, categoria,
                descuentoSalud, descuentoPension, totalVentas, porcentajeComision);
        if (agregarEmpleado(empleado)) {
            return Optional.of(empleado);
        }
        return Optional.empty();
    }

    public Optional<Empleado> registrarEmpleadoTemporal(String nombre, String documento, int edad,
                                                        float salarioBase, CategoriaEmpleado categoria,
                                                        float descuentoSalud, float descuentoPension,
                                                        int diasTrabajados, float valorDia) {
        EmpleadoTemporal empleado = new EmpleadoTemporal(nombre, documento, edad, salarioBase, categoria,
                descuentoSalud, descuentoPension, diasTrabajados, valorDia);
        if (agregarEmpleado(empleado)) {
            return Optional.of(empleado);
        }
        return Optional.empty();
    }

    // Devuelve false si ya existe un empleado con el mismo documento
    public boolean agregarEmpleado(Empleado empleado) {
        if (buscarEmpleado(empleado.getDocumento()).isPresent()) {
            return false;
        }
        empleados.add(empleado);
        return true;
    }

    public String mostrarEmpleados() {
        if (empleados.isEmpty()) {
            return "No hay empleados registrados.";
        }
        String texto = "";
        for (Empleado empleado : empleados) {
            texto += empleado.mostrarInformacion() + "\n--------------------\n";
        }
        return texto;
    }

    public Optional<Empleado> buscarEmpleado(String documento) {
        for (Empleado empleado : empleados) {
            if (empleado.getDocumento().equals(documento)) {
                return Optional.of(empleado);
            }
        }
        return Optional.empty();
    }

    public Optional<Empleado> obtenerEmpleadoMayorSalarioNeto() {
        Empleado mayor = null;
        for (Empleado empleado : empleados) {
            if (mayor == null || empleado.calcularSalarioNeto() > mayor.calcularSalarioNeto()) {
                mayor = empleado;
            }
        }
        return Optional.ofNullable(mayor);
    }

    public float calcularNominaTotal() {
        float total = 0;
        for (Empleado empleado : empleados) {
            total += empleado.calcularSalarioNeto();
        }
        return total;
    }

    public ArrayList<ResumenPago> obtenerResumenesPago() {
        ArrayList<ResumenPago> resumenes = new ArrayList<>();
        for (Empleado empleado : empleados) {
            resumenes.add(empleado.generarResumenPago());
        }
        return resumenes;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
