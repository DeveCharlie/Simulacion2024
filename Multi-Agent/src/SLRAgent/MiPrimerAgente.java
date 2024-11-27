package SLRAgent;
//import examples.yellowPages.DFRegisterAgent;
import jade.core.Agent;
import jade.domain.DFService; // registra agentes en el DF
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.lang.reflect.Field;

public class MiPrimerAgente extends Agent {

    /*

    protected void setup() {

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("servicio regresion lineal");
        sd.setName("calcula regresion lineal");

        try{
            DFService.register(this, dfd);
            System.out.println("Agente registrado en DF: " + getLocalName());
        } catch (FIPAException e){
            e.printStackTrace();
        }

        Printer printer = new Printer();
        printer.printerAllResults();


    }

    protected void takeDown(){
        try{
            DFService.deregister(this);
            System.out.println("Agente desregistrado");
        } catch (FIPAException e){
            e.printStackTrace();
        }
    }
    */


}
