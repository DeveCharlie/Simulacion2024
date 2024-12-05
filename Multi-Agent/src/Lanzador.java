import SLRAgent.DataSet;
import jade.core.Agent;
import SLRAgent.Printer;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import com.google.gson.Gson;

// -agents Emisor1:PrimerAgenteEmisor;SLRAgent1:Lanzador;Emisor2:SegundoAgenteEmisor;PRAgent1:LanzadorPR -gui
public class Lanzador extends Agent{

    protected void setup (){
        System.out.println("Hola soy el agente: " + getLocalName());
        System.out.println("Mi trabajo es calcular las regresiones lineales");

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

                    Printer printer = new Printer();
                    printer.printerAllResults(dataSetSerialized);
                }
                else{
                    block(); // paraliza el agente hasta que reciba un mensaje
                }
            }
        });
    }

}
