package PolinomialAgent;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
public class ResultBHat {

    //Operation operation =  new Operation();

    BigDecimal [][] bHat;

    public ResultBHat(Operation operation){
        this.bHat = calcularBHat(operation.matrizInversa, operation.matrizXTY);
    }

    public BigDecimal [][] calcularBHat(BigDecimal [][] matrizInversa, BigDecimal [][] XTY){
        int rows = matrizInversa.length;
        int col = matrizInversa[0].length;

        //se crea la matriz result para hacer las multiplicaciones

        BigDecimal[][] result = new BigDecimal[rows][1];

        BigDecimal sum = BigDecimal.valueOf(0);

        for (int i = 0; i<rows; i++){
            for (int j = 0; j<col; j++){
                sum = sum.add(matrizInversa[i][j].multiply( XTY[j][0] ) );
            }
            result[i][0] = sum;
            sum = BigDecimal.valueOf(0);
        }
        return result;
    }

}

