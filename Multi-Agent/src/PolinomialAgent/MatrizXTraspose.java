package PolinomialAgent;

import java.math.BigDecimal;

public class MatrizXTraspose {

    //MatrizX matrizX = new MatrizX();
    int tam;
    int col;
    BigDecimal[][] matrizTraspuesta;
    public MatrizXTraspose(MatrizX matrizX){
        this.matrizTraspuesta = matrizTraspuesta(matrizX.matrizX);
        this.tam = matrizX.tam;
        this.col = matrizX.grade;
    }

    //Matriz X =            [filas=tam] [columnas=grade]
    //Matriz traspuesta =   [filas=grade] [columnas=tam]

    public BigDecimal[][] matrizTraspuesta(BigDecimal [][] matriz){
        int tam = matriz.length;
        int col = matriz[0].length;

        BigDecimal [][] traspuesta = new BigDecimal[col][tam];

        for (int i = 0; i<col; i++){//columns
            for (int j = 0; j<tam; j++){//rows
                traspuesta[i][j] = matriz[j][i];
            }
        }

        for (int i = 0; i < traspuesta.length; i++) {
            for (int j = 0; j < traspuesta[i].length; j++) {
                if (traspuesta[i][j] == null) {
                    throw new IllegalArgumentException("La matriz matrizXTX_Traspuesta contiene valores null en la posición: [" + i + "][" + j + "]");
                }
            }
        }

        return traspuesta;
    }

}
