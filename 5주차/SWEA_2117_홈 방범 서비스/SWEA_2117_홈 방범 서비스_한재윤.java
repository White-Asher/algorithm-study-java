package SWEA.SSWtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 메모리: 23356 KB
 * 실행시간: 217 ms
 * 코드길이: 1907
 */
public class SWEA_2117 {
	static int[][] map;
	static List<int[]> houseList;
	static int N, M;
	static int ans;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		
		for (int tc = 1; tc <= t; tc++) {
			houseList = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) houseList.add(new int[] {i, j});
				} 
			}
			ans = 0;
			solution();

			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	public static void solution() {
		for (int searchX = 0; searchX < N; searchX++) {
			for (int searchY = 0; searchY < N; searchY++) {
				
				int K = (N%2 == 0) ? N+1 : N;
				while (true) {
					if (K == 0) break;
					int cost = (K * K) + (K - 1) * (K - 1);
					if(cost <= ans) break;
					int houseCnt = 0;
					for (int j = 0; j < houseList.size(); j++) {
						int[] targetHouse = houseList.get(j);
						int targetX = targetHouse[0];
						int targetY = targetHouse[1];
						if (isCoverage(searchX, searchY, targetX, targetY, K-1)) {
							houseCnt++;
						}
					}
					int calcResult = (houseCnt * M) - cost;
					if (calcResult >= 0) {
						ans = Math.max(houseCnt, ans);
					}
					K--;

				}
			}
		}

	}
	
	public static boolean isCoverage(int x1, int y1, int x2, int y2, int d) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2) <= d;
	}
}
