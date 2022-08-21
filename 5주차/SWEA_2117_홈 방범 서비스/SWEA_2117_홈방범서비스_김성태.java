package com.joykst96.practice.swea;

import java.util.*;
import java.io.*;

/*
 *  메모리: 27,488 kb
 *  실행시간: 332 ms
 *  코드길이: 1,772
 * 
 */

public class SWEA_2117_홈방범서비스 {

	static int N, M, maxCustomer, homes;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("data/SWEA_2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1, T = Integer.parseInt(br.readLine()); t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			maxCustomer = 0;
			homes = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) ++homes;
				}
			}
			for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) for (int size = 0; isFeasible(size); size++) countCustomers(i, j, size);
			sb.append(String.format("#%d %d%n", t, maxCustomer));
		}
		System.out.print(sb);
	}
	
	static void countCustomers(int r, int c, int size) {
		int income = -getCost(size);
		int customers = 0;
		for (int i = r - size; i <= r + size; i++) for (int j = c - size; j <= c + size; j++) if (isIn(i, j) && isInRhombus(i, j, r, c, size)) {
			if (map[i][j] == 1) {
				income += M;
				++customers;
			}
		}
		if (income >= 0 && maxCustomer < customers) maxCustomer = customers;
	}
	
	static boolean isFeasible(int size) {
		return M * homes >= getCost(size);
	}
	
	static int getCost(int size) {
		return (size + 1) * (size + 1) + size * size;
	}
	
	static boolean isInRhombus(int pr, int pc, int r, int c, int size) {
		return Math.abs(pr - r) + Math.abs(pc - c) <= size;
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}
