public class Proceso {
    // Atributos
    private int id;
    private int size;
    private boolean state;

    // Constructor
    public Proceso(int id, int size) {
        this.id = id;
        this.size = size;
        this.state = false;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public boolean getState() {
        return state;
    }
}