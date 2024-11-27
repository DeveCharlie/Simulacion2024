package SLRAgent;

public class Printer {

    private DataSet dataSet;
    private DiscretMaths discretMaths;

    private Resultado resultado;

    public Printer(){
        this.dataSet = new DataSet();
        this.discretMaths = new DiscretMaths();
        this.resultado = new Resultado();
    }

    public void printerSumatoriaX(double[] x){
        System.out.println("La sumatoria de X es: " + discretMaths.sumatoriaX(x));
    }

    public void printerSumatoriaY(double[] y){
        System.out.println("La sumatoria de Y es: " + discretMaths.sumatoriaY(y));
    }

    public void printerSumatoriaXY(double [] x, double[] y){
        System.out.println("La suatoria de X*Y es : " + discretMaths.sumatoriaXY(x,y));
    }

    public void printerSumatoriaXCuadrada(double[] x){
        System.out.println("La sumatoria de X cuadrada es: " + discretMaths.sumatoriaXAlCuadrada(x));
    }

    public void printerDividendoB1(int n, double[] x, double[] y){
        System.out.println("El resultado del dividendo es: " + discretMaths.dividendoB1(n, x, y));
    }

    public void printerSumatoriaCuadradaX(double[] x){
        System.out.println("El resultado de la sumatoria cuadrada de X: " + discretMaths.sumatoriaCuadradaX(x));
    }

    public void printerDivisorB1(int n, double[] x){
        System.out.println("El resultado del divisor es: " + discretMaths.divisorB1(n, x));
    }

    public void printerB1(int n, double[] x, double[] y){
        System.out.println("El resultado de B1 es: " + resultado.calculoB1(n, x, y));
    }

    public void printerB0(int n, double[] x, double[] y){
        System.out.println("El resultado de B0 es " + resultado.calculoB0(n, x, y));
    }

    public void printerYHat(int n, double[] x, double[] y){
        dataSet.yHat = resultado.calculateYHat(n, x, y);
        System.out.println("El resultado de YHat es:\n");
        for (int i = 0; i<dataSet.yHat.length; i++){
            System.out.println(dataSet.yHat[i]);
        }
    }

    public void printerToPredict(int n, double[] x, double[] y, double[] toPredict){
        double[] predictions = resultado.calculateToPredict(n, x, y, toPredict);
        System.out.println("El resultado de las prediciones ");
        for (int i = 0;n<toPredict.length; i++){
            System.out.print(toPredict[i] + ",");
        }
        System.out.println("es:");

        for (int j = 0; j<predictions.length; j++){
            System.out.println(predictions[j]);
        }
    }

    public void printerAllResults(DataSet dataSetSerialized){

        System.out.println("\t----- Mostrando resultados De SLR -----");
        printerSumatoriaX(dataSetSerialized.x);
        printerSumatoriaY(dataSetSerialized.y);
        printerSumatoriaXY(dataSetSerialized.x, dataSetSerialized.y);
        printerDividendoB1(dataSetSerialized.n, dataSetSerialized.x, dataSetSerialized.y);
        System.out.println("\n");
        printerSumatoriaXCuadrada(dataSetSerialized.x);
        printerSumatoriaCuadradaX(dataSetSerialized.x);
        printerDivisorB1(dataSetSerialized.n, dataSetSerialized.x);
        System.out.println("\n");
        printerB1(dataSetSerialized.n, dataSetSerialized.x, dataSetSerialized.y);
        printerB0(dataSetSerialized.n, dataSetSerialized.x, dataSetSerialized.y);
        printerYHat(dataSetSerialized.n, dataSetSerialized.x, dataSetSerialized.y);
        System.out.println("\nPredicciones");
        printerToPredict(dataSetSerialized.n, dataSetSerialized.x, dataSetSerialized.y, dataSetSerialized.toPredict);
    }

}


