public class PlanificadorProcesos {
    // Atributos
    private static PlanificadorProcesos instancia; // Singleton

    // Constructor
    private PlanificadorProcesos() {
    }

    // Getters
    public static PlanificadorProcesos getInstancia() {
        if (instancia == null) {
            instancia = new PlanificadorProcesos();
        }
        return instancia;
    }

    

    public Proceso[] AlgoritmoFIFO(Proceso[] procesos) {
        // FCFS o FIFO es el algoritmo más simple, pero el que menos rendimiento ofrece
        // el primer proceso que llega se ejecuta, y una vez terminado se ejecuta el
        // siguiente
        // Al ser un FIFO no hay en si un algoritmo, sino que enviamos en el orden en
        // que llega
        return procesos;
    }

    public Proceso[] AlgoritmoSJF(Proceso[] procesos) {
        // SJF, prioriza los procesos más cortos primero independientemente de su
        // llegada
        // en caso de que sean iguales usará FIFO. Tiene el problema de generar
        // inanición para procesos largos
        // comprobamos que exista el arreglo de procesos
        if (procesos == null || procesos.length == 0) {
            return procesos;
        }

        for (int i = 0; i < procesos.length - 1; i++) {
            for (int j = 0; j < procesos.length - i - 1; j++) {
                if (procesos[j].getSize() > procesos[j + 1].getSize()) {
                    Proceso aux = procesos[j];
                    procesos[j] = procesos[j + 1];
                    procesos[j + 1] = aux;
                }
            }
        }

        return procesos;
    }
}