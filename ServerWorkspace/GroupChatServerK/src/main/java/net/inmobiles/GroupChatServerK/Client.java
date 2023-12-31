package net.inmobiles.GroupChatServerK;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable, Closeable {

	private Server server;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private String name;

	public Client(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}

	public void open() throws IOException {
		this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.output = new PrintWriter(socket.getOutputStream());
	}

	public void print(String message) {
		this.output.println(message);
	}

	@Override
	public void run() {
		try {

			while (true) {
				String line = input.readLine();
				System.out.println("recieved from " + name + " :" + line + "... broadcasting!!");
				server.broadcast(this, line);
			}
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
			}
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	public void send(Client sender, String line) {
		String message = sender.getName() + " sent: " + line;
		output.println(message);
	}

	@Override
	public void close() throws IOException {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
		}

		try {
			if (input != null) {
				input.close();
			}

		} catch (Exception e) {
		}

		try {
			if (output != null) {
				output.close();
			}
		} catch (Exception e) {
		}

		server.removeClient(this);
	}

}
