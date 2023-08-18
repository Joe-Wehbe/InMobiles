package net.imobiles.Exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProblemF {
	
	private TestCase[] tests = null;

	public void getFileContent() {
		
		String path = "C:\\Users\\joewe\\Desktop\\inmobiles\\"
				+ "EclipseWorkspaces\\OtherWorkspace\\Exercises\\"
				+ "src\\main\\java\\net\\imobiles\\Exercises\\input.txt";	
		File file = new File(path);
		
		String[] numbers = null;
		int numOfTests = 0;
		int count = 1;
		int index = 0;
		int n = 0;
		int k = 0;
		
		try {
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			
			System.out.println("The file contains: ");
			System.out.println("------------------");
						
			while(scanner.hasNextLine()) {
				
				String[] line = scanner.nextLine().split(" ");
				for(int i = 0; i < line.length; i++) {
					System.out.print(line[i]);
									
					if(count % 2 == 0) {
						n = Integer.parseInt(line[0]);
						k = Integer.parseInt(line[1]);
					}
					
					if(count % 2 == 1) {
						if(count < 2) {
							numOfTests = Integer.parseInt(line[0]);
							tests = new TestCase[numOfTests];
						}
						if(count > 2) {
							numbers = line;
							tests[index] = new TestCase(n, k, numbers);
						}
					}
				}
				if(count % 2 == 1 && count > 2) {
					index++;
				}
				count++;
				System.out.println();
			}
			
		}catch(FileNotFoundException ex) {
			System.out.println("File not found!");
		}
	}
	
	public void print() {
		System.out.println();
		for(int i = 0; i < tests.length; i++) {
			System.out.println(tests[i].toString());
		}
	}
	
	public static void main(String[] args) {
		ProblemF pf = new ProblemF();
		pf.getFileContent();
		pf.print();
	}
}
