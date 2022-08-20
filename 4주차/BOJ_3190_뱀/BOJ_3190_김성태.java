package com.joykst96.practice.boj;

import java.util.*;
import java.io.*;

/*
 *  메모리: 25584 KB
 *  시간: 336 ms
 * 
 */

public class BOJ_3190_뱀 {
	static class Location {
		int r, c;
		
		Location(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof Location)) return false;
			if (this.r == ((Location)obj).r && this.c == ((Location)obj).c) return true;
			return false;
		}
		
		@Override
		public int hashCode() {
			return toString().hashCode();
		}
		
		@Override
		public String toString() {
			return String.format("[%d, %d]", r, c);
		}
	}
	
	static int N, head, time;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static Set<Location> apples;
	static Deque<Location> snake;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		apples = new HashSet<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apples.add(new Location(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
		}
		Map<Integer, Integer> events = new HashMap<>();
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			events.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0) == 'D' ? 1 : -1);
		}
		snake = new LinkedList<>();
		snake.add(new Location(0, 0));
		head = 0;
		time = 0;
		while (true) {
			++time;
			move();
			if (events.get(time) != null) turn(events.get(time));
		}
		
	}
	
	private static void move() {
		Location cur = snake.peekFirst();
		Location next = new Location(cur.r + dr[head], cur.c + dc[head]);
		if (!isIn(next) || snake.contains(next)) {
			System.out.println(time);
			System.exit(0);
		}
		snake.offerFirst(next);
		if (apples.contains(next)) apples.remove(next);
		else snake.pollLast();
	}

	private static void turn(int d) {
		head = (head + 4 + d) % 4;
	}
	
	private static boolean isIn(Location l) {
		return (l.r >= 0 && l.c >= 0 && l.r < N && l.c < N);
	}
}
