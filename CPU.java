public class CPU {

    private static CPU instancia;

    private boolean[] nucleos;
    private int nucleosSize;
    private int quantum;
    
    private CPU(){}

    public static CPU getInstancia(){
        if (instancia == null) {
            instancia = new CPU();
        }
        return instancia;
    }

    public boolean ejecutarProceso(int id){
        return true;
    }

}