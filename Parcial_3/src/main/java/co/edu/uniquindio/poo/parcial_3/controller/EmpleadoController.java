package co.edu.uniquindio.poo.parcial_3.controller;

import co.edu.uniquindio.poo.parcial_3.model.Cajero;
import co.edu.uniquindio.poo.parcial_3.model.Empleado;
import co.edu.uniquindio.poo.parcial_3.model.Panaderia;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoArtesanal;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoIndustrial;
import co.edu.uniquindio.poo.parcial_3.viewController.App;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoController {
    private Panaderia panaderia;

    public EmpleadoController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public Empleado registrarPanaderoArtesanal(String nombre, String documento, String codigo, int minutos) {
        Empleado empleado = new PanaderoArtesanal(nombre, documento, codigo, minutos);
        panaderia.registrarEmpleado(empleado);
        return empleado;
    }

    public Empleado registrarPanaderoIndustrial(String nombre, String documento, String codigo, int minutos) {
        Empleado empleado = new PanaderoIndustrial(nombre, documento, codigo, minutos);
        panaderia.registrarEmpleado(empleado);
        return empleado;
    }

    public Empleado registrarCajero(String nombre, String documento, String codigo, int minutos, String numeroCaja) {
        Empleado empleado = new Cajero(nombre, documento, codigo, minutos, numeroCaja);
        panaderia.registrarEmpleado(empleado);
        return empleado;
    }

    public List<Empleado> obtenerListaEmpleados() {
        return new ArrayList<>(panaderia.getEmpleados());
    }
    empleadoController = new EmpleadoController(App.panaderia);
}
