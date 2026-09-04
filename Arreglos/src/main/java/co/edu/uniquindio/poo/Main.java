package co.edu.uniquindio.poo;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //1. Crear un arreglo e inicializarlo
        int[] arreglo = {1, 2, 3, 4, 5, 6,};
        //2. Realizar un metodo que sume todos los numeros del arreglo
        System.out.println(sumarArreglo(arreglo));

    }

    //Metodo
    public static int sumarArreglo(int[] numeros) {
        int suma = 0;
        for (int i = 0; i < numeros.length; i++) {
            suma += numeros[i];
        }
        return suma;
    }

    //Realizar un metodo que diga si existe un numero en el arreglo
    public static boolean identificarExistencia(int[] numeros, int numero){
        boolean verificacion= false;
        for(int i=0; i<numeros.length; i++){
            if(numeros[i]==numero){
                verificacion= true;
                break;
            }
        }
        return verificacion;
    }

}