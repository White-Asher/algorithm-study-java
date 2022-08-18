package com.joykst96.practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		solve(1 << Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		System.out.println(ans);
	}
	static int ans;
	private static void solve(int n, int r, int c) {
		if (n == 2) {
			if      (r == 0 && c == 1) ans += 1;  // 2사분면
			else if (r == 1 && c == 0) ans += 2;  // 3사분면
			else if (r == 1 && c == 1) ans += 3;  // 4사분면
			return;
		}
		int half = n / 2;
		int skip = 3;  // 기본은 4사분면에 있다고 가정
		if 		(r <  half && c <  half) skip = 0;  // 1사분면
		else if (r <  half && c >= half) skip = 1;  // 2사분면
		else if (r >= half && c <  half) skip = 2;  // 3사분면
		ans += half * half * skip;
		solve(half, r % half, c % half);
	}
}
// 메모리 초과
//public class Main {
//	static int cnt = -1;
//	
//	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		int r = Integer.parseInt(st.nextToken());
//		int c = Integer.parseInt(st.nextToken());
//		final int SIZE = 1 << N;
//		int[][] map = new int[SIZE][SIZE];
//		for (int i = 0, v = 0; i < SIZE; i++) for (int j = 0; j < SIZE; j++, v++) map[i][j] = v;
//		int target = map[r][c];
//		search(map, target);
//		
//	}
//	
//	static int[][][] divide(int[][] map){
//		final int size = map.length /2;
//		int[][] map1 = new int[size][size];
//		int[][] map2 = new int[size][size];
//		int[][] map3 = new int[size][size];
//		int[][] map4 = new int[size][size];
//		
//		for (int i = 0; i < size; i++) for (int j = 0; j < size; j++) map1[i][j] = map[i][j];
//		for (int i = 0; i < size; i++) for (int j = 0; j < size; j++) map2[i][j] = map[i][j + size];
//		for (int i = 0; i < size; i++) for (int j = 0; j < size; j++) map3[i][j] = map[i + size][j];
//		for (int i = 0; i < size; i++) for (int j = 0; j < size; j++) map4[i][j] = map[i + size][j + size];
//		
//		return new int[][][] {map1, map2, map3, map4};
//	}
//	
//	static void search(int[][] map, int num) {
//		if (map.length == 2) {
//			for (int i = 0; i < 2; i++) for (int j = 0; j < 2; j++) {
//				++cnt;
//				if (num == map[i][j]) System.out.println(cnt);
//			}
//			return;
//		}
//		for (int[][] divMap: divide(map)) {
//			search(divMap, num);
//		}
//	}
//}