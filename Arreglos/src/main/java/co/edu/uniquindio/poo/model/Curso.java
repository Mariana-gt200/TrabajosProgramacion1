package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Esta clase representa un curso de una universidad
 * @version 1.0
 * @author Mariana Giraldo Tabares
 * @fecha : 17/09/26
 */
public class Curso { // singular, el nombre de la clase debe ser la primer letra en mayuscula

    //declaracion de atributos
    // modificador de acceso -> tipo de dato -> nombre del atributo
    private String nombre;//por defecto es null
    private String codigo;
    // declarar las relaciones
    private ArrayList<Estudiante> listaEstudiantes;

    /*
     * Constructor: Es el metodo que permite darle valores o inicializar
     * los valores de los atributos de las clases
     */
    public Curso(String nombre,String codigo){//parametros informacion que entra
        //inicializar las variables
        this.nombre = nombre;
        this.codigo = codigo;
        listaEstudiantes = new ArrayList<>();
    }

    // set y get

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public String getCodigo(){
        return codigo;
    }
    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes){
        this.listaEstudiantes = listaEstudiantes;
    }
    public ArrayList<Estudiante> getListaEstudiantes(){
        return listaEstudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", listaEstudiantes=" + listaEstudiantes +
                '}';
    }

    //logica

    //CRUD del estudiante

    //crear
    public String registrarEstudiante(String nombres,String apellidos,String identificacion,
                                      byte edad,String correo,String telefono){
        String mensaje = "";
        Estudiante buscado = buscarEstudiante(identificacion);
        if(buscado != null){
            return "Error el estudiante que usted desea registra ya se encuentra registrado";
        }else{
            Estudiante estudianteNuevo = new Estudiante(nombres,apellidos,identificacion,edad,correo,telefono,this);
            listaEstudiantes.add(estudianteNuevo);
            mensaje = "Estudiante registrado con exito";
        }
        return mensaje;
    }
    public Estudiante buscarEstudiante (String identificacion){
        for(Estudiante aux : listaEstudiantes){
            if(aux.getIdentificacion().equals(identificacion)){
                return aux;
            }
        }
        return null;
    }
    public boolean eliminarEstudiante(String identificacion) {
        Estudiante estudianteEncontrado = buscarEstudiante(identificacion);
        if(estudianteEncontrado != null){
            listaEstudiantes.remove(estudianteEncontrado);
            return true;
        }else return false;
    }

    public boolean actualizarEstudiante(String identificacionAntigua, String identificacionNueva,
                                        String nombresNuevos, String apellidosNuevos,
                                        byte edadEstudianteNueva, String correoNuevo, String telefonoNuevo) {
        Estudiante estudianteEncontrado = buscarEstudiante(identificacionAntigua);
        if(estudianteEncontrado != null){
            estudianteEncontrado.setApellidos(apellidosNuevos);
            estudianteEncontrado.setNombres(nombresNuevos);
            estudianteEncontrado.setIdentificacion(identificacionNueva);
            estudianteEncontrado.setEdad(edadEstudianteNueva);
            estudianteEncontrado.setCorreo(correoNuevo);
            estudianteEncontrado.setTelefono(telefonoNuevo);
            return true;
        }else return false;
    }

    public String registrarNotaEstudiante(String identificacion, String nombreNota, float valorNota) {

        Estudiante estudianteEncontrado = buscarEstudiante(identificacion);
        if(estudianteEncontrado != null){
            return estudianteEncontrado.registrarNota(nombreNota,valorNota);
        }else{
            return "El estudiante no esta registrado";
        }
    }

    public Nota buscarNota(String nombre, Estudiante estudiante ){
        for(Nota aux : estudiante.getListaNotas()){
            if(aux != null && aux.getNombre().equals(nombre)){
                return aux;
            }
        }
        return null;
    }
    //========================= TAREA =========================
    public double calcularPromedio(Estudiante estudiante){
        double promedio= 0;
        double sumaNotas = 0;
        int contadorNotas = 0;
        Nota[] notas = estudiante.getListaNotas();
        for(int i= 0; i<notas.length; i++){
            if(notas[i] != null){
                sumaNotas += notas[i].getValor();
                contadorNotas++;
            }
        }
        if(contadorNotas == 0){
            return 0;
        }
        promedio = sumaNotas / contadorNotas;
        return promedio;
    }

    public boolean verificarDefinitiva(Estudiante estudiante){
        double promedio = calcularPromedio(estudiante);
        boolean verificado = false;
        if(promedio == 5.0){
            return true;
        }
        return verificado;
    }

    public boolean existenMasDeDosMariana(){
        int contador = 0;
        for(Estudiante aux : listaEstudiantes){
            if(aux.getNombres().equalsIgnoreCase("mariana")){
                contador++;
            }
        }
        if(contador > 2){
            return true;
        }else{
        return false;
        }
    }

    public float calcularNotaMayor(){
        float mayor = 0;
        boolean primera = true;
        for(Estudiante aux : listaEstudiantes){
            for(Nota nota : aux.getListaNotas()){
                if(nota != null){
                    if(primera || nota.getValor() > mayor){
                        mayor = nota.getValor();
                        primera = false;
                    }
                }
            }
        }
        return mayor;
    }

    public float calcularNotaMenor(){
        float menor = 0;
        boolean primera = true;
        for(Estudiante aux : listaEstudiantes){
            for(Nota nota : aux.getListaNotas()){
                if(nota != null){
                    if(primera || nota.getValor() < menor){
                        menor = nota.getValor();
                        primera = false;
                    }
                }
            }
        }
        return menor;
    }

    public double calcularPromedioGeneralCurso(){
        double promedio=0;
        double sumaTotal = 0;
        int contadorNotas = 0;
        for(Estudiante aux : listaEstudiantes){
            for(Nota nota : aux.getListaNotas()){
                if(nota != null){
                    sumaTotal += nota.getValor();
                    contadorNotas++;
                }
            }
        }
        if(contadorNotas == 0){
            return 0;
        }
        promedio = (sumaTotal / contadorNotas);
        return promedio;
    }

    public void ordenarEstudiantesPorNombre(){
        Collections.sort(listaEstudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante e1, Estudiante e2) {
                return e1.getNombres().compareToIgnoreCase(e2.getNombres());
            }
        });
    }

    public ArrayList<Estudiante> obtenerEstudiantesSConPromedioAlto(){
        ArrayList<Estudiante> resultado = new ArrayList<>();
        for(Estudiante aux : listaEstudiantes){
            if(aux.getNombres().toUpperCase().startsWith("S")){
                double promedio = calcularPromedio(aux);
                if(promedio > 3.5){
                    resultado.add(aux);
                }
            }
        }
        return resultado;
    }

}