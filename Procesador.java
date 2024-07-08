import java.util.Random;

public class Procesador {

    private static Procesador instancia;
    private CPU[] nucleos;
    private Random random = new Random();

    public static Procesador getInstancia() {
        if (instancia == null) {
            instancia = new Procesador();
        }
        return instancia;
    }

    private Procesador() {

    }

    public void setNucleos(int cantidadNucleos) {
        this.nucleos = new CPU[cantidadNucleos];
        for (int i = 0; i < cantidadNucleos; i++) {
            this.nucleos[i] = new CPU(1, i + 1);
        }
    }

    public void ejecutarProceso(Proceso proceso) {
        while(true){
            int i = random.nextInt(this.nucleos.length);
            if(this.nucleos[i].getStatus() == false){
                System.out.println("Ejecutando proceso " + proceso.getId() + " en nucleo "+ this.nucleos[i].getId());        
                this.nucleos[i].ejecutarProceso(proceso);
                break;
            }
        }
    }
}
