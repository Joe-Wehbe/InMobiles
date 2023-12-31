package net.inmobiles.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
      
public class Server {	
	
	public static ArrayList<String> clients = new ArrayList<String>();

	public void run() {
		try {
			
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(4060);
			System.out.println("Waiting for clients...");

			while(true) {			
			
				Socket socket = serverSocket.accept();
				System.out.println("Connected to client: " + socket.getRemoteSocketAddress());
				System.out.println();
				
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());					
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				
				dos.writeUTF("Welcome! Enter your name to start chatting: ");
				String name = dis.readUTF();
				System.out.println();

				
				if(checkClient(clients, name) == -1) {
					clients.add(name);
					Thread t = new ClientHandler(socket, name);
					t.start();
				}
				
			}	
			
		}catch(UnknownHostException ex1) {
			ex1.printStackTrace();
		}catch(IOException ex2) {
			ex2.printStackTrace();
		}
	}
	
	public int checkClient(ArrayList<String> a, String name) {
		for (int i=0; i < a.size(); i++) {
			if(a.get(i).equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.run();
		
	}
}
