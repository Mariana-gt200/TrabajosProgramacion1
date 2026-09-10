package co.edu.uniquindio.poo;

public class TareaMatrices {
    public static void main(String[] args){
        //Estudiar decisiones- operadores logicos- ciclos- arreglos- matrices- metodos

        //Tarea: 1. imprimir una matriz
        // 2. sumar los numeros de una matriz
        // 3. sumar los numeros de la diagonal de una matriz
        // 4. Dibujar en una matriz un espiral de numeros


    //Imprimir una matriz
String [][] nombres= {{"Carolina", "Camila", "Mariana"}, {"Paula", "Max", "Andy"}};
        System.out.println("Lo que contiene el arreglo es:"+"\n");
    for(int i=0; i<2; i++){
        for(int j=0; j<3; j++){
            System.out.println(nombres[i][j]+ "");
        }
    }

   //Sumar los numeros de una matriz
int [][] numeros= {{1, 4, 6, 7, 8, 9}, {6, 0, 8, 10, 3, 5}, {4, 15, 9, 2, 6, 0}};
        int suma=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<6; j++){
                suma += numeros[i][j];

            }
        }
        System.out.println("La suma de los numeros de la matriz es: " + suma);

   //Sumar los numeros de la diagonal de una matriz
int [][] numeros2= {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int sumaDiagonal=0;
    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++){
            if(i==j){
                sumaDiagonal += numeros2[i][j];
                break;
            }
        }
    }
    System.out.println("La suma de los numeros de la diagonal de la matriz es: " + sumaDiagonal);

    //Dibujar en una matriz un espiral de numeros
    //Nota: El codigo fue generado con la ayuda de Gemini y analizado por mi para entender el ejercicio.
        int filas=5, columnas=5;
        int [][] espiral= new int[filas][columnas];
        int inicioFila= 0;
        int finFila= filas-1;
        int inicioColumna= 0;
        int finColumna= columnas-1;
        int valor=1;

        while (inicioFila <= finFila && inicioColumna <= finColumna) {
            // 1. Derecha
            for (int j = inicioColumna; j <= finColumna; j++) {
                espiral[inicioFila][j] = valor++;
            }
            inicioFila++; // El limite superior se mueve hacia abajo

            // 2. Abajo
            for (int i = inicioFila; i <= finFila; i++) {
                espiral[i][finColumna] = valor++;
            }
            finColumna--; // El límite derecho se mueve hacia la izquierda

            // 3. Izquierda
            if (inicioFila <= finFila) {
                for (int j = finColumna; j >= inicioColumna; j--) {
                    espiral[finFila][j] = valor++;
                }
                finFila--; // El límite inferior se mueve hacia arriba
            }

            // 4. Arriba
            if (inicioColumna <= finColumna) {
                for (int i = finFila; i >= inicioFila; i--) {
                    espiral[i][inicioColumna] = valor++;
                }
                inicioColumna++; // El límite izquierdo se mueve hacia la derecha
            }
        }





    }
}
