import java.util.ArrayList;

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

    public void asignarMemoria(int option, Proceso proceso) {
        // La idea es entregar que algoritmo se va a usar en option.
        if (option <= 0 || option > 3) {
            return;
        }

        // traemos el contexto de la RAM
        RAM memoria = RAM.getInstancia();
        this.contextoMemoria = memoria.getContext();

        if (option == 1) {
            primerAjuste(proceso);
            return;
        }

        if (option == 2) {
            mejorAjuste(proceso);
            return;
        }

        if (option == 3) {
            peorAjuste(proceso);
            return;
        }
    }

    private void primerAjuste(Proceso proceso) {
        System.out.println("Alocando memoria primer ajuste");
        // Obtenemos el tamaño del agujero a buscar
        int goal = proceso.getSize();

        RAM ram = RAM.getInstancia();

        int[] memoria = ram.getContext();

        boolean aux = false;

        int[] placeholder = { 0, 0, 0 };

        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] == 0 && aux == false) {
                aux = true;
                placeholder[0] = i;
                placeholder[1] = 1;
            } else if (memoria[i] == 0 && aux == true) {
                placeholder[1] += 1;
            }

            else if (memoria[i] != 0 && aux == false) {
            }

            else if (memoria[i] != 0 && aux == true) {
                aux = false;
                placeholder[1] += 1;
                placeholder[2] = i;
                if (placeholder[1] >= goal) {
                    break;
                } else {
                    placeholder[0] = 0;
                    placeholder[1] = 0;
                    placeholder[2] = 0;
                }
            }

        }

        ram.Alocar(proceso.getId(), placeholder[0], placeholder[2]);

    }

    private void mejorAjuste(Proceso proceso) {

        System.out.println("Alocando memoria mejor ajuste");
        // Obtenemos el tamaño del agujero a buscar
        int goal = proceso.getSize();

        RAM ram = RAM.getInstancia();

        int[] memoria = ram.getContext();

        boolean aux = false;
        // Placeholder en realidad corresponde a los agujeros individuales en el formato
        // [iniciodelagujero, tamañodelagujero, finaldelagujero]
        ArrayList<int[]> Agujeros = new ArrayList<>();

        for (int i = 0; i < memoria.length; i++) {

            if (memoria[i] == 0 && aux == false) {
                aux = true;
                int[] placeholder = new int[3];
                placeholder[0] = i;
                placeholder[1] = 1;
                Agujeros.add(placeholder);
            } else if (memoria[i] == 0 && aux == true) {
                Agujeros.get(Agujeros.size() - 1)[1] += 1;
            }

            else if (memoria[i] != 0 && aux == false) {
            }

            else if (memoria[i] != 0 && aux == true) {
                aux = false;
                Agujeros.get(Agujeros.size() - 1)[2] = i;
            }
        }

        int mejorAjuste = Agujeros.get(0)[1];
        int[] agujero = Agujeros.get(0);
        for (int i = 0; i < Agujeros.size(); i++) {
            if (mejorAjuste < goal) {
                mejorAjuste = Agujeros.get(i)[1];
            }
            if (Agujeros.get(i)[1] > goal && Agujeros.get(i)[1] < mejorAjuste) {
                mejorAjuste = Agujeros.get(i)[1];
                agujero = Agujeros.get(i);
            }
        }

        System.out.println("Agujero escogido: [" + agujero[0] + " " + agujero[1] + " " + agujero[2] + " ]");
        // Alocamos el agujero con mejorAjuste

        ram.Alocar(proceso.getId(), agujero[0], agujero[2]);

        return;
    }

    private void peorAjuste(Proceso proceso) {
        System.out.println("Alocando memoria peor ajuste");
        // Obtenemos el tamaño del agujero a buscar
        int goal = proceso.getSize();

        RAM ram = RAM.getInstancia();

        int[] memoria = ram.getContext();

        boolean aux = false;
        // Placeholder en realidad corresponde a los agujeros individuales en el formato
        // [iniciodelagujero, tamañodelagujero, finaldelagujero]
        ArrayList<int[]> Agujeros = new ArrayList<>();

        for (int i = 0; i < memoria.length; i++) {

            if (memoria[i] == 0 && aux == false) {
                aux = true;
                int[] placeholder = new int[3];
                placeholder[0] = i;
                placeholder[1] = 1;
                Agujeros.add(placeholder);
            } else if (memoria[i] == 0 && aux == true) {
                Agujeros.get(Agujeros.size() - 1)[1] += 1;
            }

            else if (memoria[i] != 0 && aux == false) {
            }

            else if (memoria[i] != 0 && aux == true) {
                aux = false;
                Agujeros.get(Agujeros.size() - 1)[2] = i;
            }
        }

        // ahora buscamos el agujero que mejor se adapte al tamaño requerido

        int peorAjuste = Agujeros.get(0)[1];
        int[] agujero = Agujeros.get(0);
        for (int i = 0; i < Agujeros.size(); i++) {
            if (peorAjuste < goal) {
                peorAjuste = Agujeros.get(i)[1];
            }
            if (Agujeros.get(i)[1] > goal && Agujeros.get(i)[1] > peorAjuste) {
                peorAjuste = Agujeros.get(i)[1];
                agujero = Agujeros.get(i);
            }
        }

        // Alocamos el agujero con peorAjuste

        System.out.println("Agujero escogido: [" + agujero[0] + " " + agujero[1] + " " + agujero[2] + " ]");
        ram.Alocar(proceso.getId(), agujero[0], agujero[2]);

        return;
    }

}