package GeneticAlgorithm;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.IOException;
public class CrossOver {

    public double crossOverRate; // mutar el 90%
    public int poblacion;
    public ArrayList<double[]> nuevaListaCromosomas;
    public int numGenes;
    public double[][] rCuadrada;
    public double fitness;
    public double mutation;
    public ArrayList<double[]> listaAnterior;
    public ArrayList <double[]> yHat;
    Random random = new Random();


    public CrossOver(GeneticParameters gp, double[][] rCuadrada, ArrayList<double[]> listaActualCromosomas,
                     DataSet dataSet){
        this.crossOverRate = gp.crossOverRate;
        this.poblacion = gp.population;
        this.numGenes = gp.numGenes;
        this.fitness = gp.fitness;
        this.mutation = gp.mutationRate;
        this.listaAnterior = gp.listaCromosomas;
        this.rCuadrada = rCuadrada;
        this.nuevaListaCromosomas = cruzar(listaActualCromosomas, dataSet);
    }

    public void printerNuevaListaCromosomas(ArrayList<double[]> nuevaListaCromosomas){
        for (double[] cromosoma : nuevaListaCromosomas){
            for (int i = 0; i<numGenes; i++){
                System.out.print("[" + cromosoma[i] + "]");
            }
            System.out.print("\n");
        }
    }
    //Esta funcion no esta terminada
    public ArrayList<double[]> cruzar(ArrayList<double[]> listaCromosomas, DataSet dataSet) {
        ArrayList<double[]> nuevaListaCromosomas = new ArrayList<>();
        ArrayList<double[]> ruleta = crearRuleta(listaCromosomas);
        Set<Integer> cromosomasSeleccionados = new HashSet<>(); // Para rastrear índices seleccionados

        for (double[] cromosoma : listaCromosomas) {
            int genParaCrossover = random.nextInt(2); // Genera un número entre 0 y 1
            int indiceCromosoma = listaCromosomas.indexOf(cromosoma);
            if (nuevaListaCromosomas.size() >= poblacion) {
                System.out.println("Se alcanzó el límite de la población.");
                break;
            }

            // Agregar el índice del cromosoma actual al conjunto para evitar su selección en la ruleta
            cromosomasSeleccionados.add(indiceCromosoma);

            boolean flag = seCruzara();
            if (flag && (nuevaListaCromosomas.size() + 2) <= poblacion) {
                System.out.println("Se cruzará cromosoma.");
                double[] segundoCromosoma = girarRuleta(ruleta, cromosomasSeleccionados);
                int indiceSegundoCromosoma = (int) segundoCromosoma[0];
                double[] encontrarSegundoCromosoma = listaCromosomas.get(indiceSegundoCromosoma);

                // cruzar los cromosomas seleccionados
                System.out.println("El gen que se cruzará es el " + genParaCrossover);
                hacerCrossOverGenes(cromosoma, encontrarSegundoCromosoma, genParaCrossover);

                // clonar y agregar a la nueva lista
                nuevaListaCromosomas.add(cromosoma);
                nuevaListaCromosomas.add(encontrarSegundoCromosoma);
            } else {
                System.out.println("Se mantiene el cromosoma existente.");
                nuevaListaCromosomas.add(cromosoma.clone());
            }
        }

        //mutacion de los cromosomas
        realizarMutacion(nuevaListaCromosomas, mutation);

        //revisar duplicados y cambiar uno de ellos
        nuevaListaCromosomas = cambiarCromosomaRepetido(nuevaListaCromosomas);

        YdeHat ydeHat = new YdeHat(nuevaListaCromosomas, dataSet); // recalculo de YHat con la nueva lista
        yHat = ydeHat.yHat;

        //System.out.println("\t---- Imprimiendo nuevas R2 ----");
        //r2.imprimirR2DePoblacion(r2.rCuadrada);

        System.out.println("Tamaño de la lista nueva antes de retornarla: " + nuevaListaCromosomas.size());
        return nuevaListaCromosomas;
    }


    //esta funcoin es para hacer debuggin
    public void imprimirNuevaListaMietrasSeCrea(ArrayList<double[]> nuevaListaCromosomas){
        System.out.println("B0\t\t\tB1");
        for(double[] cromosoma : nuevaListaCromosomas){
            System.out.println("[" + cromosoma[0] + "][" +cromosoma[1] + "]");
        }
    }

    public ArrayList<double[]> cambiarCromosomaRepetido(ArrayList<double[]> listaCromosomas) {
        ArrayList<double[]> nuevaListaCromosomas = new ArrayList<>();
        ArrayList<double[]> cromosomasVistos = new ArrayList<>();

        for (double[] cromosoma : listaCromosomas) {
            boolean repetido = false;

            // comparacion con los cromosomas de ya vistos
            for (double[] visto : cromosomasVistos) {
                if (Arrays.equals(cromosoma, visto)) {
                    repetido = true;
                    break; // si se encuentra repetido se rompe el ciclo
                }
            }

            if (repetido) {

                System.out.println("Cromosoma repetido encontrado, generando un nuevo cromosoma...");
                double[] nuevoCromosoma = generarNuevoCromosoma();
                nuevaListaCromosomas.add(nuevoCromosoma);
            } else {
                // agregar cromosomas no repetidos
                nuevaListaCromosomas.add(cromosoma.clone());
                cromosomasVistos.add(cromosoma.clone());
            }
        }

        // asegurarse de que la población mantenga el tamaño original
        while (nuevaListaCromosomas.size() < listaCromosomas.size()) {
            double[] nuevoCromosoma = generarNuevoCromosoma();
            nuevaListaCromosomas.add(nuevoCromosoma);
        }

        return nuevaListaCromosomas;
    }

    public boolean seCruzara(){
        double num = random.nextDouble();
        if(num >= crossOverRate){ // si el numero generado es mayor al crossOverRate no se cruzara
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<double[]> crearRuleta(ArrayList<double[]> listaCromosmas){
        ArrayList<double[]> ruleta = new ArrayList<>();
        double probabilidadAcumulada = 0;

        for (int i = 0; i<listaCromosmas.size(); i++) {
            double[] datosCromosoma = new double[4];
            datosCromosoma[0] = i; // el indice del cromosma
            datosCromosoma[1] = rCuadrada[i][1];
            double sumaTotalFitness = sumaTotalFitness();
            datosCromosoma[2] = (rCuadrada[i][1]) / sumaTotalFitness; // individual
            probabilidadAcumulada += datosCromosoma[2];
            datosCromosoma[3] = probabilidadAcumulada;
            ruleta.add(datosCromosoma);
        }
        System.out.println("El tamaño de la ruleta es de :" + ruleta.size());
        return ruleta;
    }

    public double[] girarRuleta(ArrayList<double[]> ruleta, Set<Integer> cromosomasSeleccionados) {
        double num = random.nextDouble();
        for (double[] cromosoma : ruleta) {
            int indice = (int) cromosoma[0];
            if (cromosomasSeleccionados.contains(indice)) {
                continue; // Saltar si ya fue seleccionado
            }
            num -= cromosoma[3];
            if (num <= 0) {
                cromosomasSeleccionados.add(indice); // Marcar como seleccionado
                System.out.println("El indice seleccionado por la ruleta es: " + indice);
                return cromosoma.clone();
            }
        }
        // En caso de no encontrar ninguno válido, devolver el último disponible no seleccionado
        for (double[] cromosoma : ruleta) {
            int indice = (int) cromosoma[0];
            if (!cromosomasSeleccionados.contains(indice)) {
                cromosomasSeleccionados.add(indice);
                System.out.println("El indice seleccionado por la ruleta es: " + indice);
                return cromosoma.clone();
            }
        }
        throw new IllegalStateException("No hay cromosomas válidos para seleccionar.");
    }

    public double[] buscarCromosomaSorteado(ArrayList<double[]> listaCromosomas, double sorteadoRuleta){
        double[] cromosomaSorteado;
        int index = (int) sorteadoRuleta;
        cromosomaSorteado = listaCromosomas.get(index);
        return cromosomaSorteado;
    }

    public void hacerCrossOverGenes(double[] primero, double[] segundo, int genCambiado){
        //System.out.println("primero: " + primero[genCambiado]);
        //System.out.println("segundo: " + segundo[genCambiado]);
        double aux;
        aux = primero[genCambiado];
        primero[genCambiado] = segundo[genCambiado];
        segundo[genCambiado] = aux;
    }

    public void mostrarRuleta(ArrayList<double[]> ruleta){
        System.out.println("Indice\t\tFitness\t\tprobabilidad\t\tprobrabilidadAcumulada");
        for (double[] r : ruleta){
            System.out.println(r[0] + "\t\t" + r[1] + "\t\t" + r[2] + "\t\t" +r[3]);
        }
    }
    public double sumaTotalFitness(){
        double result = 0;
        for(int i = 0; i<poblacion; i++){
            result += rCuadrada[i][1];
        }
        return result;
    }

    public double[] generarNuevoCromosoma(){
        double[] result = new double[2];

        double beta1 = random.nextDouble()*100; // genera un número entre 1 y 100
        double beta2 = random.nextDouble()*100; // genera un número entre 1 y 100
        result[0] = beta1;
        result[1] = beta2;

        return result;
    }

    public static void realizarMutacion(ArrayList<double[]> listaCromosmas, double mutation) {
        System.out.println("se hara mutacion de cromosmas");
        Random random = new Random();

        for (double[] cromosoma : listaCromosmas) {
            int numeroRandom = random.nextInt(100) + 1; // genera un número entre 1 y 100

            if (numeroRandom <= mutation) {

                double beta1 = random.nextDouble()*100; // genera un número entre 1 y 100
                double beta2 = random.nextDouble()*100; // genera un número entre 1 y 100
                cromosoma[0] = beta1;
                cromosoma[1] = beta2;

            }
        }
    }
}

