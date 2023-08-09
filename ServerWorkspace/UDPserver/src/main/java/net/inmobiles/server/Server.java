package net.inmobiles.server;

import java.io.*;
import java.net.*;

public class Server {
	public void run(){
		
		try {
			DatagramSocket ds = new DatagramSocket(4021);
			byte[] buffer = new byte[200];
			DatagramPacket dp = null;
			
			while(true) {
	            System.out.println("Waiting for client...");
				dp =  new DatagramPacket(buffer, buffer.length);
				ds.receive(dp);
				String received = new String(buffer,0, dp.getLength());
	            System.out.println("Server received: " + received);
	            buffer = new byte[200];
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
    public static void main( String[] args ){
        Server server = new Server();
        server.run();
    }
}
