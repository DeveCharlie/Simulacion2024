package SLRAgent;

public class DataSet {

    public double[] x;
    public double[] y;
    public double[] yHat;
    public double[] toPredict;
    public int n = 0;
    public DataSet(double [] x, double [] y, double[] toPredict){//constructor
        this.x= x;
        this.y= y;
        this.n=x.length;
        this.toPredict = toPredict;
    }

    public DataSet(){

    }

    public double[] getxData() {
        return x;
    }

    public double[] getyData() {
        return y;
    }

}
