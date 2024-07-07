public class MMU {

    private static MMU instancia;

    private MMU(){}

    public static MMU getInstancia(){
        if (instancia == null) {
            instancia = new MMU();
        }
        return instancia;
    }

    public String traducir(int memorialogica){
        //transformar mediante un hashing la posición de memoria lógica a memoria fisica
        return "xd98f54";
    }

    public int traducir2(String memoriafisica){
        return 0;
    }


}