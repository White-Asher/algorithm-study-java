package com.algo.week08;

import java.io.*;
import java.util.*;

public class SWEA_2105_모의SW역량테스트_디저트카페{
	
	static int[][] map;
	static int N, R, C, ans;
//	private static boolean[][] isVisited;
	private static boolean[] isVisited;

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("data/swea2105.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		

		
		for(int t = 1 ; t <= TC ; t++) {
			sb.append("#"+t+" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0 ; i < N ; i ++){
				st = new  StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print(map[i][j]+"\t");
				}
//				System.out.println();
			}
			
			ans = 0;
			for(int i = 0 ; i < N ; i ++){
				for(int j = 0 ; j < N ; j ++){
					R=i;
					C=j;
					isVisited = new boolean[101];
					isVisited[map[i][j]] =true;
					dfs(new Node(i, j, -1,-1, 0, 0));
				}
			}
			sb.append(ans==0?-1:ans).append("\n");
			
		}
		System.out.println(sb.toString());
		

	}
	
	private static class Node {
		int x, y, pX, pY, dir, value;
		
		Node(){}
		
		Node(int x, int y, int pX, int pY, int dir, int value){
			this.x = x;
			this.y = y;
			this.pX = pX;
			this.pY = pY;
			this.dir = dir;
			this.value = value;
		}
		
	}
	
	private static final int[] dx = {1,1,-1,-1};
	private static final int[] dy = {1,-1,-1,1};

//	private static void bfs(int r, int c) {
//		boolean[][] isVisited = new boolean[N][N];
//		Queue<Node> q = new LinkedList<>();
//		q.offer(new Node(r, c, -1, -1, 0));
//		
//		while(!q.isEmpty()) {
//			Node cur = q.poll();
//			System.out.println(cur.x+" / "+cur.y + " / "+ cur.value+" / "+map[cur.x][cur.y]);
//			
//			for(int d = 0 ; d < 4 ; d++) {
//				int nx = cur.x + dx[d];
//				int ny = cur.y + dy[d];
//				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
//				if(nx==cur.pX && ny==cur.pY) continue;
//				if(isVisited[nx][ny]) continue;
//				isVisited[nx][ny] = true;
//				q.offer(new Node(nx, ny, cur.x, cur.y, cur.value+1));
//			}			
//		}
//	}
	
	private static void dfs(Node node) {
		
		for(int d = node.dir ; d < 4 ; d++ ) {
			
			int nx = node.x + dx[d];
			int ny = node.y + dy[d];
			
			// 범위 체크
			if(nx < 0 || ny < 0 || nx >= N || ny >= N)continue;
			
			// 이전 경로를 탐색하면 continue			
			if(node.pX==nx && node.pY==ny) continue;
			
			// 종료조건
			if(nx == R && ny == C) {
				ans = Math.max(ans, node.value+1);
				return;
			}
			
			
			// 이미 먹으러 간 곳이면 continue
			if(isVisited[map[nx][ny]]) continue;
						
			// DFS
			isVisited[map[nx][ny]] = true;
			dfs(new Node(nx, ny, node.x, node.y, d, node.value+1));
			isVisited[map[nx][ny]] = false;

			
		}
		
	}

	/*
#1 6
#2 -1
#3 4
#4 4
#5 8
#6 6
#7 14
#8 12
#9 18
#10 30
	 */
	


}
