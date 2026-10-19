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

}
