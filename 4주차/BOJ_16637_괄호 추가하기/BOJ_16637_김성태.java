package com.joykst96.practice.boj;

import java.io.*;

/*
 *  메모리: 11584 KB
 *  시간: 84 ms
 * 
 */

public class BOJ_16637_괄호추가하기 {
	
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] exp = br.readLine().toCharArray();
		int[] nums = new int[N - N / 2];
		char[] ops = new char[N / 2];
		for (int i = 0; i < exp.length; i++) {
			if (i % 2 == 0) nums[i / 2] = exp[i] - '0';
			else ops[i / 2] = exp[i];
		}
		subset(ops, nums);
		System.out.println(max);
	}

	private static void subset(char[] ops, int[] nums) {
		int n = ops.length;
		for (int i = 0; i < 1 << n; i++) {
			boolean[] out = new boolean[n];
			for (int j = 0; j < n; j++) if ((i & (1 << j)) != 0) out[j] = true;
			int result = solve(ops, out, nums);
			if (max < result) max = result;
		}
	}

	private static int solve(char[] ops, boolean[] isBracketed, int[] nums) {
		int[] operands = nums.clone();
		char[] operators = ops.clone(); 
		for (int i = 1; i < operators.length; i++) if (isBracketed[i] & isBracketed[i -1]) return Integer.MIN_VALUE;
		for (int i = 0; i < operators.length; i++) if (isBracketed[i]) operands[i] = calc(operands[i], operands[i + 1], operators[i]);
		int result = operands[0];
		for (int i = 0; i < operators.length; i++) if (!isBracketed[i]) result = calc(result, operands[i + 1], operators[i]);
		return result;
	}

	private static int calc(int i, int j, char op) {
		switch (op) {
		case '+': return i + j; 
		case '-': return i - j;
		case '*': return i * j;
		}
		return 0;
	}
}
