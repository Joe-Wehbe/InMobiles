package net.imobiles.Exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ProblemF {

	private TestCase[] tests;
	private int numOfTests;

	public void getFileContent() {

		String path = "C:\\Users\\joewe\\Desktop\\inmobiles\\" + "EclipseWorkspaces\\OtherWorkspace\\Exercises\\"
				+ "src\\main\\java\\net\\imobiles\\Exercises\\input.txt";
		File file = new File(path);

		int[] numbers = null;
		int count = 1;
		int index = 0;
		int n = 0;
		int k = 0;

		try (Scanner scanner = new Scanner(file)) {

			System.out.println("Input file content: ");
			System.out.println("-------------------");
			while (scanner.hasNextLine()) {

				String[] line = scanner.nextLine().split(" ");

				for (int i = 0; i < line.length; i++) {
					System.out.print(line[i] + " ");

					if (count % 2 == 0) {
						n = Integer.parseInt(line[0]);
						k = Integer.parseInt(line[1]);
					}

					if (count % 2 == 1) {
						if (count < 2) {
							numOfTests = Integer.parseInt(line[0]);
							tests = new TestCase[numOfTests];
						}
						if (count > 2) {
							numbers = convertToInt(line);
							tests[index] = new TestCase(n, k, numbers);
						}
					}
				}

				if (count % 2 == 1 && count > 2) {
					index++;
				}
				count++;
				System.out.println();
			}

		} catch (FileNotFoundException ex) {
			System.out.println("File not found!");
		}
	}

	public int[] convertToInt(String[] array) {
		int[] converted = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			converted[i] = Integer.parseInt(array[i]);
		}
		return converted;
	}

	public void printTestCases() {
		System.out.println();
		System.out.println(numOfTests + " Test cases: ");
		System.out.println("-------------");
		for (int i = 0; i < tests.length; i++) {
			int count = i + 1;
			System.out.println("TestCase " + count + " --> " + tests[i].toString());
		}
	}

	public void performOperations() {

		System.out.println();

		Random random = new Random();

		String path = "C:\\Users\\joewe\\Desktop\\inmobiles\\" + "EclipseWorkspaces\\OtherWorkspace\\Exercises\\"
				+ "src\\main\\java\\net\\imobiles\\Exercises\\output.txt";

		try {

			FileWriter fw = new FileWriter(path);
			PrintWriter pw = new PrintWriter(fw);

			int[] allSums = new int[tests.length];
			int index = 0;

			for (int i = 0; i < tests.length; i++) {

				int count = i + 1;
				System.out.println("Performing " + tests[i].getK() + " operations on test case " + count + ":");
				System.out.println("---------------------------------------");

				if (tests[i].getK() == 0) {
					allSums[index] = sumArray(tests[i].getArray());
					System.out.println(allSums[index]);
					index++;
				}

				else {

					int[] sorted = sort(tests[i].getArray());
					int sum = 0;

					for (int j = sorted.length - 1; j >= sorted.length - tests[i].getK(); j--) {
						sum = sum + sorted[j] | (int) Math.pow(2, random.nextInt(31));
					}

					System.out.println("Sum: " + sum);
					System.out.println();

					allSums[index] = sum;
					index++;

				}
			}
			for (int k = 0; k < allSums.length; k++) {
				pw.println(allSums[k]);
			}

			pw.close();

		} catch (FileNotFoundException ex1) {
			System.out.println("File not found");
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}

	}

	public int sumArray(int[] array) {
		int sum = 0;
//		for (int i : array) {
//			sum += i;
//		}
		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i];
		}
		return sum;
	}

	public int[] sort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}

		}
		return array;
	}

	public static void main(String[] args) {
		ProblemF pf = new ProblemF();
		pf.getFileContent();
		pf.printTestCases();
		pf.performOperations();
	}
}
