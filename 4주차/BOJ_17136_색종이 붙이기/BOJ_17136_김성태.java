package com.joykst96.practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17136 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[10][10];
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) q.offer(new int[] {i, j});
			}
		}
		// 누적합 행렬
		int[][]cumsum = new int[10][10];
		// 원배열 복사
		for (int i = 0; i < 10; i++) for(int j = 0; j < 10; j++) cumsum[i][j] = map[i][j];
		// 행의 누적합 적용
		for (int i = 0; i < 10; i++) for (int j = 1; j < 10; j++) cumsum[i][j] += cumsum[i][j - 1];
		// 열의 누적합 적용
		for (int i = 1; i < 10; i++) for (int j = 0; j < 10; j++) cumsum[i][j] += cumsum[i - 1][j];
		
		int toFill = q.size();
		int[] paper = {5, 5, 5, 5, 5};
		boolean[][] isFill = new boolean[10][10];
		int paperSize = 4;
		
		while (!q.isEmpty() && toFill > 0 && paperSize > 0) {
			int size = q.size();
			outer: for (int k = 0; k < size; k++) {
				int[] pos = q.poll();
				int x = pos[0];
				int y = pos[1];
				q.offer(new int[] {x, y});
				if (isFill[x][y]) continue;
				if (paper[paperSize] == 0) continue;
				if (x + paperSize >= 10 || y + paperSize >= 10) continue;
				// 덧씌우기 방지
				for (int i = x; i <= x + paperSize; i++) for (int j = y; j <= y + paperSize; j++) if (isFill[i][j]) continue outer;
				// 누적합 가져오기
				int area = cumsum[x + paperSize][y + paperSize];
				// 중복부분 빼기
				if (x - 1 >= 0) area -= cumsum[x - 1][y + paperSize];
				if (y - 1 >= 0) area -= cumsum[x + paperSize][y - 1];
				// 이중중복부분 더하기
				if (x - 1 >= 0 && y - 1 >= 0) area += cumsum[x - 1][y - 1];
				// 꽉 차있을때 붙인다
				if (area == (paperSize + 1) * (paperSize + 1)) {
					for (int i = x; i <= x + paperSize; i++) {
						for (int j = y; j <= y + paperSize; j++) {
							isFill[i][j] = true;
						}
					}
					toFill -= area;
					--paper[paperSize];
				}
			}
			paperSize--;
		}
		if (toFill > 5) {
			System.out.println(-1);
		} else {
			int ans = 0;
			ans += toFill;
			for (int i = 0; i < 5; i++) {
				ans += 5 - paper[i];
			}
			System.out.println(ans);
		}
	}
}
