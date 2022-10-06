package com.joykst96.practice.swea;

import java.io.*;
import java.util.*;

/*
 *  메모리 : 54,316 kb
 *  실행시간 : 661 ms
 * 
 */

public class SWEA_2477_차량_정비소 {
	static class Customer {
		int no, avTime, recept, repair, waiting;
		
		public Customer(int no) {
			this(no, -1, -1, -1, -1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/SWEA_2477.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1, T = Integer.parseInt(br.readLine()); t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 접수창구 개수
			int M = Integer.parseInt(st.nextToken()); // 정비창구 개수
			int K = Integer.parseInt(st.nextToken()); // 고객의 수
			int A = Integer.parseInt(st.nextToken()) - 1; // 고객이 방문한 접수창구의 번호
			int B = Integer.parseInt(st.nextToken()) - 1; // 고객이 방문한 정비창구의 번호
			int[] Ntime = new int[N];
			int[] Mtime = new int[M];
			int[] Ktime = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) Ntime[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; ++i) Mtime[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; ++i) Ktime[i] = Integer.parseInt(st.nextToken());
			int time = 0;
			int complete = 0;
			int ans = 0;
			// 입구 대기열을 처리할 PQ
			PriorityQueue<Customer> entrance = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.no, c2.no));
			// 접수창구들
			Customer[] receptionDesks = new Customer[N];
			// 접수창구 -> 수리창구를 처리할 PQ
			PriorityQueue<Customer> repairWaiting = new PriorityQueue<>((c1, c2) -> {
				if (c1.avTime == c2.avTime) return Integer.compare(c1.recept, c2.recept);
				return Integer.compare(c1.avTime, c2.avTime);
			});
			// 수리창구들
			Customer[] repairDesks = new Customer[N];
			while (complete != K) {
				// 시간에 따른 고객 입장
				for (int i = 0; i < K; ++i) {
					if (Ktime[i] == time) {
						entrance.offer(new Customer(i + 1));
					}
				}
				// 접수창구 처리
				for (int i = 0; i < N; ++i) {
					if (receptionDesks[i] != null) {
						if (--receptionDesks[i].waiting == 0) {
							receptionDesks[i].avTime = time;
							repairWaiting.offer(receptionDesks[i]);
							receptionDesks[i] = null;
						}
					} 
					if (receptionDesks[i] == null && !entrance.isEmpty()) {
						receptionDesks[i] = entrance.poll();
						receptionDesks[i].recept = i;
						receptionDesks[i].waiting = Ntime[i];
					}
				}		
				// 수리창구 처리
				for (int i = 0; i < M; ++i) {
					if (repairDesks[i] != null) {
						if (--repairDesks[i].waiting == 0) {
							if (repairDesks[i].recept == A && repairDesks[i].repair == B) {
								ans += repairDesks[i].no;
							}
							repairDesks[i] = null;
							++complete;
						}
					} 
					if (repairDesks[i] == null && !repairWaiting.isEmpty()) {
						repairDesks[i] = repairWaiting.poll();
						repairDesks[i].repair = i;
						repairDesks[i].waiting = Mtime[i];
					}
				}	
				// 시간의 흐름
				++time;
			}
			
			ans = ans == 0 ? -1 : ans; 
			sb.append(String.format("#%d %d%n", t, ans));
		}
		System.out.print(sb);
	}
}
