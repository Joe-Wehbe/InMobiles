package net.inmobiles.server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler extends Thread{
	
	private Socket socket;
	private String name;
	
    public static Map<Socket, String> clientMap = new HashMap<>();
	
	public ClientHandler(Socket socket, String name) {
		this.socket = socket;
		this.name = name;	
		clientMap.put(socket, name);
	}

	public void run() {
		while(true) {
			try {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				
				String message = dis.readUTF();
				System.out.println("Message received: '" + message + "', sending to all clients...");
				
	            for (Socket clientSocket : clientMap.keySet()) {
	                dos = new DataOutputStream(clientSocket.getOutputStream());
	                if(!clientMap.get(clientSocket).equals(name)) {
		                dos.writeUTF(name + ": " + message);
	                }
	            }
			
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	
		
	}

}
