package GeneticAlgorithm;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculoRCuadrada {
    //DataSet dataSet = new DataSet();
    public double[][] rCuadrada;
    public double[] suficienteR2;

    public boolean cromosomaAlcanzado;

    public CalculoRCuadrada(ArrayList<double[]> listaCromosomas, ArrayList <double[]> yHat, double fitness, DataSet dataSet){
        this.rCuadrada = obtenerRcuadradaParaCromosomas(listaCromosomas, yHat, dataSet.y);
        this.suficienteR2 = suficienteR2(rCuadrada);
        this.cromosomaAlcanzado = cromosomaAlcanzado(suficienteR2, fitness);
    }

    public double calcularR2(double[] yReales, double[] yPredichos) {
        // calcular promedio de y
        double sumaY = 0.0;
        for (double y : yReales) {
            sumaY += y;
        }
        double mediaY = sumaY / yReales.length;

        // denominador de la funcion R2
        double denominador = 0.0;
        for (double y : yReales) {
            denominador += Math.pow(y - mediaY, 2);
        }

        // calcular numerador de la funcion R2
        double numerador = 0.0;
        for (int i = 0; i < yReales.length; i++) {
            numerador += Math.pow(yReales[i] - yPredichos[i], 2);
        }

        // Calcular R cuadrada
        double R2 = 1 - (numerador / denominador);
        return R2;
    }

    public double[][] obtenerRcuadradaParaCromosomas(ArrayList<double[]> listaCromosomas, ArrayList<double[]> resultados,
                                                     double[] yReales){

        double[][] resultR2 = new double[listaCromosomas.size()][2]; // [indice del cromosoma][valor de R cuadrada]

        for (int i = 0; i < listaCromosomas.size(); i++) {
            double[] yPredichos = resultados.get(i);  // obtener valores yHat de cromosoma
            double R2 = calcularR2(yReales, yPredichos);
            resultR2[i][0] = i+1;
            resultR2[i][1] = R2;
        }

        return resultR2; // arreglo con valores r cuadrada
    }

    public void imprimirR2DePoblacion(double[][] R2Resultados) {
        // imprimir los valores de R cuadrada para cada cromosoma
        for (int i = 0; i < R2Resultados.length; i++) {
            System.out.println("Cromosoma " + R2Resultados[i][0] + " : R cuadrada: " + R2Resultados[i][1]);
        }
    }

    public double[] suficienteR2(double[][] R2Resultado){
        // busca el mejor que cromosoma tiene un R cuadrada mayor que el fitness
        double[] result = new double[2];
        double mejorR2 = 0;

        for(int i = 0; i< R2Resultado.length;i++){
            if (R2Resultado[i][1] >= mejorR2){
                mejorR2 = R2Resultado[i][1];
                result[0] = i;
                result[1] = mejorR2;
            }
        }
        return result;
    }

    public void imprimirSuficienteR2(ArrayList<double[]> listaCromosomas, double[] suficienteR2){
        System.out.println("---- Datos del cromosoma ----");

        System.out.println("Cromosoma : " + ((suficienteR2[0])+1) + "\tValor R2 : " + suficienteR2[1]);

        int indice = (int) suficienteR2[0];
        double[] betas = listaCromosomas.get(indice);
        System.out.println("---- Betas del cromosoma ----");
        System.out.println("Beta_0 : " + betas[0] + "\tBeta_1 : " + betas[1]);
    }

    public boolean cromosomaAlcanzado(double[] suficienteR2, double fitness){
        if(suficienteR2[1] >= fitness){
            return true;
        }
        else{
            return false;
        }
    }

    public void printFitnessAlcanzado(boolean cromosomaAlcanzado){
        if(cromosomaAlcanzado){
            System.out.println("Se ha iguaaldo o superado el fitness");
        }
        else{
            System.out.println("Aun no se ha superado el fitness");
        }
    }

}
