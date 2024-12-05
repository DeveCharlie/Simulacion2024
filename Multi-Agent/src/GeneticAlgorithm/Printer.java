package GeneticAlgorithm;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Printer {


    //DataSet dataSet = new DataSet();
    static Scanner scanner = new Scanner(System.in);
    public ArrayList<double[]> listaActualizada;
    double[][] datosR2;
    public ArrayList<double[]> yHatPrinter;
    public double[] suficienteR2;
    public boolean cromosmaAlcanzadoPrinter;
    public Printer(){

    }

    public void imprimirTodo(DataSet dataSet) {

        GeneticParameters genetic = new GeneticParameters(dataSet);
        YdeHat yhat = new YdeHat(genetic.listaCromosomas, dataSet);
        CalculoRCuadrada r2 = new CalculoRCuadrada(genetic.listaCromosomas, yhat.yHat, genetic.fitness, dataSet);

        boolean flag = false;
        int contGeneraciones = 0;

        System.out.println("Generando posibles soluciones");
        //genetic.generarPosiblesSoluciones();
        genetic.mostrarListaCromosomas(genetic.listaCromosomas);
        //System.out.println("\n ---- Calculando Y de hat para las soluciones generadas ----");
        //yhat.imprimirResultadosYHat(yhat.yHat);
        System.out.println("\n ---- Calculo de R2 -----");
        r2.imprimirR2DePoblacion(r2.rCuadrada);

        listaActualizada = genetic.listaCromosomas;
        yHatPrinter = yhat.yHat;
        datosR2 = r2.obtenerRcuadradaParaCromosomas(listaActualizada, yHatPrinter, dataSet.y);
        suficienteR2 = r2.suficienteR2(datosR2); // busca el mejor cromosma

        System.out.println("\n ---- Suficiente R2 -----");
        r2.imprimirSuficienteR2(listaActualizada, suficienteR2);

        r2.printFitnessAlcanzado(r2.cromosomaAlcanzado);
        flag = r2.cromosomaAlcanzado;
        contGeneraciones++;
        System.out.println("Presione enter para continuar generando cromosomas");
        scanner.next();

        while (flag == false) {// si no se alcanzo el cromosa en la primera generacion, hacer crossover
            if(contGeneraciones <= 1){

                System.out.println("Entrando al ciclo de crossover");
                // para crear la ruleta dentro de la clase CrossOver
                yHatPrinter = yhat.yHat;
                datosR2 = r2.obtenerRcuadradaParaCromosomas(listaActualizada, yHatPrinter, dataSet.y);

                // se crea una nueva lista para nuevos cromosomas
                // se calcula YHat dentro del crossover otra vez para actualizarlo
                CrossOver crossOver = new CrossOver(genetic, datosR2, listaActualizada, dataSet);
                yHatPrinter = crossOver.yHat;
                listaActualizada = crossOver.nuevaListaCromosomas;
                //CalculoRCuadrada siguientesGeneraciones = new CalculoRCuadrada(listaActualizada, yhat.yHat, genetic.fitness);

                System.out.println("Mostrando la nueva lista de cromosmas");
                crossOver.printerNuevaListaCromosomas(listaActualizada);


                System.out.println("imprimiendo datos R2 para nueva lista cromosmas");
                datosR2 = r2.obtenerRcuadradaParaCromosomas(listaActualizada,yHatPrinter, dataSet.y);
                r2.imprimirR2DePoblacion(datosR2);
                suficienteR2 = r2.suficienteR2(datosR2); // busca el mejor cromosma
                cromosmaAlcanzadoPrinter = r2.cromosomaAlcanzado(suficienteR2, genetic.fitness);
                flag = cromosmaAlcanzadoPrinter;

                r2.printFitnessAlcanzado(cromosmaAlcanzadoPrinter);
                r2.imprimirSuficienteR2(listaActualizada, suficienteR2);
                contGeneraciones++;
                scanner.next();
                System.out.println("Presione enter para continuar generando cromosomas");

            }
            else{
                flag = imprimirNuevasGeneraciones(listaActualizada, contGeneraciones, dataSet, flag,
                                                genetic, r2);
                contGeneraciones++;
            }

        }
    }

    public boolean imprimirNuevasGeneraciones(ArrayList<double[]> listaActualCromosomas, int contGeneraciones, DataSet dataSet, boolean flag
                                            ,GeneticParameters genetic, CalculoRCuadrada r2){
        System.out.println("Si este mensaje aparece es porque es la " + contGeneraciones + " generacion");
        double[][] datosR2 = r2.obtenerRcuadradaParaCromosomas(listaActualCromosomas, yHatPrinter, dataSet.y);

        // se crea una nueva lista para nuevos cromosomas
        // se calcula YHat dentro del crossover otra vez para actualizarlo
        CrossOver crossOver = new CrossOver(genetic, datosR2, listaActualCromosomas, dataSet);
        yHatPrinter = crossOver.yHat;
        listaActualizada = crossOver.nuevaListaCromosomas;

        System.out.println("Mostrando la nueva lista de cromosmas");
        crossOver.printerNuevaListaCromosomas(listaActualizada);

        System.out.println("imprimiendo datos R2 para nueva lista cromosmas");
        datosR2 = r2.obtenerRcuadradaParaCromosomas(listaActualizada,yHatPrinter, dataSet.y);
        r2.imprimirR2DePoblacion(datosR2);
        suficienteR2 = r2.suficienteR2(datosR2); // busca el mejor cromosma
        cromosmaAlcanzadoPrinter = r2.cromosomaAlcanzado(suficienteR2, genetic.fitness);
        flag = cromosmaAlcanzadoPrinter;

        r2.printFitnessAlcanzado(cromosmaAlcanzadoPrinter);
        r2.imprimirSuficienteR2(listaActualizada, suficienteR2);
        contGeneraciones++;
        System.out.println("Presione enter para continuar generando cromosomas");
        scanner.next();

        return flag;
    }

}
