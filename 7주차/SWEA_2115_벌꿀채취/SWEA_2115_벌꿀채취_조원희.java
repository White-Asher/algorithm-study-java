package com.algo.allAProblems;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2115_모의SW역량테스트_벌꿀채취{
	
	static int N, M, S;
	static int[][] maps;
	static ArrayList<Integer> honeys;

	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("data/swea2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			
			maps = new int[N][N];
			
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j ++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print(maps[i][j]+"\t");
				}
//				System.out.println();
			}
			
			max = Integer.MIN_VALUE;
			
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N-M+1 ; j++) {
					
					int[] honey1 = new int[M];
					int idx = 0;
					
					for(int k = j ; k < j+M ; k++) {
						honey1[idx++] = maps[i][k];
					}
					doBee(honey1, i, j+M, i, j);					
					
				}				
			}
			
			sb.append(max).append("\n");
			
		}
		System.out.println(sb.toString());
		

	}
	
	private static boolean[] isSelected;;
	static int Tsum, max;
	private static void subset(int[] honey, int idx, int sum) {
		
		if(sum > S) return;
		
		if(idx==M) {			
			int result = 0;
			for(int i = 0 ; i < M; i ++) {
				if(isSelected[i]) {
					result += honey[i]*honey[i];
				}
			}
			Tsum = Math.max(Tsum, result);
			return;
		}
		
		isSelected[idx] = true;
		subset(honey, idx+1, sum+honey[idx]);
		
		isSelected[idx] = false;
		subset(honey, idx+1, sum);
		
		
	}

	private static void doBee(int[] honey1, int startR, int startC, int x, int y) {
		
		for(int i = startR ; i < N ; i ++) {
			for(int j = i==x?startC:0 ; j < N-M+1 ; j++) {
				
				int[] honey2 = new int[M];
				int idx = 0;				
				for(int k = j ; k < j+M ; k++) {
					honey2[idx++] = maps[i][k];
				}
				
				int result = 0;
				Tsum = 0;
				isSelected = new boolean[M];
				subset(honey1, 0, 0);
				result+=Tsum;
				
				Tsum=0;
				isSelected = new boolean[M];
				subset(honey2, 0, 0);
				result+=Tsum;
				
//				System.out.println(result);
				
				max = Math.max(max, result);
				
				
				
				
//				System.out.println("---- 수확 통에 담긴 꿀 ----");
//				System.out.println(Arrays.toString(honey1)+" "+Arrays.toString(honey2));
//				
//				System.out.println("---- 벌통의 좌표 ----");
//				System.out.println("벌통 1 좌표 : (" +x+", "+y+" )");
//				System.out.println("벌통 2 좌표 : ("+i+", "+j+" )");
//				System.out.println("-----------------");
				
				
				
			}
		}
	}


}
