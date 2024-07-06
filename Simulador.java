public class Simulador{
    public static void main(String args[]){

        PlanificadorProcesos planificador = PlanificadorProcesos.getInstancia();

        // imaginemos que estos seran los procesos

        Proceso proceso1 = new Proceso(1, 3); 
        Proceso proceso2 = new Proceso(2, 8); 
        Proceso proceso3 = new Proceso(3, 5); 
        Proceso proceso4 = new Proceso(4, 5); 
        Proceso proceso5 = new Proceso(5, 10); 
        Proceso proceso6 = new Proceso(6, 2); 

        Proceso[] procesos = {proceso1, proceso2, proceso3};

        Proceso[] procesosFIFO = planificador.AlgoritmoFIFO(procesos);

        Proceso[] procesosSJF = planificador.AlgoritmoSJF(procesos);

        System.out.println("Procesos por FIFO");
        ImprimirProcesos(procesosFIFO);

        System.out.println("Procesos por SFJ");
        ImprimirProcesos(procesosSJF);

    }

    public static void ImprimirProcesos(Proceso[] procesos){
        for (int i=0; i<procesos.length; i++){
            System.out.println("proceso: " + procesos[i].getId() + " tamaño: " + procesos[i].getSize());
        }
    }

}

