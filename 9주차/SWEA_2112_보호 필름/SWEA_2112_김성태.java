package com.joykst96.practice.swea;

import java.io.*;
import java.util.*;

public class SWEA_2112_보호_필름 {
	static int D, W, K, min;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/SWEA_2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1, T = Integer.parseInt(br.readLine()); t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			dfs(0, map);
			sb.append(String.format("#%d %d%n", t, min));
		}
		System.out.print(sb);
	}

	static void dfs(int count, int[][] map) {
		if (check(map)) {
			if (min > count) min = count;
			return;
		}
		
		if (count >= min || count == D) return;
		
		dfs(count + 1, map);
		dfs(count + 1, inject(count, cloneMap(map), 0));
		dfs(count + 1, inject(count, cloneMap(map), 1));
	}
	
	static boolean check(int[][] map) {
		for (int j = 0; j < W; j++) {
			boolean isVaild = false;
			int series = 0;
			int cur = map[0][j];
			for (int i = 0; i < D; i++) {
				series = cur == map[i][j] ? series + 1 : 1;
				if (series >= K) {
					isVaild = true;
					break;
				}
				cur = map[i][j];
			}
			if (!isVaild) return false;
		}
		return true;
	}
	
	
	static int[][] inject(int row, int[][] map, int type) {
		for (int i = 0; i < W; i++) {
			map[row][i] = type;
		}
		return map;
	}
	
	static int[][] cloneMap(int[][] oldMap) {
		int[][] newMap = new int[D][W];
		for (int i = 0; i < D; i++) for (int j = 0; j < W; j++) {
			newMap[i][j] = oldMap[i][j];
		}
		return newMap;
	}
}
