package SWEA.SWNormal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 실행시간: 90652 KB
 * 실행시간: 275 ms
 * 코드길이: 1408 
 */
public class SWEA_2819 {
	static final int MAP_SIZE = 4;
	static int ans;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Set<String> setList;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			ans = 0;
			setList = new HashSet<>();
			int[][] map = new int[MAP_SIZE][MAP_SIZE];
			for (int i = 0; i < MAP_SIZE; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < MAP_SIZE; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < MAP_SIZE; i++) {
				for (int j = 0; j < MAP_SIZE; j++) {
					DFS(i,j,map,"");
				}
			}
			System.out.printf("#%d %d\n", tc, setList.size());
		}
	}
	
	public static void DFS(int x, int y, int[][] map, String num) {
		if(num.length() >= 7) {
			setList.add(num);
			return;
		}
		
		num += String.valueOf(map[x][y]);
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx >=0 && nx < MAP_SIZE && ny >= 0 && ny < MAP_SIZE) {
				DFS(nx, ny, map, num);
			}
			
		}
	
	}
}
