import GeneticAlgorithm.DataSet;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TercerAgenteEmisor extends Agent {

    protected void setup(){
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                double[] x = {23, 26, 30, 34, 43, 48, 52, 57, 58};
                double[] y = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
                //double[] toPredict = {30, 58, 60, 80, 100};
                //int grade = 3;

                // Crear un objeto para representar el dataset
                DataSet dataset = new DataSet(x, y);

                try{
                    //serializacion del objeto
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
                    objectStream.writeObject(dataset);
                    objectStream.close();

                    //convertir a arreglo de byte para enviarlo
                    byte[] serializedObject = byteStream.toByteArray();
                    ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
                    mensaje.addReceiver(getAID("EvolutiveAgent1")); // Nombre del receptor
                    mensaje.setByteSequenceContent(serializedObject); // Usar byte array como contenido

                    send(mensaje);
                    System.out.println("Mensaje enviado a EvolutiveAgent1:");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /* Convertir el dataset a JSON
                Gson gson = new Gson();
                String datasetJson = gson.toJson(dataset);

                // Crear mensaje ACL
                ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
                mensaje.addReceiver(getAID("PRAgent1")); // Nombre del receptor
                mensaje.setContent(datasetJson);


                // Enviar mensaje
                send(mensaje);
                System.out.println("AgenteEmisor2: Dataset enviado en formato JSON: " + datasetJson);
                */
            }
        });
    }
}
