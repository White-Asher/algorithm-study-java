package week_3주차;

/*
 *  메모리: 12224 KB
 *  시간: 600ms
 *  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		Queue<int[]> start = new LinkedList<>();
		int raw = 0;
		int day = 0;
		int[][][] map = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 0) ++ raw;
					else if (map[i][j][k] == 1) start.offer(new int[] {i, j, k});
				}
			} 
		}
		
		int[] dz = { 0, 0, 0, 0, 1, -1};
		int[] dy = { 0, 0, -1, 1, 0, 0};
		int[] dx = { 1, -1, 0, 0, 0, 0};
		
		while(raw > 0 && !start.isEmpty()) {
			int size = start.size();
			for (int i = 0; i < size; i++) {
				int[] pos = start.poll();
				int z = pos[0], y = pos[1], x = pos[2];
				
				for (int j = 0; j < 6; j++) {
					int mz = z + dz[j];
					int my = y + dy[j];
					int mx = x + dx[j];
					
					if (mz < 0 || my < 0 || mx < 0 || mz >= H || my >= N || mx >= M) continue;
					if (map[mz][my][mx] != 0) continue;
					
					--raw;
					map[mz][my][mx] = 1;
					start.offer(new int[] {mz, my, mx});
				}
			}
			++day;
		}
		System.out.println(raw == 0 ? day : -1);
	}
}
