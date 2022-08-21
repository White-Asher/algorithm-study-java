package com.joykst96.practice.swea;

import java.util.*;
import java.io.*;

/*
 *  메모리: 18,608 kb
 *  실행시간: 108 ms
 * 
 */

public class SWEA_8382_방향전환2 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/SWEA_8382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1, T = Integer.parseInt(br.readLine()); t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int[] start = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int[] end = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int dx = Math.abs(start[0] - end[0]);
			int dy = Math.abs(start[1] - end[1]);
			int result = 0;
			if ((dx + dy) % 2 == 0) result = Math.max(dx, dy) * 2;
			else result = Math.max(dx, dy) * 2 - 1;
			sb.append(String.format("#%d %d%n", t, result));
		}
		System.out.print(sb);
	}
}