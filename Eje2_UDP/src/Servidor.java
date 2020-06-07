import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

//NOMBRE: JUAN GUILLERMO LAURA MAMANI
//CI: 8301405
//PARALELO A - GALLARDO - JUEVES (16-18)hrs 

public class Servidor {

	public static void main(String[] args) {
		DatagramSocket socket;
		int puerto=8888;
		try {


			socket = new DatagramSocket(puerto);
			Date date = new Date();
			DateFormat formato = new SimpleDateFormat("hh:mm:ss dd/mm/yyyy");

			System.out.println(" ******SERVIDOR INICIADO*******");
			System.out.println("Esperando...");
			byte[] buffer=new byte[1024];

			while(true){
				

				DatagramPacket paqueteRecep = new DatagramPacket(buffer,buffer.length);
				socket.receive(paqueteRecep);

				System.out.println("CLIENTE CONECTADO: "+"["+paqueteRecep.getPort()+" "+paqueteRecep.getAddress()+"]");
				
				
				
				String frase =new String(paqueteRecep.getData());
				String respCliente = "Cantida de palabras:"+ ContarPalabras(frase);						
				buffer = respCliente.getBytes();
				
				
				DatagramPacket paqueteAEnviar = new DatagramPacket(buffer,buffer.length,paqueteRecep.getAddress(),paqueteRecep.getPort());
				socket.send(paqueteAEnviar);
						

			}


		}catch(Exception e){ 	
			System.out.println("Error del servidor");
		}
	}

	public static int ContarPalabras(String frase) {

		StringTokenizer st = new StringTokenizer(frase);
		System.out.println("Frase del Cliente: " +frase);                                             
		return st.countTokens();
	}
}
