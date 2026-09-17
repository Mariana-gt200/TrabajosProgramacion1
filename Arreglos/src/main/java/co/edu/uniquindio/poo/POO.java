package co.edu.uniquindio.poo;

import java.util.List;
import java.util.ArrayList;

public class POO {
    public static class Nota{
        private String nombre;
        private float valor;

        public Nota(String nombre, float valor){
            this.nombre= nombre;
            this.valor= valor;
        }
    }
    //(Como recordatorio, sus atributos son: nombres, apellidos, identificacion, correo,
    // telefono de tipo String, edad de tipo byte y la lista de notas List<Nota> notas).

    public static class Estudiante{
        private String nombre;
        private String apellido;
        private String identificacion;
        private String correo;
        private String telefono;
        private byte edad;
        private List<Nota> notas;

        public Estudiante(String nombre, String apellido, String ID, String correo,
                          String telefono, byte edad){
            this.notas= new ArrayList<>();
            this.nombre= nombre;
            this.apellido= apellido;
            this.identificacion= ID;
            this.correo= correo;
            this.telefono= telefono;
            this.edad= edad;
        }

        //Metodo para agregar una nota a la lista de notas
        public void agregarNota(String nombreNota, float valorNota){
            Nota nuevaNota= new Nota(nombreNota, valorNota);
            notas.add(nuevaNota);
        }
    }

    public static class Curso{
        private String nombre;
        private String codigo;
        private List<Estudiante> estudiantes;

        public Curso(String nombre, String codigo){
            this.nombre = nombre;
            this.codigo = codigo;
            this.estudiantes= new ArrayList<>();
        }

        public void agregarEstudiante(Estudiante estudiante){
            estudiantes.add(estudiante);
        }
    }

    public static void main(String[] args){

        //Crear un curso
        Curso curso= new Curso("11A", "101");

        //Crear un estudiante
        Estudiante estudiante1= new Estudiante("Mariana", "Giraldo", "1092853957",
                "mariana123123@ejemplo.com", "3124372665", (byte)17);

        //Agregar al estudiante
        curso.agregarEstudiante(estudiante1);

        //Agregar notas al estudiante
        estudiante1.agregarNota("Parial 1", 4.0f);
        estudiante1.agregarNota("Parcial 2", 3.5f);
        estudiante1.agregarNota("Parcial 3", 4.5f);
        estudiante1.agregarNota("Parcial 4", 5.0f);
        estudiante1.agregarNota("Parcial 5", 4.8f);
    }
}

