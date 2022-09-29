package com.joykst96.practice.swea;

import java.io.*;
import java.util.*;

public class SWEA_4014_활주로_건설 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/SWEA_4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1, T = Integer.parseInt(br.readLine()); t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			int vaildRoad = 0;
			// 가로탐색
			for (int r = 0; r < N; r++) {
				boolean isDown = false;
				boolean isVaild = true;
				int pv = map[r][0];
				int resource = 0;
				for (int c = 0; c < N; c++) {
					if (pv == map[r][c]) {
						++resource;
					} else if (pv > map[r][c]) {
						if (Math.abs(pv - map[r][c]) != 1) {
							isVaild = false;
							break;
						}
						if (isDown) {
							if (resource < 0) {
								isVaild = false;
								break;
							}
							resource = 1 - L;
							isDown = true;
						} else {
							resource = 1 - L;
							isDown = true;
						}
					} else {
						if (Math.abs(pv - map[r][c]) != 1) {
							isVaild = false;
							break;
						}
						if (resource >= L) {
							resource = 1;
						} else {
							isVaild = false;
							break;
						}
					}
					pv = map[r][c];
				}
				if (resource < 0)
					isVaild = false;
				if (isVaild)
					++vaildRoad;
			}

			// 세로탐색
			for (int c = 0; c < N; c++) {
				boolean isDown = false;
				boolean isVaild = true;
				int pv = map[0][c];
				int resource = 0;
				for (int r = 0; r < N; r++) {
					if (pv == map[r][c]) {
						++resource;
					} else if (pv > map[r][c]) {
						if (Math.abs(pv - map[r][c]) != 1) {
							isVaild = false;
							break;
						}
						if (isDown) {
							if (resource < 0) {
								isVaild = false;
								break;
							}
							resource = 1 - L;
							isDown = true;
						} else {
							resource = 1 - L;
							isDown = true;
						}
					} else {
						if (Math.abs(pv - map[r][c]) != 1) {
							isVaild = false;
							break;
						}
						if (resource >= L) {
							resource = 1;
						} else {
							isVaild = false;
							break;
						}
					}
					pv = map[r][c];
				}
				if (resource < 0)
					isVaild = false;
				if (isVaild)
					++vaildRoad;
			}
			sb.append(String.format("#%d %d%n", t, vaildRoad));
		}
		System.out.print(sb);
	}
}
