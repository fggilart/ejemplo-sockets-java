package servidorconsola;

import cliente.DataPaquete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {

    private final int PUERTO_SERVIDOR = 40000;

    @Override
    public void run() {
        System.out.println("Hilo del servidor iniciado..");

        try {
            // crear el servidor
            var server = new ServerSocket(PUERTO_SERVIDOR);

            while (true) {
                // crear el socket para aceptar la comunicación
                Socket socket = server.accept();

                // crear el flujo de entrada asociado al socket
                var flujoEntrada = new ObjectInputStream(socket.getInputStream());
                // extraer el mensaje
                DataPaquete dataEntrada = (DataPaquete) flujoEntrada.readObject();

                var nombreUsuario = dataEntrada.getNombreUsuario();
                var direccionIP = dataEntrada.getDireccionIP();
                var mensaje = dataEntrada.getMensaje();

                // visualizar el mensaje en el area del mensaje en la interfaz
                var mensajeConcatenado = nombreUsuario + " para: " + direccionIP + ": \n" + mensaje + "\n";
                System.out.println(mensajeConcatenado);
                // cerrar el flujo
                //flujoEntrada.close();
                // cerrar el socket
                socket.close();
            }

        } catch (IOException ex) {

            System.out.println("No se puede crear el socket en el servidor");;
        } catch (ClassNotFoundException ex) {

            System.out.println("No se encuentra la clase DataPaquete");
        }
    }

}
