package week_3주차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6853 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/SWEA_6853.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int[] s1 = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int[] s2 = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int N = Integer.parseInt(br.readLine());
			int[][] points = new int[N][];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				points[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			int case1 = 0;
			int case2 = 0;
			int case3 = 0;
			for (int[] point: points) {
				int x = point[0];
				int y = point[1];
				// 안에있을때
				if (s1[0] < x && x < s2[0] && s1[1] < y && y < s2[1]) {
					++case1;
				}
				// 테두리에 겹칠때
				else if (((s1[0] == x || s2[0] == x) && (s1[1] <= y && y <= s2[1])) || ((s1[1] == y || s2[1] == y) && (s1[0] <= x && x <= s2[0]))) {
					++case2;
				}
				// 바깥에 있을때
				else {
					++case3;
				}
				
			}
			sb.append(String.format("#%d %d %d %d%n", t, case1, case2, case3));
		}
		System.out.println(sb);
	}
}
