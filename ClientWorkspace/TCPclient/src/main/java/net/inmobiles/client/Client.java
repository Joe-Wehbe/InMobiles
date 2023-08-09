package net.inmobiles.client;

import java.io.*;
import java.net.*;

public class Client {
	public void run() {
		try {
			
			int serverPort = 4021;
			InetAddress hostIP  = InetAddress.getByName("localhost"); // InetAddress class provides methods to get the IP of any host name
			
			System.out.println("Connecting to server on port " + serverPort);
			Socket socket = new Socket(hostIP, serverPort);
			System.out.println("Connected to " + socket.getRemoteSocketAddress());
			
			PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
			toServer.println("Hello from client " + socket.getLocalSocketAddress());
			String received = fromServer.readLine();
			System.out.println("Client received: " + received + " from server");
			
			toServer.close();
			fromServer.close();
			socket.close();
			
		}catch (UnknownHostException ex1){
			ex1.printStackTrace();
		}catch (IOException ex2) {
			ex2.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		Client client = new Client();
		client.run();
	}
}
