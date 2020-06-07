import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Cliente {
	static Socket socketCliente = null;
	static BufferedReader entrada = null;
	static PrintWriter salida = null;
	public static void main(String[] args)  throws IOException {


		// Creamos un socket en el lado cliente, enlazado con un
		// servidor que está en la misma máquina que el cliente
		// y que escucha en el puerto 4444
		try {
			socketCliente = new Socket("localhost", 4444);
			// Obtenemos el canal de entrada
			entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			// Obtenemos el canal de salida
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
		} catch (IOException e) {
			System.err.println("No puede establer canales de E/S para la conexión");
			System.exit(-1);
		}
		BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));

		String linea;

		// El programa cliente no analiza los mensajes enviados por el
		// usario, simplemente los reenvía al servidor hasta que este
		// se despide con "Adios"
		try {
			while (true) {
				System.out.println("***MENU***\n"
						+"Opcion 1\n"
						+"Opcion 2\n"
						+"Opcion 3\n"
						+"Salir");

				// Leo la entrada del usuario
				System.out.print("Escoga una Opcion: ");
				linea = stdIn.readLine();


				// La envia al servidor
				salida.println(linea);
				if (linea.equals("Salir")) 
				{	
					finalizar();

				}
				// Envía a la salida estándar la respuesta del servidor
				linea = entrada.readLine();
				System.out.println("Respuesta servidor: " + linea);
				// Si es "Adios" es que finaliza la comunicación

			}
		} catch (IOException e) {
			finalizar();
		}
		// Libera recursos

	}
	public static void finalizar(){
		try {
			salida.close();
			entrada.close();
			socketCliente.close();
			System.out.println("conexion finalizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}