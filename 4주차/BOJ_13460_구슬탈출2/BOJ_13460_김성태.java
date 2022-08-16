package com.joykst96.practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  메모리: 18208 KB
 *  시간: 132 ms
 * 
 */

public class BOJ_13460 {
	static boolean isClear = false;
	static int[] hole = new int[2];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		Queue<int[][]> q = new LinkedList<>();
		int[][] start = new int[2][2];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				switch (map[i][j]) {
				case 'R':
					start[0][0] = i;
					start[0][1] = j;
					break;
				case 'B':
					start[1][0] = i;
					start[1][1] = j;
					break;
				case 'O':
					hole[0] = i;
					hole[1] = j;
					break;
				}
			}
		}
		q.offer(start);
		int depth = 1;
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		simulation: while(!q.isEmpty() && depth <= 10) {
			int size = q.size();
			for(int s = 0; s < size; s++) {
				int[][] pos = q.poll();
				int rx = pos[0][0];
				int ry = pos[0][1];
				int bx = pos[1][0];
				int by = pos[1][1];
				for (int d = 0; d < 4; d++) {
					int nrx = rx;
					int nry = ry;
					int nbx = bx;
					int nby = by;
					for (int i = 0; i < 2; i++) {
						while(
								nrx + dx[d] >= 0 && nry + dy[d] >= 0 && nrx + dx[d] < N && nry + dy[d] < M &&
								map[nrx + dx[d]][nry + dy[d]] != '#' && !(nrx + dx[d] == hole[0] && nry + dy[d] == hole[1]) && !(nrx + dx[d] == nbx && nry + dy[d] == nby)
							) {
							nrx += dx[d];
							nry += dy[d];
						}
						while(
								nbx + dx[d] >= 0 && nby + dy[d] >= 0 && nbx + dx[d] < N && nby + dy[d] < M &&
								map[nbx + dx[d]][nby + dy[d]] != '#' && !(nbx + dx[d] == hole[0] && nby + dy[d] == hole[1]) && !(nrx == nbx + dx[d] && nry == nby + dy[d])
							) {
							nbx += dx[d];
							nby += dy[d];
						}
					}
					if (nbx + dx[d] == hole[0] && nby + dy[d] == hole[1]) continue;
					if (nrx + dx[d] == hole[0] && nry + dy[d] == hole[1]) {
						if (nrx == nbx + dx[d] && nry == nby + dy[d]) continue;
						isClear = true;
						break simulation;
					}
					if (rx == nrx && ry == nry && bx == nbx && by == nby) continue;
					q.offer(new int[][] {{nrx, nry}, {nbx, nby}});
				}
			}
			++depth;
		}
		if (depth <= 10 && isClear) {
			System.out.println(depth);
		} else {
			System.out.println(-1);
		}
	}
}
