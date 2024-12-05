package GeneticAlgorithm;
import javax.xml.crypto.Data;
import java.util.ArrayList;

public class YdeHat {

    //DataSet dataSet = new DataSet();
    public ArrayList <double[]> yHat;

    public YdeHat(ArrayList<double[]> listaCromosomas, DataSet dataSet){
        this.yHat = resultadoarreglosYdeHat(listaCromosomas, dataSet.x);
    }

    public double calcularYHatPorCromosoma(double[] cromosoma, double x) {
        double yHat = 0.0;

        for (int i = 0; i < cromosoma.length; i++) {
            yHat += cromosoma[i] * Math.pow(x, i); // β_i * x^i
        }

        return yHat;
    }

    public double[] yDeHatsParaCromosoma(double[] cromosoma, double[] x) {
        double[] result = new double[x.length];

        for (int i = 0; i < x.length; i++) {
            result[i] = calcularYHatPorCromosoma(cromosoma, x[i]);
        }

        return result;
    }

    public ArrayList<double[]> resultadoarreglosYdeHat(ArrayList<double[]> listaCromosomas, double[] x) {
        ArrayList<double[]> result = new ArrayList<>();

        // Iterar sobre cada cromosoma y calcular sus valores yHat
        for (double[] cromosoma : listaCromosomas) {
            result.add(yDeHatsParaCromosoma(cromosoma, x)); // Calcular yHat para cada cromosoma
        }

        return result;
    }

    public void imprimirResultadosYHat(ArrayList<double[]> resultados) {
        // recorrer la lista de arreglos para obtener yHat por cromosoma
        for (int i = 0; i < resultados.size(); i++) {
            double[] yHats = resultados.get(i); // Obtener el arreglo de yHat del cromosoma
            System.out.println("Cromosoma " + (i + 1) + ":");

            // imprimir cada valor yHat de ese cromosoma
            for (int j = 0; j < yHats.length; j++) {
                System.out.println("\tx" + (j + 1) + ": " + yHats[j]);
            }
        }
    }

    public void imprimirCromosomasDesdeYHat(ArrayList<double[]> listaCromosomas, DataSet dataSet){
        int numGenes = dataSet.numGenes;
        for (double[] cromosoma : listaCromosomas){
            for (int i = 0; i<numGenes; i++){
                System.out.print("[" + cromosoma[i] + "]");
            }
            System.out.print("\n");
        }
    }

}
