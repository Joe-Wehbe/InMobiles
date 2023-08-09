package net.inmobiles.GroupChatServerK;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server implements Runnable {

	private int port;
	private Set<Client> clientSet;

	public Server(int port) {
		this.port = port;
		this.clientSet = new HashSet<Client>();
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(this.port);
			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				this.handleSocket(socket);

			}
		} catch (Exception e) {

		}

	}

	private void handleSocket(Socket socket) {
		Client client = new Client(this, socket);
		try {
			clientSet.add(client);
			
			Thread t = new Thread(client);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void broadcast(Client sender, String line) {
		for (Client client : clientSet) {
			if (client != sender) {
				client.send(sender, line);
			}
		}

	}

	public void removeClient(Client client) {
		clientSet.remove(client);
	}

}
