package com.joykst96.practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {
	static char[][] map;
	static int R, C, connected;
	static int[] dx = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		connected = 0;
		for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
		for (int i = 0; i < R; i++) if(dfs(i, 0)) ++connected;
		System.out.println(connected);
	}

	private static boolean dfs(int x, int y) {
		map[x][y] = 'x';
		if (y == C - 1)	return true;
		for (int d = 0, ny = y + 1; d < 3; d++) {
			int nx = x + dx[d];
			if (nx < 0 || nx >= R || map[nx][ny] != '.') continue;
			if (dfs(nx, ny)) return true;
		}
		return false;
	}
}