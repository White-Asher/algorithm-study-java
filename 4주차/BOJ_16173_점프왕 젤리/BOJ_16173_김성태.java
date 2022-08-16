package com.joykst96.practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  메모리: 13032 KB
 *  시간: 96 ms
 *  isVisited 안쓰면 메모리초과뜸
 * 
 */

public class BOJ_16173 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<int[]> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][N];
		q.offer(new int[] {0, 0});
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			if (isVisited[x][y]) continue;
			if (map[x][y] == -1) {
				System.out.println("HaruHaru");
				System.exit(0);
			}
			isVisited[x][y] = true;
			// 오른쪽
			if (y + map[x][y] < N) q.offer(new int[] {x, y + map[x][y]});
			// 아래
			if (x + map[x][y] < N) q.offer(new int[] {x + map[x][y], y});
		}
		System.out.println("Hing");
	}
}
