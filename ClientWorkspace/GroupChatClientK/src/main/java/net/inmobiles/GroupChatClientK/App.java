package net.inmobiles.GroupChatClientK;

import java.io.IOException;
import java.net.UnknownHostException;

public class App {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "127.0.0.1";
		int port = 4010;
		if(args.length > 0) {
			host = args[0];
		}
		if(args.length > 1) {
			port = Integer.parseInt(args[1]);
		}
		Connection connection = new Connection(host, port);
		connection.connect();
		
		Console console = new Console();
		
		console.setConnection(connection);
		connection.setConsole(console);
		
		Thread consoleThread = new Thread(console);
		Thread connectionThread = new Thread(connection);
		
		consoleThread.start();
		connectionThread.start();
		System.out.println("exiting main thread");
		
		
	}

}
