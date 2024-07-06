public class Programa {
    //Atributos
    private String nombre;
    private Proceso[] procesos;
    private boolean state;

    //Constructor
    public Programa(String nombre, Proceso[] procesos) {
        this.nombre = nombre;
        this.procesos = procesos;
        this.state = false;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public Proceso[] getProcesos() {
        return procesos;
    }

    public boolean getState() {
        return state;
    }
}
