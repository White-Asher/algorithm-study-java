package SWEA.SWNormal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 메모리: 29742 KB
 * 실행시간: 149 ms
 * 코드길이: 1390
 */
public class SWEA_1227 {
	static int startX, startY, endX, endY;
	static final int MAP_SIZE = 100;
	static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int t = Integer.parseInt(br.readLine());
			ans = 0;
			
			
			
			int[][] map = new int[MAP_SIZE][MAP_SIZE];
			
			for (int i = 0; i < MAP_SIZE; i++) {
				String inputLine = br.readLine();
				for (int j = 0; j < MAP_SIZE; j++) {
					int input = Integer.parseInt(String.valueOf(inputLine.charAt(j)));
					map[i][j] = input;
					if(input == 2) {
						startX = i;
						startY = j;
					} 
				}
			}
			boolean[][] visited = new boolean[100][100];
			DFS(startX, startY, visited, map);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	public static void DFS(int x, int y, boolean[][] visited, int[][] map) {
		visited[x][y] = true;
		
		if (map[x][y] == 3) {
			ans = 1;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx >= 0 && nx < MAP_SIZE && ny >= 0 && ny < MAP_SIZE && map[nx][ny] == 0 || map[nx][ny] == 3) {
				if (!visited[nx][ny]) {
					DFS(nx, ny, visited, map);
				}
			}

		}
		
	}
}
