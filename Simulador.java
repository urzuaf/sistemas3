public class Simulador {
    public static void main(String args[]){

        // Instancias
        PlanificadorProcesos planificador = PlanificadorProcesos.getInstancia();
        Disco disco = Disco.getInstancia();

        // Validación de la cantidad de Argumentos
        if (args.length != 5) {
            System.err.println("Error, debe ingresar como argumento:");
            System.err.println("- El Algoritmo de Planificación (1: FIFO, 2: SJF).");
            System.err.println("- El Algoritmo de Asignación de Memoria (1: Primer Ajuste, 2: Mejor Ajuste, 3: Peor Ajuste).");
            System.err.println("- El Número de Procesadores.");
            System.err.println("- El Quantum de los Procesadores.");
            System.err.println("- El Tamaño de la Memoria RAM.");
            System.exit(0);
        }

        // Validación de Argumentos Enteros
        try {
            for (int i = 0; i < 5; i++) {
                Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e){
            System.err.println("Error, los argumentos deben ser números enteros.");
            System.err.println(e.getMessage());
            System.exit(0);
        }

        // Validación de los Argumentos
        int algoritmoPlanificacion = Integer.parseInt(args[0]);
        int algoritmoAsignacion = Integer.parseInt(args[1]);
        int numProcesadores = Integer.parseInt(args[2]);
        int quantum = Integer.parseInt(args[3]);
        int sizeRAM = Integer.parseInt(args[4]);
        validarArgumentos(algoritmoPlanificacion, algoritmoAsignacion, numProcesadores, quantum, sizeRAM);

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

    public static void validarArgumentos(int algoritmoPlanificacion, int algoritmoAsignacion, int numProcesadores, int quantum, int sizeRAM){
        if (algoritmoPlanificacion < 1 || algoritmoPlanificacion > 2) {
            System.err.println("Error, el Algoritmo de Planificación debe ser 1 (FIFO) o 2 (SJF).");
            System.exit(0);
        }
        if (algoritmoAsignacion < 1 || algoritmoAsignacion > 3) {
            System.err.println("Error, el Algoritmo de Asignación de Memoria debe ser 1 (Primer Ajuste), 2 (Mejor Ajuste) o 3 (Peor Ajuste).");
            System.exit(0);
        }
        if (numProcesadores < 1) {
            System.err.println("Error, el Número de Procesadores debe ser un número mayor que 0.");
            System.exit(0);
        }
        if (quantum < 1) {
            System.err.println("Error, el Quantum de los Procesadores debe ser un número mayor que 0.");
            System.exit(0);
        }
        if (sizeRAM < 1) {
            System.err.println("Error, el Tamaño de la Memoria RAM debe ser un número mayor que 0.");
            System.exit(0);
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