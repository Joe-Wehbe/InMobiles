package net.inmobiles.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	public void run() {
		int flag = 1;
		while(flag == 1) {
			try {
				DatagramSocket ds = new DatagramSocket();
				InetAddress hostIP = InetAddress.getByName("localhost");
				byte[] buffer = null;
				
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a message to send to the server: ");
				String toSend = scanner.nextLine();
				buffer = toSend.getBytes();
				
				DatagramPacket dp  = new DatagramPacket(buffer, buffer.length, hostIP, 4021);
				ds.send(dp);
				System.out.println("Message sent to server");
				
				if(toSend.equalsIgnoreCase("exit")){
					flag = 0;
				}

			}catch (IOException ex) {
				ex.printStackTrace();
			}	
		}
	}
	
	public static void main (String[] args) {
		Client client = new Client();
		client.run();
	}
}
