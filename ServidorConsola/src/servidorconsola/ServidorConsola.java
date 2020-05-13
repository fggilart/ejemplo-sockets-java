package servidorconsola;

public class ServidorConsola {

    public static void main(String[] args) {
        var servidor = new Thread(new Servidor());
        servidor.start();
    }

}
