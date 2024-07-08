
import java.util.ArrayList;
import java.util.List;

// public class Procesador {

//     private boolean[] nucleos;
//     private List<Thread> hilosEnEjecucion;

//     public Procesador(int cantidadNucleos) {
//         this.nucleos = new boolean[cantidadNucleos];

//         this.hilosEnEjecucion = new ArrayList<>();
//         for (int i = 0; i < cantidadNucleos; i++) {
//             this.nucleos[i] = false;
//         }
//     }

//     public synchronized void ejecutarProceso(Proceso proceso) {
//         // Obtener un hilo para ejecutar el proceso
//         while (checkAll(nucleos)) {
//             try {
//                 Thread.sleep(10);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }

//         // Verificar si alguno de los hilos ha terminado
//         for (Thread hilo : hilosEnEjecucion) {
//             if (!hilo.isAlive()) {
//                 int nucleoLiberado = Integer.parseInt(hilo.getName().split("-")[1]) - 1;
//                 nucleos[nucleoLiberado] = false;
//                 hilosEnEjecucion.remove(hilo);
//                 break;
//             }
//         }

//         for (int i = 0; i < nucleos.length; i++) {
//             if (!nucleos[i]) {
//                 int nucleo = i + 1;
//                 nucleos[i] = true;
//                 Thread hilo = new Thread(new CPU(1, proceso), "Hilo-" + (nucleo));
//                 this.hilosEnEjecucion.add(hilo);
//                 System.out.println("Ejecutando proceso " + proceso.getId() + " en núcleo: " + nucleo);
//                 hilo.start();
//                 break;
//             }
//         }

//     }

//     private boolean checkAll(boolean[] arr) {
//         verificarHilos();
//         for (int i = 0; i < arr.length; i++) {
//             if (arr[i]) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     private synchronized void verificarHilos() {
//         for (Thread hilo : new ArrayList<>(hilosEnEjecucion)) {
//             if (!hilo.isAlive()) {
//                 int nucleoLiberado = Integer.parseInt(hilo.getName().split("-")[1]) - 1;
//                 nucleos[nucleoLiberado] = false;
//                 hilosEnEjecucion.remove(hilo);
//                 System.out.println("Núcleo " + (nucleoLiberado + 1) + " liberado.");
//             }
//         }
//     }

// }

public class Procesador {

    private static Procesador instancia;
    private boolean[] nucleos;
    private List<Thread> hilosEnEjecucion;

    public static Procesador getInstancia() {
        if (instancia == null) {
            instancia = new Procesador();
        }
        return instancia;
    }

    private Procesador() {

    }

    public void setNucleos(int cantidadNucleos) {
        this.nucleos = new boolean[cantidadNucleos];
        this.hilosEnEjecucion = new ArrayList<>();
        for (int i = 0; i < cantidadNucleos; i++) {
            this.nucleos[i] = false;
        }
    }

    public void ejecutarProceso(Proceso proceso) {
        while (true) {
            // Obtener un núcleo disponible
            for (int i = 0; i < nucleos.length; i++) {
                if (!nucleos[i]) {
                    int nucleo = i;
                    nucleos[i] = true;
                    Thread hilo = new Thread(new CPU(1, proceso), "Hilo-" + (nucleo + 1));
                    hilosEnEjecucion.add(hilo);
                    System.out.println("Ejecutando proceso " + proceso.getId() + " en núcleo: " + (nucleo + 1));

                    hilo.start();
                    try {
                        
                        hilo.join();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    return; // Salir del método después de iniciar el hilo
                }
            }
            // Esperar un momento antes de volver a intentar
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void verificarHilos() {

    }
}
