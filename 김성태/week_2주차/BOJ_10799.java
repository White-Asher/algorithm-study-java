package week_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10799 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		int bars = 0;
		int result = 0;
		char[] brackets = br.readLine().toCharArray();
		for (int i = 0; i < brackets.length; i++) {
			if (!stack.isEmpty() && brackets[i] != '(') {
				if (stack.peek() + i == 1) {
					result += stack.size() - 1;
				} else {
					++bars;
				}
				stack.pop();
			} else {
				stack.push(-i);
			}
		}
		result += bars;
		System.out.println(result);
	}
}
