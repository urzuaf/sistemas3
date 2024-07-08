public class CPU implements Runnable{

    //private static CPU instancia;
    RAM ram = RAM.getInstancia();

    private int quantum;
    private Proceso proceso;
    
    public CPU(int quantum, Proceso proceso){
        this.quantum = quantum;
        this.proceso = proceso;
    }

    @Override
    public void run() {
        ejecutarProceso(this.proceso);
    }

    public synchronized void ejecutarProceso(Proceso proceso){
        try {
            /* System.out.println(""); */
            Thread.sleep(proceso.getSize() * 1000);
            ram.Dealocar(proceso.getId());
        }
        catch (Exception e) {
            System.out.println("El proceso fue interrumpido mientras dormia");
        }
    }

}