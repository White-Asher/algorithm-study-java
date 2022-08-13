package BOJ.BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 14084KB / 실행시간: 112ms / 코드길이 1402B
public class BOJ_1012 {
	static StringTokenizer st;
	static int[][] map;
	static int M,N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t<T; t++) {
			st = new StringTokenizer(br.readLine()); 
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 1) {
						if (DFS(i, j, visited)) answer+=1;
					}
				}
			}
			System.out.println(answer);
		}
	}
	
	public static boolean DFS(int y, int x, boolean[][] visited) {
		if(y < 0 || x < 0 || y >= N || x >= M) return false;
		if(visited[y][x] == false && map[y][x] == 1) {
			visited[y][x] = true;
			DFS(y-1,x,visited);
			DFS(y+1,x,visited);
			DFS(y,x+1,visited);
			DFS(y,x-1,visited);
			return true;
		}
		return false;
	}
}
