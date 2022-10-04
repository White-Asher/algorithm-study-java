package com.algo.week08;

import java.io.*;
import java.util.*;


public class SWEA_5656_벽돌깨기_solving {
	
	// 디버깅용 배열 출력 메서드
	private static void printMaps(int[][] copyMap) {
		for(int r = 0; r < copyMap.length ; r++) {
			for(int c = 0 ; c < copyMap[r].length ; c++) {
				System.out.print(copyMap[r][c]+"\t");
			}
			System.out.println();
		}		
	}
	
	
	static class Brick{
		int x, y, range;
		public Brick(int x, int y, int range) {
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}
	
	static int N, W, H, min;
	static int[][] maps;

	public static void main(String[] args) throws Exception {
		
		/* 문제풀이 전략 */
		/*
		 * 이번 문제는 BFS + 중복순열 문제였다. 또한, DFS로 하더라도 제대로 코드 짰으면 문제푸는데 지장 없다고 함.
		 * 자력으로 못풀어서 다른 풀이를 참조하여 코드를 작성했음.
		 * 
		 * 구슬이 한번 떨어진 곳에 다시 떨어질 수 있고(중복을 허용하고)
		 * 구슬이 떨어지는 "순서"에 따라 최종 결과가 달라질 수 있는(완전 탐색인데, 순열을 필요로 하는)
		 * 구현문제였습니다.
		 * 
		 * 중복순열의 구현까지는 어렵지 않게 잘 했으나,
		 * 제일 어려웠던 부분은 구슬의 연쇄 폭발(?) 메서드를 어떻게 구현하느냐, 즉 BFS를 어떻게 짤 것인가가 헷갈렸습니다.
		 * 벽돌 배열에 기록된 값은 벽돌이 폭발 했을 때 최대 확산범위를 의미하고, 이걸 어떻게 확산하게 할 지 구현하는 것에 어려움을 느꼈습니다.
		 * 
		 */
		
		
		System.setIn(new FileInputStream("data/swea5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; t++ ) {
			sb.append("#"+t+" ");			
			// N W H
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			maps = new int[H][W];
			
			for(int i = 0 ; i < H ; i ++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0 ; j < W ; j ++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			overPerm(maps, 0);
			sb.append(min).append("\n");			
			
		}
		System.out.print(sb.toString());
		
	}
	
	private static void overPerm(int[][] maps, int cnt) {
		
		if(cnt == N) {			
			int count = countBricks(maps);
			min = Math.min(count, min);
			return;
		}
		
		int[][] copyMap = new int[H][W];
		for(int c = 0 ; c < W ; c ++) {
			// 현재 열에서 구슬 찾기
			
			int r = 0;
			while(r < H && maps[r][c]==0)++r;
			if(r==H) {
				overPerm(maps, cnt+1);			
			}else {
				// 벽돌 배열의 복사
				copy(maps, copyMap);
								
				// 벽돌의 연쇄처리
				breakBricks(copyMap, r, c);
								
				// 벽돌의 중력처리
				gravityBricks(copyMap);

				// 다음 벽돌 던지기
				overPerm(copyMap, cnt+1);
			}
		}
	}
	
	private static int countBricks(int[][] maps) {
		int result = 0;
		for(int r = 0; r < H ; r ++) {
			for(int c= 0 ; c < W ; c ++) {
				if(maps[r][c]>0) result++;
			}
		}
		return result;
	}

	private static void gravityBricks(int[][] copyMap) {
		for(int c = 0 ; c < W ; c++) {
			Stack<Integer> st = new Stack<>();
			for(int r = 0 ; r < H ; r++) {
				if(copyMap[r][c]!=0) {
					st.push(copyMap[r][c]);
					copyMap[r][c]=0;
				}
			}
			
			for(int r = H-1 ; !st.isEmpty(); r--) {
				copyMap[r][c] = st.pop();
			}			
		}
	}
	
	
	private static final int[] dx = {-1,1,0,0};
	private static final int[] dy = {0,0,-1,1};
	private static void breakBricks(int[][] maps, int r, int c) {
		
		Queue<Brick> que = new LinkedList<Brick>();
		
		if(maps[r][c]>1) {
			que.offer(new Brick(r, c, maps[r][c]));			
		}
		
		maps[r][c] = 0;
		
		while(!que.isEmpty()) {
			Brick cur = que.poll();
			
			for(int d = 0 ; d < 4 ; d++) {
				int nx = cur.x;
				int ny = cur.y;
				for(int k = 1 ; k <cur.range; k++) {
					nx += dx[d];
					ny += dy[d];
					if(nx>=0 && ny>=0 && nx<H && ny<W && maps[nx][ny] > 0) {
						if(maps[nx][ny]>1) {
							que.offer(new Brick(nx, ny, maps[nx][ny]));
						}
						maps[nx][ny] = 0;
					}
				}				
			}
		}
		
	}
	

	private static void copy(int[][] maps, int[][] copyMap) {
		for(int i = 0 ; i < copyMap.length; i ++) {
			for(int j = 0 ; j < copyMap[i].length; j ++) {
				copyMap[i][j] = maps[i][j];
			}
		}
	}
	

	
	

}
