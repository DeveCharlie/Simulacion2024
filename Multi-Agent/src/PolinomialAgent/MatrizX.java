package PolinomialAgent;
import java.math.BigDecimal;

public class MatrizX {

    DataSet dataSet;
    public int grade;
    public int tam;
    public BigDecimal[][] matrizX;
    //Contruccion de la Matriz X [filas][columnas]
    public MatrizX(DataSet dataSet){
        this.dataSet = dataSet;
        this.grade = dataSet.grade;
        this.tam = dataSet.x.length;
        this.matrizX = datosMatrizX(dataSet.x);
    }


    //funcion para llenar los datos de la matriz X
    public BigDecimal[][] datosMatrizX(double [] x){
        BigDecimal matrizX[][] = new BigDecimal[tam][grade+1];
        for (int i = 0; i<=grade; i++){//columns
            for (int j = 0; j<tam; j++){//rows

                if (i == 0)
                    matrizX[j][i] = BigDecimal.ONE;
                else
                    matrizX[j][i] = BigDecimal.valueOf(Math.pow(x[j], i));
            }
        }

        return matrizX;
    }

}

