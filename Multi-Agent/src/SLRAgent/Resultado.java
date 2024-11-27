package SLRAgent;

public class Resultado {

    DiscretMaths discretMaths = new DiscretMaths();

    public double calculoB0(int n, double[] x, double[] y){
        double result = discretMaths.dividendoB0(x, y) / discretMaths.divisorB0(n, x, y);
        return result;
    }

    public double calculoB1(int n, double[] x, double[] y){
        double result = discretMaths.dividendoB1(n, x, y) / discretMaths.divisorB1(n, x);
        return result;
    }

    public double[] calculateYHat(int n, double[] x, double[] y){
        double [] result = new double[x.length];
        double B1 = calculoB1(n, x, y);
        double B0 = calculoB0(n, x, y);

        for (int i = 0; i<x.length; i++){
            result[i] = (B1 * x[i]) +  B0;
        }
        return result;
    }

    public double[] calculateToPredict(int n, double[] x, double[] y, double[] toPredict){
        double [] result = new double [toPredict.length];
        double B0 = calculoB0(n, x, y);
        double B1 = calculoB1(n, x, y);

        for (int i = 0; i<toPredict.length; i++){
            result[i] = B0 + (B1 * toPredict[i]);
        }
        return result;
    }

}

