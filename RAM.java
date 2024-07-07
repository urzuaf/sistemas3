public class RAM {

    private static RAM instancia;
    private int[] bloques;

    private RAM(){}

    public static RAM getInstancia(){
        if (instancia == null) {
            instancia = new RAM();
        }
        return instancia;
    }

    public void Alocar(int idProceso, int start, int end){
        // recibir donde comienza y terminan los bloques a guardar los procesos
    }

    public int[] getContext(){
        //retorna la información de los bloques
        return bloques;
    }

}