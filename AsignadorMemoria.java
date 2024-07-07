public class AsignadorMemoria {
    // Atributos
    private static AsignadorMemoria instancia; // Singleton
    private int[] contextoMemoria;


    // Constructor
    private AsignadorMemoria() {
    }

    // Getters
    public static AsignadorMemoria getInstancia() {
        if (instancia == null) {
            instancia = new AsignadorMemoria();
        }
        return instancia;
    }

    public void asignarMemoria(int option, Proceso proceso){
        // La idea es entregar que algoritmo se va a usar en option.
        if(option <= 0 || option > 3){
            return;
        }

        //traemos el contexto de la RAM
        RAM memoria = RAM.getInstancia();
        this.contextoMemoria = memoria.getContext();

        if(option == 1){
            primerAjuste(proceso);
            return;
        }

        if(option == 2){
            mejorAjuste(proceso);
            return;
        }

        if(option == 3){
            peorAjuste(proceso);
            return;
        }
    }

    private void primerAjuste(Proceso proceso){
        return;
    }

    private void mejorAjuste(Proceso proceso){
        return;
    }

    private void peorAjuste(Proceso proceso){
        return;
    }

}