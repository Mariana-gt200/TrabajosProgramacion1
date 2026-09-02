/*
Repositorio para la reutilización de funciones
 */

import java.util.Scanner;

public class Repositorio {


    //Función para ingresar una cadena de texto
    public static String ingresarTexto (String mensaje) {
        Scanner sc= new Scanner(System.in);
        System.out.println(mensaje);
        String texto= sc.nextLine();
        return texto;
    }
    //Función para ingresar un número entero
    public static int ingresarNumeroEntero (String mensaje) {
        Scanner sc= new Scanner(System.in);
        System.out.println(mensaje);
        int numero= sc.nextInt();
        return numero;
    }

    //Función para ingresar un número decimal
    public static double ingresarNumeroDecimal (String mensaje) {
        Scanner sc= new Scanner(System.in);
        System.out.println(mensaje);
        double numero= sc.nextDouble();
        return numero;
    }

    //Función para ingresar un valor booleano
    public static boolean ingresarValorBooleano (String mensaje) {
        Scanner sc= new Scanner(System.in);
        System.out.println(mensaje);
        boolean valor= sc.nextBoolean();
        return valor;
    }

    //Función para ingresar un arreglo de texto
    public static String [] ingresarArregloTexto(int cantidad, String mensaje){
        String [] nuevoArreglo= new String[cantidad];
        for(int i=0; i<cantidad; i++){
            nuevoArreglo[i]= ingresarTexto(mensaje+"("+(i+1)+" de "+cantidad+"):");
        }
        return nuevoArreglo;
    }

    //Función para ingresar un arreglo de números enteros
    public static int [] ingresarArregloEnteros(int cantidad, String mensaje){
        int [] nuevoArreglo= new int[cantidad];
        for(int i=0; i<cantidad; i++){
            nuevoArreglo[i]= ingresarNumeroEntero(mensaje+"("+(i+1)+" de "+cantidad+"):");
        }
        return nuevoArreglo;
    }

    //Función para ingresar un arreglo de números decimal
    public static double [] ingresarArregloDecimales(int cantidad, String mensaje){
        double [] nuevoArreglo= new double[cantidad];
        for(int i=0; i<cantidad; i++){
            nuevoArreglo[i]= ingresarNumeroDecimal(mensaje+"("+(i+1)+" de "+cantidad+"):");
        }
        return nuevoArreglo;
    }

    //Función para ingresar un arreglo de booleanos
    public static boolean [] ingresarArregloBooleanos(int cantidad, String mensaje){
        boolean [] nuevoArreglo= new boolean[cantidad];
        for(int i=0; i<cantidad; i++){
            nuevoArreglo[i]= ingresarValorBooleano(mensaje+"("+(i+1)+" de "+cantidad+"):");
        }
        return nuevoArreglo;
    }

    //Función para identificar vocal
    public static boolean identificarVocales(char letra){
        letra = Character.toLowerCase(letra);
        return letra == 'a' || letra == 'e' || letra == 'i'
                || letra == 'o' || letra == 'u';
    }

}