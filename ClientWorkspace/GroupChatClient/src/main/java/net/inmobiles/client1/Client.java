package net.inmobiles.client1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public void run() {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        try {
            InetAddress hostIP = InetAddress.getByName("localhost");
            @SuppressWarnings("resource")
			Socket socket = new Socket(hostIP, 4060);

            final DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            final DataInputStream dis = new DataInputStream(socket.getInputStream());

            System.out.println(dis.readUTF());
            String name = scanner.nextLine();
            dos.writeUTF(name);
            
            System.out.println();
			System.out.println("Type your messages...");
                         
            CommunicationHandler thread = new CommunicationHandler(dis, dos);
            thread.start();
            
            while (true) {
                String message = scanner.nextLine();
                dos.writeUTF(message);
            }            

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
