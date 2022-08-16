package BOJ.BFSDFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리: 132736 KB
 * 시간: 520 ms
 * 코드길이: 2294 B
 */
public class BOJ_2573 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int[][] map;
	static StringTokenizer st;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		boolean checkInit = true;
		int islandNum = checkIslandNum();
		if(islandNum >= 2) checkInit = false;

		while (checkInit) {
			int[][] meltArr = new int[N][M];
			
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) { 
					// 이 부분의 로직을 수정하면 실행시간을 더 줄일 수 있음.
					if (map[n][m] != 0) {
						meltArr[n][m] = calcMelt(n, m);
					}
				}
			}
			
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (meltArr[n][m] != 0) {
						map[n][m] -= meltArr[n][m];
						if(map[n][m] < 0) map[n][m] = 0;
					}
				}
			}
			
			islandNum = checkIslandNum();
			ans++;
			
			if(islandNum >= 2) break;
			if(islandNum == 0) {
				ans = 0;
				break;
			}
			
		}
		System.out.println(ans);
	}
	
	public static int checkIslandNum() {
		int islandNum = 0; // 섬의 조각 수
		boolean[][] visited = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] != 0) {
					if (DFS(n, m, visited)) {
						islandNum++;
					}
				}
			}
		}
		return islandNum;
	}
	
	public static boolean DFS(int y, int x, boolean[][] visited) {
		if(y < 0 || x < 0 || y >= N || x >= M ) return false;
		if(visited[y][x] == false && map[y][x] != 0) {
			visited[y][x] = true;
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				DFS(ny, nx, visited);
			}
			return true;
		}
		return false;
	}

	public static int calcMelt(int y, int x) {
		int zeroCnt = 0;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny >= 0 && ny < N && nx >=0 && nx < M) {
				if(map[ny][nx] == 0) zeroCnt++;
			}
		}
		return zeroCnt;
	}
}
