package GeneticAlgorithm;

import java.io.Serializable;

public class DataSet implements Serializable {

    double[] x;
    double[] y;
    int numGenes;

    public DataSet(double[] x, double[] y){
        this.x = x;
        this.y = y;
        this.numGenes = 2;
    }


    public double[] getX() {
        return this.x;
    }

    public double[] getY() {
        return this.y;
    }
}
