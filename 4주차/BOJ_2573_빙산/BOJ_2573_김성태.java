package com.joykst96.practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  메모리: 299492 KB
 *  시간: 856 ms
 * 
 */

public class BOJ_2573 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> melt = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) q.offer(new int[] {i, j});
			}
		}
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int year = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] pos = q.poll();
				int x = pos[0];
				int y = pos[1];
				if (map[x][y] == 0) continue;
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
					if (map[nx][ny] == 0 && map[x][y] > 0) {
						melt.offer(new int[] {x, y});
					}
				}
			}
			while(!melt.isEmpty()) {
				int[] meltPos = melt.poll();
				int meltX = meltPos[0];
				int meltY = meltPos[1];
				if (map[meltX][meltY] > 0)--map[meltX][meltY];
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) q.offer(new int[] {i, j});
				}
			}
			++year;
			if (q.isEmpty()) {
				System.out.println(0);
				break;
			}
			if (size > q.size()) {
				boolean[][] isVisited = new boolean[N][M];
				Queue<int[]> iq = new LinkedList<>();
				iq.offer(new int[] {q.peek()[0], q.peek()[1]});
				while (!iq.isEmpty()) {
					int[] inPos = iq.poll();
					int ix = inPos[0];
					int iy = inPos[1];
					if (isVisited[ix][iy]) continue;
					isVisited[ix][iy] = true;
					for (int d = 0; d < 4; d++) {
						int nx = ix + dx[d];
						int ny = iy + dy[d];
						if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
						if (map[nx][ny] != 0) iq.offer(new int[] {nx, ny});
					}
				}
				for (int[] pos: q) {
					if (!isVisited[pos[0]][pos[1]]) {
						System.out.println(year);
						System.exit(0);
					}
				}
			}
		}
	}
}
