package PolinomialAgent;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
public class Printer {

    public void printerMatrizX(MatrizX matriz){

        int grade = matriz.grade;
        int tam = matriz.tam;

        for (int i = 0; i<tam; i++){//rows
            for (int j = 0; j<=grade; j++){//columns
                System.out.print(matriz.matrizX[i][j].toPlainString() + "\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerMatrizTraspuesta(DataSet dataSet, MatrizXTraspose matrizXTraspose){
        int tam = dataSet.x.length;
        int grade = dataSet.grade;

        for (int i = 0; i<=grade; i++){//rows
            for (int j = 0; j<tam; j++){//columns
                System.out.print(matrizXTraspose.matrizTraspuesta[i][j].toPlainString() + "\t\t\t\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerProductXTX(Operation operation){
        int col = operation.matrizXTX[0].length;
        int row = operation.matrizXTX.length;

        for (int i = 0; i<row; i++){//rows
            for (int j = 0; j<col; j++){//columns
                System.out.print(operation.matrizXTX[i][j].toPlainString() + "\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerProductXTY(Operation operation){
        int row = operation.matrizXTY.length;
        int col = 1;

        for (int i = 0; i<row; i++){
            for (int j = 0; j<col; j++){
                System.out.print(operation.matrizXTY[i][j].toPlainString() + "\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerMatrizXTX_Traspuesta(Operation operation){
        int row = operation.matrizXTX_Traspuesta.length;
        int col = operation.matrizXTX_Traspuesta[0].length;

        for (int i = 0; i<row; i++){
            for (int j = 0; j<col; j++){
                System.out.print(operation.matrizXTX_Traspuesta[i][j].toPlainString() + "\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerMatrizInversa(Operation operation){
        int row = operation.matrizInversa.length;
        int col = operation.matrizInversa[0].length;

        for (int i = 0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(operation.matrizInversa[i][j].toPlainString() + "\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerResultadoBHat(ResultBHat bHat){
        int row = bHat.bHat.length;
        int col = bHat.bHat[0].length;

        for (int i = 0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(bHat.bHat[i][j] + "\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerYHat(YHat yHat){
        int rows = yHat.yHat.length;

        for(int i = 0; i<rows; i++){
            System.out.println(yHat.yHat[i]);
        }
    }

    public void printerMatrizIdentidad(Operation operation){
        int col = operation.matrizIdentidad[0].length;
        int row = operation.matrizIdentidad.length;

        for (int i = 0; i<row; i++){//rows
            for (int j = 0; j<col; j++){//columns
                System.out.print(operation.matrizIdentidad[i][j].toPlainString() + "\t\t");
            }
            System.out.print("\n");
        }
    }

    public void printerAllPolinomialResults(DataSet dataSet){

        MatrizX matrizX = new MatrizX(dataSet);
        System.out.println("\n\t\t---- Matriz X ----");
        printerMatrizX(matrizX);

        MatrizXTraspose matrizXTraspose = new MatrizXTraspose(matrizX);
        System.out.println("\n\t\t---- Matriz XT -----");
        printerMatrizTraspuesta(dataSet, matrizXTraspose);


        Operation operation = new Operation(matrizX, dataSet, matrizXTraspose);
        System.out.println("\n\t\t---- Matriz XTX ----");
        printerProductXTX(operation);

        ResultBHat bHat = new ResultBHat(operation);
        YHat yHat = new YHat(matrizX, bHat);
        ;
        System.out.println("\n\t\t---- Matriz XTX ----");
        printerProductXTX(operation);
        System.out.println("\n\t\t---- Matriz XTY ----");
        printerProductXTY(operation);
        System.out.println("\n\t\t---- MatrizXTX_Traspuesta ----");
        printerMatrizXTX_Traspuesta(operation);
        System.out.println("\n\t\t---- Matriz Identidad");
        printerMatrizIdentidad(operation);
        System.out.println("\n\t\t---- Matriz Inversa ----");
        printerMatrizInversa(operation);
        System.out.println("\n\t\t---- Result B Hat ----");
        printerResultadoBHat(bHat);
        System.out.println("\n\t\t---- Y hat ----");
        printerYHat(yHat);

    }

}


