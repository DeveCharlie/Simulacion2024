package PolinomialAgent;

import java.math.BigDecimal;

public class YHat {

    //ResultBHat bHat = new ResultBHat();
    //MatrizX matrizX = new MatrizX();
    public BigDecimal [] yHat;

    public YHat(MatrizX matrizX, ResultBHat bHat){
        this.yHat = calcularYHat(matrizX.matrizX, bHat.bHat);
    }

    public BigDecimal []calcularYHat (BigDecimal [][] X, BigDecimal [][] B){
        int rows = X.length;
        int col = B.length;

        //se crea la matriz result para hacer las multiplicaciones

        BigDecimal[] result = new BigDecimal[rows];

        BigDecimal sum = BigDecimal.valueOf(0);

        for (int i = 0; i<rows; i++){
            for (int j = 0; j<col; j++){
                sum = sum.add(X[i][j].multiply(B[j][0]));
            }
            result[i] = sum;
            sum = BigDecimal.valueOf(0);
        }
        return result;

    }

}
