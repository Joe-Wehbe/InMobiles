package net.inmobiles.ClientWebAPI;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class App {

	private final OkHttpClient client = new OkHttpClient();
	private String url = "http://localhost:1559/name/";
	
	
	public void listAll() {
		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute();
			
			String jsonFormat = response.body().string();
			
			Gson gson = new GsonBuilder().create();
			Name[] names = gson.fromJson(jsonFormat, Name[].class);
			
			for (int i = 0; i < names.length; i++) {
				if(names[i] != null) {
					System.out.println(names[i].toString());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void searchNameById() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the ID: ");
		String id = scanner.nextLine();

		Request request = new Request.Builder().url(url + id).build();

		try {
			Response response = client.newCall(request).execute();
			String jsonFormat = response.body().string();

			Gson gson = new GsonBuilder().create();
			Name name = gson.fromJson(jsonFormat, Name.class);

			System.out.println(name.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void addName() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the first name: ");
		String fname = scanner.nextLine();
		System.out.println("Enter the last name: ");
		String lname = scanner.nextLine();

		Name name = new Name("", fname, lname);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(name);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(json, mediaType);

		Request request = new Request.Builder().url(url).post(body).build();

		try {
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateName() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the ID: ");
		String id = scanner.nextLine();
		System.out.println("Enter the new first name: ");
		String fname = scanner.nextLine();
		System.out.println("Enter the new last name: ");
		String lname = scanner.nextLine();
		
		Name name = new Name(id, fname, lname);
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(name);
		
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(json, mediaType);
		
		Request request = new Request.Builder().url(url).put(body).build();
		
		try {
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void deleteName() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the ID: ");
		String id = scanner.nextLine();
		
		Request request = new Request.Builder().url(url + id).delete().build();
		
		try {
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void disconnect() {
		System.out.println("Client disconnected");
		System.exit(0);
	}
	

	public void run() {
		int flag = 1;
		int option;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (flag == 1) {
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

			switch (option) {
			case 1:
				listAll();
				break;

			case 2:
				searchNameById();
				break;

			case 3:
				addName();
				break;

			case 4:
				updateName();
				break;

			case 5:
				deleteName();
				break;

			case 6:
				disconnect();
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}

	
	public static void main(String[] args) {
		App client = new App();
		client.run();
	}
}
