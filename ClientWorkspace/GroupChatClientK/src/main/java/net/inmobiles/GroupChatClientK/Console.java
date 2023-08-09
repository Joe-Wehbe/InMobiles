package net.inmobiles.GroupChatClientK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console implements Runnable {

	private BufferedReader input;
	private Connection connection;

	public Console() {
		this.input = new BufferedReader(new InputStreamReader(System.in));
	}
 

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	public void print(String message) {
		System.out.println(message);
	}

	@Override
	public void run() {
		try {
			while (true) {
				String line = input.readLine();
				connection.print(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
