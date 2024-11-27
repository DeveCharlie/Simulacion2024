package SLRAgent;


public class DiscretMaths {
    public double sumX, sumy, sumXY;

    public DiscretMaths(){

    }

    public double sumatoriaX(double[] x){
        double result = 0;

        for (int i=0; i<x.length; i++){
            result += x[i];
        }
        return result;
    }

    public double sumatoriaY(double [] y){
        double result = 0;

        for (int i=0;i<y.length;i++){
            result += y[i];
        }
        return result;
    }

    public double sumatoriaXY(double[]x, double[]y){
        double []multiplicacionXY = new double[x.length];
        double result = 0;
        //almacena los valores de X*Y en otro array
        for (int i=0; i<x.length;i++){
            multiplicacionXY[i] = x[i] * y[i];
        }

        //calcula la sumatoria de XY
        for (int j=0;j<x.length;j++){
            result += multiplicacionXY[j];
        }
        return result;
    }

    public double sumatoriaXAlCuadrada(double[] x){
        double result = 0;
        for (int i = 0; i<x.length;i++){
            result += Math.pow(x[i], 2);
        }
        return result;
    }

    public double sumatoriaCuadradaX(double[] x){
        double base = sumatoriaX(x);
        double result = Math.pow(base, 2);
        return result;
    }

    public double dividendoB1(int n, double[] x, double[] y){
        double result = n * sumatoriaXY(x, y) - (sumatoriaX(x) * sumatoriaY(y));
        return result;
    }

    public double divisorB1(int n, double[] x){
        double result = (n * sumatoriaXAlCuadrada(x)) - sumatoriaCuadradaX(x);
        return result;
    }

    public double divisorB0(int n, double[] x, double[] y){
        double result = n * sumatoriaXAlCuadrada(x) - sumatoriaCuadradaX(x);
        return result;
    }

    public double dividendoB0(double[] x, double[] y){
        double result = (sumatoriaXAlCuadrada(x) * sumatoriaY(y)) - (sumatoriaX(x) * sumatoriaXY(x, y));
        return result;
    }
}

