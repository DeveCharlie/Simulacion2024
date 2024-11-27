package PolinomialAgent;

import java.util.Arrays;
import java.io.Serializable;

public class DataSet implements Serializable{
    // private static final long serialVersionUID = 1L;
    public double[] x;
    public double[] y;
    public double[] toPredict;
    public transient int n;
    public int grade;

    public DataSet(double[] x, double[] y, double[] toPredict, int grade) {
        this.x = x;
        this.y = y;
        this.toPredict = toPredict;
        this.grade = grade;
    }
    /*
    public DataSet() {
        this.x = new double[]{23, 26, 30, 34, 43, 48, 52, 57, 58};
        this.y = new double[]{651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
        this.toPredict = new double[]{30, 58, 60, 80, 100};
        this.grade = 3;
    }
     */

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    public double[] getToPredict() {
        return toPredict;
    }

    public void setToPredict(double[] toPredict) {
        this.toPredict = toPredict;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
