import PolinomialAgent.DataSet;
import PolinomialAgent.MatrizX;
import jade.core.Agent;
import PolinomialAgent.Printer;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LanzadorPR extends Agent{

    protected void setup (){
        System.out.println("Hola soy el agente: " + getLocalName());
        System.out.println("Mi trabajo es calcular las regresiones Polinomiales");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage mensaje = receive();
                if (mensaje != null){
                    System.out.println("Mensaje Recibido");
                    try{
                        byte[] objetoSerializado = mensaje.getByteSequenceContent();
                        ByteArrayInputStream byteStream = new ByteArrayInputStream(objetoSerializado);
                        ObjectInputStream objectStream = new ObjectInputStream(byteStream);

                        // Deserializar objeto
                        DataSet dataset = (DataSet) objectStream.readObject();
                        objectStream.close();

                        System.out.println("PRAgent receptor recibio el mensaje:");
                        System.out.println("X:" + java.util.Arrays.toString(dataset.getX()));
                        System.out.println("Y:" + java.util.Arrays.toString(dataset.getY()));
                        System.out.println("toPredict:" + java.util.Arrays.toString(dataset.getToPredict()));
                        System.out.println("Grade:" + dataset.getGrade());

                        Printer printer = new Printer();
                        printer.printerAllPolinomialResults(dataset);

                    } catch (IOException e){
                        System.out.println(e.getMessage());

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }
                else{
                    block();
                }
            }
        });



        /*
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage mensaje = receive();
                System.out.println(getLocalName() + "Esperando mensaje ");
                if (mensaje != null){
                    System.out.println(getLocalName() + "Mensaje recibido");
                    String datasetJson = mensaje.getContent();

                    Gson gson = new Gson();
                    DataSet dataSetSerialized = gson.fromJson(datasetJson, DataSet.class);

                    DataSet dataSet = new DataSet(dataSetSerialized.x, dataSetSerialized.y, dataSetSerialized.toPredict, dataSetSerialized.grade);
                    Printer printer = new Printer();
                    printer.printerAllPolinomialResults();

                }
                else{
                    block(); // paraliza el agente hasta que reciba un mensaje
                }
            }
        });
        */
    }

}
