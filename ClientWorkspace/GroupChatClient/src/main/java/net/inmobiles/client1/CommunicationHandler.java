package net.inmobiles.client1;

import java.io.*;

public class CommunicationHandler extends Thread{
	
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public CommunicationHandler(DataInputStream dis, DataOutputStream dos) {
		this.dis = dis;
		this.dos = dos;
	}
	
    public void run() {
        try {
            while (true) {
                String message = dis.readUTF();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
