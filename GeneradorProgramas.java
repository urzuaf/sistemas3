import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorProgramas {

    public static Programa[] getProgramas(){
        try {
            Programa[] programas = generarProgramas();
            return programas;
        }
        catch(IOException e) {
            System.out.println("Error al leer el archivo");
            return null;
        }
    }
    
    private static Programa[] generarProgramas() throws IOException {

        ArrayList<Programa> listaProgramas = new ArrayList<>();
        ArrayList<Proceso> listaProcesos = new ArrayList<>();
        String linea;

        BufferedReader reader = new BufferedReader(new FileReader("programas.txt"));
        while ((linea = reader.readLine()) != null) {
            int count = (int) (Math.random() * 10) + 1; // De 1 a 10 la cantidad de procesos
            for (int i = 0; i < count; i++) {
                int id = i;
                int size = (int) (Math.random() * 10) + 1; // De 1 a 10 el tamaño de un proceso
                listaProcesos.add(new Proceso(id, size));
            }
            listaProgramas.add(new Programa(linea, listaProcesos.toArray(new Proceso[0])));
            listaProcesos.clear();
        }
        reader.close();

        return listaProgramas.toArray(new Programa[0]);
    }

}