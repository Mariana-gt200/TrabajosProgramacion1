package model;

import java.util.List;
import java.util.ArrayList;

public class Curso {

        //declarar atributos
        private String nombre;
        private String codigo;

        //declarar relaciones
        private List<Estudiante> estudiantes;

        //Constructor: Es el metodo que inicializa los atributos de la clase.
        public Curso(String nombre, String codigo){
            this.nombre = nombre;
            this.codigo = codigo;
            this.estudiantes= new ArrayList<>();
        }
        public void agregarEstudiante(Estudiante estudiante){
            estudiantes.add(estudiante);
        }

        //set y get
        //Nombre
        public void setNombre(String nombre){
            this.nombre= nombre;
        }
        public String getNombre(){
            return nombre;
        }
        //Codigo del curso
        public void setCodigo(String codigo){
            this.codigo= codigo;
        }
        public String getCodigo() {
            return codigo;
        }

        //Estudiantes
        public void setEstudiantes(List<Estudiante>estudiantes){
            this.estudiantes = estudiantes;
        }

        public String getEstudiantes(){
            return estudiantes.toString();

        }

        //Logica
        //CRUD del estudiante

        //crear
    public String registrarEstudiante(String nombre, String apellido, String identificacion, String correo, String telefono, byte edad, this){
            String mensaje= "";
            Estudiante buscado= obtenerEstudiante;
            if(buscado != null){
                return "El estudiante que usted intenta buscar ya se encuentra registrado.";
            }else{
                Estudiante estudianteNuevo = new Estudiante(nombre, apellido, identificacion, correo, telefono, edad, this);
                estudiantes.add(estudianteNuevo);
                mensaje = "Estudiante registrado con exito";
            }
            return mensaje;
    }

    public Estudiante obtenerEstudiante(String identificacion){
            Estudiante estudianteEncontrado = null;
            for(Estudiante aux: estudiantes){
                if(aux.getIdentificacion().equals(identificacion)){
                    return aux;
                }
            }
            return estudianteEncontrado;
    }
    }


