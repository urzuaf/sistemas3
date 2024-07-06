public class Disco {
    // Atributos
    private static Disco instancia; // Singleton
    private PlanificadorProcesos planificador;
    private Programa[] programas;

    // Constructor
    public Disco() {
        planificador = PlanificadorProcesos.getInstancia();
    }

    // Setters
    public void setProgramas(Programa[] programas) {
        this.programas = programas;
    }

    // Getters
    public static Disco getInstancia() {
        if (instancia == null) {
            instancia = new Disco();
        }
        return instancia;
    }

    public Programa[] getProgramas() {
        return programas;
    }
    
}