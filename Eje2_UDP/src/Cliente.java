

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


// NOMBRE: JUAN GUILLERMO LAURA MAMANI
// CI: 8301405
// PARALELO A - GALLARDO - JUEVES (16-18)hrs

public class Cliente {
	public static void main(String[] args) throws IOException {
		int puerto=8888;
		Scanner in=new Scanner(System.in);
		InetAddress servidorDest = InetAddress.getByName("localhost");
		
		DatagramSocket socket;
		
		
			try {
				
				socket = new DatagramSocket();
				
				System.out.println("*********CONEXION INICIADA************");
				
				
				String frase=in.nextLine();
				byte buffer[] = new byte[1024];
				buffer = frase.getBytes();
				DatagramPacket paqEnvio = new DatagramPacket(buffer,buffer.length,servidorDest,puerto);
				socket.send(paqEnvio);
							
				
				byte buffer1[] = new byte[1024];
				DatagramPacket paqueteRecep = new DatagramPacket(buffer1,buffer1.length);
				socket.receive(paqueteRecep);
				
				String mensajeRecibido = new String(paqueteRecep.getData());  
				
				
				System.out.println(mensajeRecibido);
				
				socket.close();
				System.out.println("Conexion Finalizada...!!!");
				
			} catch (Exception e) {
				
			}
	}
}
