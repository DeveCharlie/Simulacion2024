import com.google.gson.Gson;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import SLRAgent.DataSet;

public class PrimerAgenteEmisor extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                // Dataset
                double[] x = {1,2,3,4,5,6,7,8,9};
                double[] y = {8,16,24,32,40,48,56,64,72};
                double[] toPredict = {30, 58, 60, 80, 100};
                //double[] x = {1,2,3,4,5,6,7,8,9,10};
                //double[] y = {2.1,4.1,6.0,8.1,10.2,12.1,14.2,16.2,18.3,20.1};
                //double[] toPredict = {3, 9, 10, 15, 20};

                // Crear un objeto para representar el dataset
                DataSet dataset = new DataSet(x, y, toPredict);

                // Convertir el dataset a JSON
                Gson gson = new Gson();
                String datasetJson = gson.toJson(dataset);

                // Crear mensaje ACL
                ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
                mensaje.addReceiver(getAID("SLRAgent1")); // Nombre del receptor
                mensaje.setContent(datasetJson);

                // Enviar mensaje
                send(mensaje);
                System.out.println("AgenteEmisor1: Dataset enviado en formato JSON: " + datasetJson);
            }
        });
    }
}
