package com.ssafy.day0929.problem;

import java.io.*;
import java.util.*;


public class SWEA_2112 {
	static int[][] film;
	static int d, w, k, ans;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/2112_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			ans = k;
			
			film = new int[d][w];
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 0번 시행
			if (k == 1 || check(film)) {
				sb.append("#" + tc + " 0\n");
				continue;
			}
			
			visited = new boolean[d];
			// A로 통일
			for (int i = 0; i < d; i++) {
				visited[i] = true;
				solve(1, i, 0, film, visited);
				visited[i] = false;
			}
			
			visited = new boolean[d];
			// B로 통일
			for (int i = 0; i < d; i++) {
				visited[i] = true;
				solve(1, i, 1, film, visited);
				visited[i] = false;
			}
			
			sb.append("#" + tc + " " + ans + "\n");
			
		}
		System.out.print(sb);
	}
	
	static void solve(int depth, int row, int attr, int[][] arr, boolean[] visited) {
		if (depth >= ans)
			return;
		
		int[][] cArr = copyArr(arr);
		
		
		for (int j = 0; j < w; j++) {
			cArr[row][j] = attr;
		}
		
		if (check(cArr)) {
			ans = depth;
//			ans = Math.min(ans, depth);
			return;
		}
		
		for (int i = 0; i < d; i++) {
			if(!visited[i]) {
				visited[i] = true;
				solve(depth + 1, i, attr, cArr, visited);
				visited[i] = false;
			}
		}
	}

	static boolean check(int[][] arr) {
		boolean isSame;
		for (int j = 0; j < w; j++) {
			int cnt = 1;
			int attr = arr[0][j];
			isSame = false;
			
			for (int i = 1; i < d; i++) {
				if (arr[i][j] != attr) {
					cnt = 1;
					attr = arr[i][j];
				} else {
					cnt++;
				}
				if (cnt == k) {
					isSame = true;
					break;
				}
			}
			if (!isSame)
				return false;
		}
		return true;
	}
	
	static int[][] copyArr(int[][] arr) {
		int[][] cArr = new int[d][w];
		
		for (int i = 0; i < d; i++)
			cArr[i] = arr[i].clone();
		
		return cArr;
	}
	
}
