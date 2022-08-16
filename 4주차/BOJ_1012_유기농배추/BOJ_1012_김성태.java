package com.joykst96.practice.boj;

/*
 *  메모리: 13692 KB
 *  시간: 120 ms
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[M][N];
			Queue<int[]> q = new LinkedList<>(); 
			boolean[][] isVisited = new boolean[M][N];
			int bugs = 0;
			int[] dx = {1, -1, 0, 0};
			int[] dy = {0, 0, -1, 1};
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1 && !isVisited[i][j]) {
						q.offer(new int[] {i, j});
						while(!q.isEmpty()) {
							int[] pos = q.poll();
							int x = pos[0];
							int y = pos[1];
							if (isVisited[x][y]) continue;
							isVisited[x][y] = true;
							for (int k = 0; k < 4; k++) {
								int nx = x + dx[k];
								int ny = y + dy[k];
								if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
								if (isVisited[nx][ny] == true) continue;
								if (map[nx][ny] == 1) {
									q.offer(new int[] {nx, ny});
								}
							}
						}
						++bugs;
					}
				}
			}
			
			
			sb.append(bugs + "\n");
		}
		System.out.print(sb);
	}
}
