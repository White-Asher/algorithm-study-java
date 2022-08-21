package com.joykst96.practice.swea;

import java.util.*;
import java.io.*;

/*
 *  메모리: 100,148 kb
 *  시간: 451 ms
 *  코드길이: 1,254
 * 
 */

public class SWEA_2819_격자판의숫자이어붙이기 {

	static Set<String> output;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1, T = Integer.parseInt(br.readLine()); t <= T; t++) {
			map = new int[4][4];
			output = new HashSet<>();
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) dfs(i, j, 0, new int[7]);
			sb.append(String.format("#%d %d%n", t, output.size()));
		}
		System.out.print(sb);
	}

	private static void dfs(int r, int c, int depth, int[] nums) {
		if (depth == 7) {
			output.add(Arrays.toString(nums));
			return;
		}
		nums[depth] = map[r][c];
		for (int d = 0; d < 4; d++){
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) continue;
			dfs(nr, nc, depth + 1, nums);
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < 4 && c < 4;
	}
}
