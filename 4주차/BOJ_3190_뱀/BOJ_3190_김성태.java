package com.joykst96.practice.boj;

import java.io.*;
import java.util.*;

class Pos {
	int x;
	int y;
	
	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pos)) return false;
		return this.x  == ((Pos)obj).x && this.y  == ((Pos)obj).y;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		return String.format("[%d, %d]", x, y);
	}
}

public class BOJ_3190 {
	
	static Set<Pos> apples;
	static Deque<Pos> snake;
	static int head, time, N, size;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()) + 1;
		apples = new HashSet<>();
		for (int i = 0, K = Integer.parseInt(br.readLine()); i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apples.add(new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
		}
		Map<Integer, Character> tasks = new HashMap<>();
		for (int i = 0, n = Integer.parseInt(br.readLine()); i < n; i++) {
			st = new StringTokenizer(br.readLine());
			tasks.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		snake = new LinkedList<>();
		snake.offer(new Pos(0, 0));
		head = 0;
		time = 0;
		while (true) {
			if (tasks.get(time) == null) {
				move();
			}
			else {
				turn(tasks.get(time));
				move();
			}
		}
	}

	private static void move() {
		++time;
		Pos headPos = snake.peek();
		Pos nP = new Pos(headPos.x + dx[head], headPos.y + dy[head]);
		if (!isIn(nP) || snake.contains(nP)) {
			System.out.println(time);
			System.exit(0);
		}
		snake.offerFirst(nP);
		if (apples.contains(nP)) apples.remove(nP);
		else snake.pollLast();
	}

	private static void turn(char task) {
		if (task == 'L') --head;
		else ++head;
		head %= 4;
	}
	
	private static boolean isIn(Pos p) {
		return (p.x >= 0 && p.y >= 0 && p.x < N && p.y < N); 
	}
}
