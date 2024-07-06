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
}