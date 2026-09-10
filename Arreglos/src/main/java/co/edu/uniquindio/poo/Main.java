package co.edu.uniquindio.poo;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //ACTIVIDADES / EJERCICIOS EN CLASE

        //1. Crear un arreglo e inicializarlo
        int[] arreglo = {1, 2, 3, 4, 5, 6,};
        //2. Realizar un metodo que sume todos los numeros del arreglo
        System.out.println(sumarArreglo(arreglo));

        //2. Verificar si un numero se repite en un arreglo
        int[] numeros= {1, 2, 6, 0, 7, 6, 1, 1, 2};
        System.out.println("¿Cuantos numeros se repiten en el arreglo? " + identificarRepetidos(numeros));

        //la misma pero version booleana
        System.out.println("¿El numero se repite en el arreglo? " + verificarRepetido(numeros, 6));


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

    //Realizar un metodo que diga cuantos numeros se repiten en el arreglo
    public static int identificarRepetidos(int[] numeros){
        int repetidos= 0;
        for(int i=0; i<numeros.length; i++){
            for(int j=i+1; j<numeros.length; j++){
                    if (numeros[i] == numeros[j]) {
                        repetidos++;
                    }
                }
            }
        return repetidos;
    }

    //Realizar un metodo que diga si un numero se repite en el arreglo
    public static boolean verificarRepetido(int [] numeros, int numero){
        boolean repetido= false;
        for(int i=0; i<numeros.length;i++){
            for(int j=i+1; j<numeros.length; j++){
                if (numeros[i] == numeros[j]) {
                    repetido= true;
                    break;
                }
            }
            if(repetido){
                break;
            }
        }
        return repetido;
    }
}