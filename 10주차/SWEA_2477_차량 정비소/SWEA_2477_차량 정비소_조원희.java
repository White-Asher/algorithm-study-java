package com.algo.week10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_2477_차량정비소v2 {
	
	
	static class Client{
		int customerNo;
		int receptionNo;
		int repaireNo;
		int arriveTime;
		int receptionStartTime;
		int receptionEndTime;
		int repairStartTime;

		public Client(int customerNo, int arriveTime) {
			this.customerNo = customerNo;
			this.arriveTime = arriveTime;
		}
	}
		
	static int N, M, K, A, B;
	static Client[] regBox, repBox;
	static int[] regTime, repTime;
	static PriorityQueue<Client> receptionQueue;
	static PriorityQueue<Client> repairQueue;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/swea2477.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T ; t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 접수창구 수
			M = Integer.parseInt(st.nextToken());	// 정비창구 수
			K = Integer.parseInt(st.nextToken());	// 당일 방문 고객 수
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			regBox = new Client[N+1];
			regTime = new int[N+1];
			
			repBox = new Client[M+1];
			repTime = new int[M+1];
			
			receptionQueue = new PriorityQueue<>(new Comparator<Client>() {
				@Override
				public int compare(Client o1, Client o2) {
					return o1.customerNo - o2.customerNo;
				}
			});
			repairQueue = new PriorityQueue<>(new Comparator<Client>() {
				@Override
				public int compare(Client o1, Client o2) {
					if(o1.receptionEndTime == o2.receptionEndTime) {
						return o1.receptionNo - o2.receptionNo;
					} else {
						return o1.receptionEndTime - o2.receptionEndTime;
					}
				}
			});
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; i ++) {
				regTime[i] = Integer.parseInt(st.nextToken());							
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= M ; i ++) {
				repTime[i] = Integer.parseInt(st.nextToken());							
			}
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < K ; i++ ) {
				Client c = new Client(i+1, Integer.parseInt(st.nextToken()));
				receptionQueue.offer(c);
			}
			
			
			goTime(sb);
			
		}
		System.out.print(sb.toString());

	}

	private static void goTime(StringBuilder sb) {
		int ans = 0;
		int time = 0;
		int cnt = 0;
		while(true) {
			// 접수 끝난 사람 정비창구로 보내기
			for(int i = 1 ; i <= N ; ++i) {
				if(regBox[i] == null) continue;
				
				Client c = regBox[i];
				
				if(c.receptionStartTime + regTime[i] <= time) {
					c.receptionEndTime = time;
					repairQueue.offer(c);
					regBox[i] = null;
				}
			}
			
			// 접수창구 채우기 
			for(int i = 1 ; i <= N ; ++i) {
				if(regBox[i] == null) {
					if(!receptionQueue.isEmpty()) {
						if(receptionQueue.peek().arriveTime <= time) {
							regBox[i] = receptionQueue.poll();
							regBox[i].receptionNo = i;
							regBox[i].receptionStartTime = time;
						}
					}
				}
			}
			
			// 정비 끝난 사람 내보내기
			for(int i = 1 ; i <= M ; ++i) {
				if(repBox[i] == null) continue;
				
				Client c = repBox[i];
				
				if(c.repairStartTime + repTime[i] <= time) {
					if(c.receptionNo == A && c.repaireNo == B) ans += c.customerNo;
					repBox[i] = null;
					cnt++;
				}
			}
			
			if(cnt == K) break;
			
			// 정비창구 채우기
			for(int i = 1 ; i <= M ; ++i) {
				if(repBox[i] == null) {
					if(!repairQueue.isEmpty()) {
						repBox[i] = repairQueue.poll();
						repBox[i].repaireNo = i;
						repBox[i].repairStartTime = time;
					}
				}
			}
			
			time++;
		}
		sb.append(ans==0?-1:ans).append("\n");
		
	}

	
}
