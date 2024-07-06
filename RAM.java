public class RAM {
    // Atributos
    private int tamano;
    private MMU mmu;

    // Constructor
    public RAM(int tamano) {
        this.tamano = tamano;
        this.mmu = MMU.getInstancia(tamano);
    }

    // Método para asignar memoria
    public boolean asignarMemoria(int tamano, String algoritmo) {
        switch (algoritmo) {
            case "Primer Ajuste":
                return mmu.asignarMemoriaPrimerAjuste(tamano);
            case "Mejor Ajuste":
                return mmu.asignarMemoriaMejorAjuste(tamano);
            default:
                throw new IllegalArgumentException("Algoritmo desconocido: " + algoritmo);
        }
    }

    // Método para desasignar memoria
    public void desasignarMemoria(MMU.SegmentoMemoria segmento) {
        mmu.desasignarMemoria(segmento);
    }

    // Método para obtener el estado actual de la memoria
    public void imprimirEstadoMemoria() {
        System.out.println("Memoria Libre:");
        for (MMU.SegmentoMemoria segmento : mmu.memoriaLibre) {
            System.out.println("Inicio: " + segmento.inicio + ", Tamaño: " + segmento.tamano);
        }

        System.out.println("Memoria Asignada:");
        for (MMU.SegmentoMemoria segmento : mmu.memoriaAsignada) {
            System.out.println("Inicio: " + segmento.inicio + ", Tamaño: " + segmento.tamano);
        }
    }

    // Getters
    public int getTamano() {
        return tamano;
    }
}
