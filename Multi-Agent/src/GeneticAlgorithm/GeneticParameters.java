package GeneticAlgorithm;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticParameters {
    public int population = 10;
    public double crossOverRate = 0.70; // 90%
    public double mutationRate = 0.35; // 10%
    public double elitism = 0.05; // 5%
    public int numGenes;
    public double fitness = 0.94;
    public ArrayList<double[]> listaCromosomas = new ArrayList<double[]>();
    Random random = new Random();
    public double[] yDeHat = new double[population];
    private boolean generadas = false;
    public GeneticParameters(DataSet dataSet){
        if (generadas == false) {
            this.numGenes = dataSet.numGenes;
            this.listaCromosomas = generarPosiblesSoluciones();
            generadas = true;

        }
    }


    public ArrayList<double[]> generarPosiblesSoluciones(){
        ArrayList<double[]> result = new ArrayList<>();

        for (int i = 0; i<population; i++){// agregacion de genes a la Lista
            double[] cromosoma = new double[numGenes];

            for (int j = 0; j<numGenes; j++){//creacion de genes
                double gen = random.nextDouble()*100; // genera numeros del 1 al 100
                cromosoma[j] = gen;
            }
            result.add(cromosoma);
        }
        return result;
    }

    public void mostrarListaCromosomas(ArrayList<double[]> listaCromosomas){
        for (double[] cromosoma : listaCromosomas){
            for (int i = 0; i<numGenes; i++){
                System.out.print("[" + cromosoma[i] + "]");
            }
            System.out.print("\n");
        }
    }

    public void setGeneradas(boolean generadas) {
        this.generadas = generadas;
    }

    public boolean getGeneradas(){
        return this.generadas;
    }
}
