package net.imobiles.Exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProblemF {

	public void getFileContent() {
		
		String path = "C:\\Users\\joewe\\Desktop\\inmobiles\\EclipseWorkspaces\\OtherWorkspace\\Exercises\\src\\main\\java\\net\\imobiles\\Exercises\\input.txt";
		
		File file = new File(path);
		
		try {
			Scanner scanner = new Scanner(file);
			
			System.out.println("The file contains: ");
			System.out.println("------------------");
			
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(" ");
				for(int i = 0; i < line.length; i++) {
					System.out.print(line[i]);
				}
				System.out.println();
			}
			
		}catch(FileNotFoundException ex) {
			System.out.println("File not found!");
		}
	}
	
	public static void main(String[] args) {
		ProblemF pf = new ProblemF();
		pf.getFileContent();
	}
}
