public class Disco {
    // Atributos
    private static Disco instancia; // Singleton
    private Programa[] programas;
    private int size;

    // Constructor
    public Disco() {
        programas = new Programa[0];
    }

    // Setters
    public void setProgramas(Programa[] programas) {
        this.programas = programas;
        this.size = programas.length;
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

    public int getSize() {
        return size;
    }
    
}