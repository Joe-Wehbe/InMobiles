package net.inmobiles.server;

import java.io.*;
import java.net.*;

public class Server {
	public void run() {
		try {
			
			int serverPort = 4021;
			ServerSocket serverSocket = new ServerSocket(serverPort);
			serverSocket.setSoTimeout(10000);
			
			while(true) {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");	
				
				try {
					
					Socket socket = serverSocket.accept();
					System.out.println("Connected to " + socket.getRemoteSocketAddress());
					
					PrintWriter toClient = new PrintWriter(socket.getOutputStream(),true);
					BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					String received = fromClient.readLine();
					System.out.println("Server received: " + received);
					toClient.println("Thank you for connecting to " + socket.getLocalSocketAddress() + "\nGoodbye!"); 
				
				}catch (SocketTimeoutException ex) {
					System.out.println("No connection request received from client...");

				}

			}
			
		}catch(UnknownHostException ex1) {
			ex1.printStackTrace();
		}catch(IOException ex2) {
			ex2.printStackTrace();
		}
	}
	
	  public static void main(String[] args) {
			Server server = new Server();
			server.run();
	  }

}


