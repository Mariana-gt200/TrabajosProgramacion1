package model;
import java.util.List;
import java.util.ArrayList;
public class Estudiante {
        private String nombre;
        private String apellido;
        private String identificacion;
        private String correo;
        private String telefono;
        private byte edad;

        private Curso ownedByCurso; // Relación con la clase Curso
        private Nota[]= new Nota[5];

    public Estudiante(String nombre, String apellido, String identificacion, String correo, String telefono, byte edad, Curso ownedByCurso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
        this.ownedByCurso = ownedByCurso;
    }

    //Metodo para agregar una nota a la lista de notas
        public void agregarNota(String nombreNota, float valorNota){
            Curso.Nota nuevaNota= new Curso.Nota(nombreNota, valorNota);
            notas.add(nuevaNota);
        }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public List<Curso.Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Curso.Nota> notas) {
        this.notas = notas;
    }
}
