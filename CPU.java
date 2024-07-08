public class CPU implements Runnable{

    //private static CPU instancia;
    RAM ram = RAM.getInstancia();

    private int quantum;
    private Proceso proceso;
    private boolean state = false;
    private int id;
    
    
    public CPU(int quantum, int id){
        this.quantum = quantum;
        this.id = id;
    }

    @Override
    public void run() {
        ejecutarProceso(this.proceso);
    }

    public void setProceso(Proceso proceso){
        this.proceso = proceso;
    }

    public boolean getStatus(){
        return this.state;
    }
    public int getId(){
        return this.id;
    }

    public synchronized void ejecutarProceso(Proceso proceso){
        try {
            System.out.println("");
            this.state = true;
            Thread.sleep(proceso.getSize() * 400);
            ram.Dealocar(proceso.getId());
            this.state = false;
        }
        catch (Exception e) {
            System.out.println("El proceso fue interrumpido mientras dormia");
        }
    }

}