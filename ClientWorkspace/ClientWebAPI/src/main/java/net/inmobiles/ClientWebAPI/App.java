package net.inmobiles.ClientWebAPI;

import java.io.IOException;
import java.util.Scanner;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class App {
	
	private final OkHttpClient client = new OkHttpClient();
	
	public void listAll() {		
		Request listAllRequest = new Request.Builder().url("http://localhost:1559/class/list").build();
		
		try {
			Response response = client.newCall(listAllRequest).execute();
			System.out.println(response.body().string());
			
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void disconnect() {
		System.out.println("Client disconnected");
		System.exit(0);
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		int flag = 1;
		int option;
		
		while(flag == 1) {
			System.out.println();
			System.out.println("1. List All");
			System.out.println("2. Search by ID");
			System.out.println("3. Add Name");
			System.out.println("4. Update Name");
			System.out.println("5. Delete Name");
			System.out.println("6. Exit");
			System.out.println("---------------");
			System.out.println("Enter your choice: ");
			
			option = scanner.nextInt();
			
			switch(option) {		
				case 1:
					listAll();
					break;
				
				case 2:
					break;
					
				case 3:
					break;
					
				case 4:
					break;
					
				case 5:
					break;
					
				case 6:
					disconnect();
					break;
				
				default:
					break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		App client = new App();
		client.run();


	}
}
