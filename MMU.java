import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MMU {
    // Singleton
    private static MMU instancia;

    // Atributos
    private int tamanoMemoria;
    private List<SegmentoMemoria> memoriaLibre;
    private List<SegmentoMemoria> memoriaAsignada;

    // Constructor
    private MMU(int tamanoMemoria) {
        this.tamanoMemoria = tamanoMemoria;
        this.memoriaLibre = new ArrayList<>();
        this.memoriaAsignada = new ArrayList<>();
        memoriaLibre.add(new SegmentoMemoria(0, tamanoMemoria)); // Inicialmente toda la memoria está libre
    }

    // Obtener instancia singleton
    public static MMU getInstancia(int tamanoMemoria) {
        if (instancia == null) {
            instancia = new MMU(tamanoMemoria);
        }
        return instancia;
    }

    // Asignar memoria usando Primer Ajuste
    public boolean asignarMemoriaPrimerAjuste(int tamano) {
        for (SegmentoMemoria segmento : memoriaLibre) {
            if (segmento.tamano >= tamano) {
                SegmentoMemoria asignado = new SegmentoMemoria(segmento.inicio, tamano);
                memoriaAsignada.add(asignado);
                segmento.inicio += tamano;
                segmento.tamano -= tamano;

                if (segmento.tamano == 0) {
                    memoriaLibre.remove(segmento);
                }
                return true;
            }
        }
        return false; // No hay suficiente memoria libre para asignar
    }

    // Asignar memoria usando Mejor Ajuste
    public boolean asignarMemoriaMejorAjuste(int tamano) {
        SegmentoMemoria mejorSegmento = null;

        for (SegmentoMemoria segmento : memoriaLibre) {
            if (segmento.tamano >= tamano && (mejorSegmento == null || segmento.tamano < mejorSegmento.tamano)) {
                mejorSegmento = segmento;
            }
        }

        if (mejorSegmento != null) {
            SegmentoMemoria asignado = new SegmentoMemoria(mejorSegmento.inicio, tamano);
            memoriaAsignada.add(asignado);
            mejorSegmento.inicio += tamano;
            mejorSegmento.tamano -= tamano;

            if (mejorSegmento.tamano == 0) {
                memoriaLibre.remove(mejorSegmento);
            }
            return true;
        }

        return false; // No hay suficiente memoria libre para asignar
    }

    // Desasignar memoria
    public void desasignarMemoria(SegmentoMemoria segmento) {
        memoriaAsignada.remove(segmento);
        memoriaLibre.add(segmento);
        memoriaLibre.sort(Comparator.comparingInt(s -> s.inicio)); // Ordenar por dirección de inicio
        fusionarSegmentosLibres();
    }

    // Fusionar segmentos libres adyacentes
    private void fusionarSegmentosLibres() {
        for (int i = 0; i < memoriaLibre.size() - 1; i++) {
            SegmentoMemoria actual = memoriaLibre.get(i);
            SegmentoMemoria siguiente = memoriaLibre.get(i + 1);

            if (actual.inicio + actual.tamano == siguiente.inicio) {
                actual.tamano += siguiente.tamano;
                memoriaLibre.remove(siguiente);
                i--; // Revisar de nuevo el índice actual
            }
        }
    }

    // Clase interna para representar un segmento de memoria
    public static class SegmentoMemoria {
        int inicio;
        int tamano;

        public SegmentoMemoria(int inicio, int tamano) {
            this.inicio = inicio;
            this.tamano = tamano;
        }
    }
}