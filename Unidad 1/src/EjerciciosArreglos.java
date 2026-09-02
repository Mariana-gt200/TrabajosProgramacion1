import java.util.Scanner;

public class EjerciciosArreglos {
    public static void main (String[] args){
        Scanner sc= new Scanner(System.in);

//Ejercicio 1: Verificar si un numero x se encuentra en un arreglo
        int[] numeros= {5, 10, 15, 20, 25};
        System.out.println("Ingrese un número para verificar si está en el arreglo:");
        int numero= sc.nextInt();
        System.out.println("Su numero se encuentra en el arreglo?: "+validarNumeros(numeros, numero));

//Ejercicio 2: Invertir un arreglo sin crear otro arreglo
        int[] arreglo= {30, 12, 6, 9, 2};
        System.out.println("El arreglo "+ arreglo+ " invertido se ve: "+ invertirArreglo(arreglo));

//Ejercicio 3: Sacar el numero que mas se repite en un arreglo
        int[] repetidos= {2, 4, 7, 2, 5, 2, 5, 5, 5};
        System.out.println("El numero que m'as se repite en el arreglo es: "+ repetirNumero(repetidos));

//Ejercicio 4: Ordenar un arreglo usando el metodo burbuja
        int[] ordenados={3, 7, 2, 4, 5, 8, 6};
        System.out.println("El arreglo "+ ordenados+ " ordenado se ve: "+ ordenarArreglo(ordenados));
    }
    //Arreglos
    //Ejercicio 1
    public static boolean validarNumeros(int[] numeros, int numero){
        boolean verificado= false;

        for(int i= 0; i<numeros.length; i++){
            if (numero == numeros[i]) {
                verificado= true;
            }
        }
        return verificado;
    }

    //Ejercicio 2
    public static int[] invertirArreglo(int[] arreglo){
        int x= arreglo.length-1;
        int numGuardado=0;

        for(int i=0;i<arreglo.length; i++){
            if(i<x){
                numGuardado=arreglo[i];
                arreglo[i]=arreglo[x];
                arreglo[x]=numGuardado;
                x--;
            }
        }
        return arreglo;
    }

    //Ejercicio 3
    public static int repetirNumero(int[] repetidos){
        int numeroMasRepetido=0;
        int cantidadRepeticiones=0;

        for(int i=0; i<repetidos.length; i++){
            int contador=0;
                for(int j=0; j<repetidos.length; j++){
                    if (repetidos[j]==repetidos[i]){
                    contador++;
                    }
                }
            if(contador>cantidadRepeticiones){
                cantidadRepeticiones=contador;
                numeroMasRepetido=repetidos[i];
            }
        }
        return numeroMasRepetido;
    }

    //Ejercicio 4
    public static int[] ordenarArreglo(int[] ordenados){
        for(int i=0; i<ordenados.length-1; i++){
            for(int j=i+1; j<ordenados.length; j++){
                if(ordenados[i]>ordenados[j]){
                    int mayor=ordenados[i];
                    ordenados[i]=ordenados[j];
                    ordenados[j]=mayor;
                }
            }
        }
        return ordenados;
    }

}
