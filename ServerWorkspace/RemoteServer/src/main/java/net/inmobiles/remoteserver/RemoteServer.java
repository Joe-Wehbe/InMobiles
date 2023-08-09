package net.inmobiles.remoteserver;

import java.io.*;
import java.net.*;
import java.util.Random;

public class RemoteServer {
	
	public void run() throws Exception, IOException {
		
		System.out.println("Waiting for the client...");
		
		ServerSocket serverSocket = new ServerSocket(4008);
		Socket socket = serverSocket.accept();
		
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		Random random = new Random();
		
		while (socket.isConnected()) {
		    for (int i = 0; i < 10; i++) {
		        int randomNumber = random.nextInt(20) + 1; 
		        dos.writeUTF(randomNumber+"");
		        System.out.println("Number sent: " + randomNumber);
		        System.out.println(dis.readUTF());
		        Thread.sleep(3000);
		               
		    }
		}
	}
	
	public static void main (String[] args) throws IOException, Exception {
		RemoteServer rs = new RemoteServer();
		rs.run();
	}


}





