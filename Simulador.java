public class Simulador {
    public static void main(String args[]){

        PlanificadorProcesos planificador = PlanificadorProcesos.getInstancia();
        Disco disco = Disco.getInstancia();

        disco.setProgramas(cargarProgramas());

        for (int i = 0; i < disco.getSize(); i++){

            System.out.println("Programa: " + disco.getProgramas()[i].getNombre());
            System.out.println("");
            Proceso[] procesos = disco.getProgramas()[i].getProcesos();

            Proceso[] procesosFIFO = planificador.planificar(1, procesos);

            System.out.println("Procesos por FIFO");
            ImprimirProcesos(procesosFIFO);
            System.out.println("");

            Proceso[] procesosSJF = planificador.planificar(2, procesos);

            System.out.println("Procesos por SFJ");
            ImprimirProcesos(procesosSJF);
            System.out.println("");
            
        }

    }

    public static void ImprimirProcesos(Proceso[] procesos){
        for (int i=0; i<procesos.length; i++){
            System.out.println("proceso: " + procesos[i].getId() + " tamaño: " + procesos[i].getSize());
        }
    }

    public static Programa[] cargarProgramas(){
        Programa[] programas = GeneradorProgramas.getProgramas();
        if (programas == null) {
            // Si no hay programas o no se pudieron leer crearemos un programa por defecto
            Proceso[] procesosDefault = {new Proceso(0, 3), new Proceso(1, 8), new Proceso(2, 5)};
            programas = new Programa[1];
            programas[0] = new Programa("default", procesosDefault);
        }
        return programas;
    }

}

