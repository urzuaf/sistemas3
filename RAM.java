import java.util.Random;

public class RAM {

    private static RAM instancia;
    private int[] bloques;
    private MMU mmu = MMU.getInstancia();

    private RAM() {
    }

    public static RAM getInstancia() {
        if (instancia == null) {
            instancia = new RAM();
        }
        return instancia;
    }

    // Tenia la idea de llenar la RAM con algunos valor aleatorios al comienzo, que
    // simulen los programas que estarian corriendo en el sistema y con eso
    // habilitamos los metodos del asignador

    public void StartRAM(int sizeRAM) {
        // Crear la RAM del tamaño ingresado por parametro

        Random random = new Random();

        this.bloques = new int[sizeRAM];

        // Inicializaremos la ram con todo en 0 para representar espacios vacios 
        for (int i = 0; i < this.bloques.length; i++) {
            this.bloques[i] = 0;
        }

        // Llenar posiciones aleatorias de la memorio, probablemente un 20% de la
        // memoria llena al iniciar el programa este bien
        for (int i = 0; i < this.bloques.length / 5; i++) {

            // Generamos una posicion aleatoria entre el 0 y el tamaño maximo de la ram
            int randomIndex = random.nextInt(this.bloques.length);

            // Agregar estos "procesos del sistema" con valor -1 para distinguirlos de
            // nuestros procesos.

            this.bloques[randomIndex] = -1;

        }
    }

    public void Alocar(int idProceso, int start, int end) {
        // recibir donde comienza y terminan los bloques a guardar los procesos
        System.out.println("Alocados bloques: "+  this.mmu.traducir(start)+" hasta: "+ this.mmu.traducir(end));
        for (int i = start; i < end + 1; i++) {
            this.bloques[i] = idProceso;
        }

    }

    public int[] getContext() {
        // retorna la información de los bloques
        return this.bloques;
    }

}