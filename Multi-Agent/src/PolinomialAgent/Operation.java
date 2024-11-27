package PolinomialAgent;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operation {

    //DataSet dataSet = new DataSet();
    //MatrizX matrizX = new MatrizX();
    //MatrizXTraspose matrizXTraspose = new MatrizXTraspose();

    //resultado de X*XT
    public BigDecimal[][] matrizXTX;
    //resultado de XT*Y
    public BigDecimal[][] matrizXTY;
    public BigDecimal[][] matrizXTX_Traspuesta;
    public BigDecimal[][] matrizInversa;
    public BigDecimal[][] matrizIdentidad;

    public Operation(MatrizX matrizX, DataSet dataSet, MatrizXTraspose matrizXTraspose) {
        this.matrizXTX = resultXTX(matrizX.matrizX, matrizXTraspose.matrizTraspuesta);
        this.matrizXTY = resultXTY(dataSet.y, matrizXTraspose.matrizTraspuesta);
        this.matrizXTX_Traspuesta = matrizXTX_Traspuesta(matrizXTX);// variable = funcion que traspone la matriz
        this.matrizInversa = calcularInversaMatrizXTX_Traspuesta(matrizXTX_Traspuesta);
        this.matrizIdentidad = calcularMatrizIdentidad(matrizXTX_Traspuesta);
    }

    //XT[4][9] * X[9][4] = XTX[4][4]
    public BigDecimal[][] resultXTX(BigDecimal[][] x, BigDecimal[][] xt) {
        int rowsX = x.length;
        int colsX = x[0].length;
        int rowsXT = xt.length;
        int colsXT = xt[0].length;
        BigDecimal[][] result = new BigDecimal[rowsXT][colsX]; //tamaño de la matriz

        if (colsXT == rowsX) { // coincidencia de las columnas x filas

            for (int i = 0; i < rowsXT; i++) {
                for (int j = 0; j < colsXT; j++) {
                    if (xt[i][j] == null) {
                        throw new IllegalArgumentException("La matriz xt contiene valores null en la posición: [" + i + "][" + j + "]");
                    }
                }
            }

            for (int i = 0; i < colsX; i++) {
                for (int j = 0; j < colsX; j++) {
                    BigDecimal sum = BigDecimal.valueOf(0);
                    for (int k = 0; k < rowsX; k++) {
                        BigDecimal num1 = xt[i][k];
                        BigDecimal num2 = x[k][j];
                        sum = sum.add(num1.multiply(num2));
                    }
                    result[i][j] = sum;
                }
            }
        }
        return result;
    }

    // XT * Y -> XTY[xt.grade][1]
    public BigDecimal[][] resultXTY(double[] y, BigDecimal[][] xt) {
        int rows = xt.length;
        int col = xt[0].length;
        //se crea la matriz result para hacer las multiplicaciones
        BigDecimal[][] result = new BigDecimal[rows][1];
        BigDecimal sum = BigDecimal.valueOf(0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                sum = sum.add(xt[i][j].multiply(BigDecimal.valueOf(y[j])));
            }
            result[i][0] = sum;
            sum = BigDecimal.valueOf(0);
        }
        return result;
    }

    public BigDecimal[][] matrizXTX_Traspuesta(BigDecimal[][] matrizXTX) { // la matriz XTX es de [4][4]
        int tam = matrizXTX.length;
        int col = matrizXTX[0].length;
        BigDecimal[][] traspuesta = new BigDecimal[col][tam];

        for (int i = 0; i < col; i++) {//columns
            for (int j = 0; j < tam; j++) {//rows
                traspuesta[i][j] = matrizXTX[j][i];
            }
        }
        return traspuesta;
    }


    public BigDecimal[][] calcularInversaMatrizXTX_Traspuesta(BigDecimal[][] matrizXTX_Traspuesta) { // metodo Gauss Jordan

        int n = matrizXTX_Traspuesta.length;
        BigDecimal[][] matrizIdentidad = calcularMatrizIdentidad(matrizXTX_Traspuesta);

        //matriz expandida [matrizXTX_Traspuesta]|[matrizIdentidad]
        BigDecimal[][] matrizExpandida = matrizExpandida(matrizXTX_Traspuesta, matrizIdentidad);

        for (int i = 0; i < matrizExpandida.length; i++) {
            for (int j = 0; j < matrizExpandida[i].length; j++) {
                if (matrizExpandida[i][j] == null) {
                    throw new IllegalArgumentException("La matriz matrizExpandida contiene valores null en la posición: [" + i + "][" + j + "]");
                }
            }
        }

        for (int i = 0; i < n; i++) {
            BigDecimal pivote = matrizExpandida[i][i]; // el pivote es la diagonal de la matriz
            if (pivote.compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException("El pivote de la matrizXTX_Traspuesta es 0, no se puede invertir");
            }

            for (int j = 0; j < n * 2; j++) { // dividir una fila completa por el pivote para tener 1
                matrizExpandida[i][j] = matrizExpandida[i][j].divide(pivote, 10, RoundingMode.HALF_UP); //divisor, cuantos decimales se conservan, redondeo hacia arriba
            }

            //calcular los demas ceros de la misma columna
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    BigDecimal multiplicador = matrizExpandida[k][i];
                    for (int u = 0; u < 2 * n; u++) { //augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j]
                        matrizExpandida[k][u] = matrizExpandida[k][u].subtract(matrizExpandida[i][u].multiply(multiplicador));
                    }
                }
            }
        }

        //extraer la matriz inversa
        BigDecimal[][] matrizInversa = new BigDecimal[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizInversa[i][j] = matrizExpandida[i][j + n];
            }
        }

        return matrizInversa;
    }

    public BigDecimal[][] calcularMatrizIdentidad(BigDecimal[][] matrizXTX_Traspuesta) {

        int rows = matrizXTX_Traspuesta.length; //tamaño de filas
        int cols = matrizXTX_Traspuesta[0].length;  //tamaño de columnas
        BigDecimal[][] matrizIdentidad = new BigDecimal[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == j)
                    matrizIdentidad[i][j] = BigDecimal.valueOf(1);
                else
                    matrizIdentidad[i][j] = BigDecimal.valueOf(0);
            }
        }
        return matrizIdentidad;
    }

    public BigDecimal[][] matrizExpandida(BigDecimal[][] matrizXTX_Traspuesta, BigDecimal[][] matrizIdentidad) {

        int n = matrizXTX_Traspuesta[0].length;
        BigDecimal[][] matrizExpandida = new BigDecimal[n][n * 2];

        for (int i = 0; i < matrizXTX_Traspuesta.length; i++) {
            for (int j = 0; j < matrizXTX_Traspuesta[i].length; j++) {
                if (matrizXTX_Traspuesta[i][j] == null) {
                    throw new IllegalArgumentException("La matriz matrizXTX_Traspuesta contiene valores null en la posición: [" + i + "][" + j + "]");
                }
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizExpandida[i][j] = matrizXTX_Traspuesta[i][j];
                matrizExpandida[i][j + n] = matrizIdentidad[i][j];
            }
        }
        return matrizExpandida;
    }
}