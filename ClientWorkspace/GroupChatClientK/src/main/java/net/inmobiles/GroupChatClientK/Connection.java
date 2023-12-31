package net.inmobiles.GroupChatClientK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection implements Runnable {

	private String host;
	private int port;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private Console console;
	
	public Connection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void connect() throws UnknownHostException, IOException {
        this.socket = new Socket(host,port);
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream());
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public void print(String message) {
		this.output.println(message);
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String line = input.readLine();
				console.print(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
