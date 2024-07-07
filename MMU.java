public class MMU {

    private static MMU instancia;

    private MMU(){}

    public static MMU getInstancia(){
        if (instancia == null) {
            instancia = new MMU();
        }
        return instancia;
    }

    public String traducir(int memoriaLogica){
        //transformar mediante un hashing la posición de memoria lógica a memoria fisica
        
        String index = String.format("%05d", memoriaLogica);
        
        String memoriaFisica = "0x" + index;

        return memoriaFisica;
    }

    public int traducir2(String memoriaFisica){
        // Remove el prefijo de hexadecimla
        String index = memoriaFisica.substring(2);

        int memoriaLogica = Integer.parseInt(index, 16);

        return memoriaLogica;
    }


}