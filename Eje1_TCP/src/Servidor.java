
import java.io.*;
import java.net.*;

public class Servidor {  
	public static final int PORT = 4444;
	static Socket socketCliente = null;
	static BufferedReader entrada = null;
	static PrintWriter salida = null;
	static ServerSocket socketServidor = null;
	public static void main(String[] args) throws IOException {
		// Establece el puerto en el que escucha peticiones

		try {
			socketServidor = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("No puede escuchar en el puerto: " + PORT);
			System.exit(-1);
		}



		System.out.println("Escuchando: " + socketServidor);
		try {
			// Se bloquea hasta que recibe alguna petición de un cliente
			// abriendo un socket para el cliente
			socketCliente = socketServidor.accept();
			System.out.println("Connexión acceptada: "+ socketCliente);
			// Establece canal de entrada
			entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			// Establece canal de salida
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);

			// Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
			while (true) {  


				String str = entrada.readLine();
				System.out.println("Cliente: " + str);

				if (str.equals("1")) 
					salida.println("Papel");
				if (str.equals("2")) 
					salida.println("Piedra");
				if (str.equals("3")) 
					salida.println("Tijera");
				if (str.equals("Salir")) 
				{	
					finalizar();

				}	
			}

		} catch (IOException e) {
			finalizar();
		} 
	}
	public static void finalizar(){
		try {
			salida.close();
			entrada.close();
			socketServidor.close();
			socketCliente.close();
			System.out.println("Conexion Finalizada...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


