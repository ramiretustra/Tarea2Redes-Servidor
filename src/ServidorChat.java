import java.net.*;
import java.io.*;
import java.util.StringTokenizer;


public class ServidorChat {

	public static void main(String argv[]) {

		try {
			ServerSocket socket = new ServerSocket(60);

			Socket socket_cliente = socket.accept();
			BufferedReader msj = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));

			String cadena = "";
			cadena = msj.readLine();
			if (cadena != null)
			{
				StringTokenizer st = new StringTokenizer(cadena);
				if(!st.nextToken().equals("USER")){
					
					String[] split;
					
					split = cadena.split("/");
					String emisor = split[0], receptor = split[1], mensaje = split[2], ip_emisor = split[3], ip_receptor = split[4];
					
					File mensajes = new File("mensajes.txt");
					BufferedWriter writer = new BufferedWriter(new FileWriter(mensajes, true));
					
					String mensajefinal = "Cliente(" + ip_emisor + "): " + mensaje + " " + ip_receptor + " " + receptor;
					writer.write(mensajefinal);
					writer.newLine();
					
					mensajefinal = "Servidor(" + ip_emisor + "): " + mensaje + " " + receptor;
					writer.write(mensajefinal);
					writer.newLine();
					writer.close();
				}
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}