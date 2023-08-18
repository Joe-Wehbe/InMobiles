package net.imobiles.Exercises;

import java.util.Arrays;

public class TestCase {

	private int n;
	private int k;
	private int[] array;
	
	public TestCase(int n, int k, int[] array) {
		this.n = n;
		this.k = k;
		this.array = array;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	@Override
	public String toString() {
		return "n=" + n + ", k=" + k + ", Elements: " + Arrays.toString(array);
	}
	
}
