package week_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Stack {
	private final int MAX_SIZE = 100000;
	private int[] iArr;
	private int size;
	
	Stack() {
		iArr = new int[MAX_SIZE];
		size = 0;
	}
	
	boolean isEmpty() { 
		return size == 0;
	}
	
	boolean isFull() {
		return size == MAX_SIZE;
	}
	
	void push(int i) {
		if (isFull()) {
			System.out.println("Overflow");
		} else {
			iArr[size++] = i;
		}
	}
	
	int pop() {
		if (isEmpty()){
			// underflow
			return -1;
		} else {
			return iArr[--size];
		}
	}
	
	int peek() {
		if (isEmpty()){
			// underflow
			return -1;
		} else {
			return iArr[size - 1];
		}
	}
}

public class BOJ1874 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] purpose = new int[n];
		Stack arr = new Stack();
		int[] result = new int[n];
		for (int i = 0; i < n; i++ ) {
			purpose[i] = Integer.parseInt(br.readLine());
			arr.push(n - i);
		}
		Stack stack = new Stack();
		int i = 0;
		while (!arr.isEmpty()) {
			if (purpose[i] == stack.peek()) {
				result[i] = stack.pop();
				sb.append("-\n");
				i++;
			} else {
				stack.push(arr.pop());
				sb.append("+\n");
			}
		}
		while (!stack.isEmpty()) {
			result[i++] = stack.pop();
			sb.append("-\n");
			
		}
		for (i = 0; i < n; i++) {
			if (purpose[i] != result[i]) {
				System.out.println("NO");
				break;
			}
		}
		if (i == n) {
			System.out.print(sb.toString());
		}
	}
}
