import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  메모리: 18156 KB
 *  시간: 212 ms
 *  BFS 시간초과 -> 점간의 거리 
 */

public class BOJ_15686 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] city = new int[N][N];
		Queue<int[]> fromStore = new LinkedList<>();
		Queue<int[]> fromHome = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == 1) fromHome.offer(new int[] {i , j});
				else if (city[i][j] == 2) {
					fromStore.offer(new int[] {i, j});
					city[i][j] = 0;
				}
			}
		}
		
//		int[] dx = {1, -1, 0, 0};
//		int[] dy = {0 , 0, 1, -1};
		int ans = Integer.MAX_VALUE;
		
		int[][][] result = combination(fromStore.toArray(new int[0][0]), M);
		
		for (int[][] stores: result) {
			int chickenDistance = 0;
			for (int[] home: fromHome) {
				int minVal = Integer.MAX_VALUE;
				for (int[] store: stores) {
					int distance = Math.abs(home[0] - store[0]) + Math.abs(home[1] - store[1]);
					if (minVal > distance) minVal = distance;
				}
				chickenDistance+= minVal;
			}
			if (ans > chickenDistance) ans = chickenDistance;
		}
		
		System.out.println(ans);
		
//		for (int[][] simulation: result) {
//			for(int[] storePos: simulation) city[storePos[0]][storePos[1]] = 2;
//			int chikenDistance = 0;
//			for(int[] home: fromHome) {
//				Queue<int[]> q = new LinkedList<>();
//				q.offer(home);
//				boolean[][] isVisited = new boolean[N][N];
//				int ox = q.peek()[0];
//				int oy = q.peek()[1];
//				isVisited[ox][oy] = true;
//				boolean isFound = false;
//				int depth = 1;
//				h: while (!q.isEmpty() && !isFound) {
//					int size = q.size();
//					for (;size > 0; size--) {
//						int[] next = q.poll();
//						int x = next[0];
//						int y = next[1];
//						for (int i = 0; i < 4; i++) {
//							int nx = x + dx[i];
//							int ny = y + dy[i];
//							if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
//								continue;
//							}
//							if (isVisited[nx][ny]) {
//								continue;
//							}
//							if (city[nx][ny] == 2) {
//								chikenDistance += depth;
//								isFound = true;
//								break h;
//							}
//							isVisited[nx][ny] = true;
//							q.offer(new int[] {nx, ny});
//						}
//					}
//					++depth;
//				}
//			}
//			if (ans > chikenDistance) ans = chikenDistance;
//			for(int[] storePos: simulation) city[storePos[0]][storePos[1]] = 0;
//		}
//
//		System.out.print(ans);
	}

	private static int[][][] combination(int[][] arr, int r) {
		List<int[][]> result = new ArrayList<>();
		combination(arr, new int[r], new boolean[arr.length], 0, 0, r, result);
		return result.toArray(new int[0][0][r]);
	}

	private static void combination(int[][] arr, int[] out, boolean[] isSelected, int start, int depth, int r, List<int[][]> result) {
		if (depth == r) {
			List<int[]> element = new ArrayList<>();
			for (int i: out) element.add(arr[i]);
			result.add(element.toArray(new int[0][r]));
			return;
		}
		for (int i = start; i < arr.length; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				out[depth] = i;
				combination(arr, out, isSelected, i + 1, depth + 1, r, result);
				isSelected[i] = false;
			}
		}
	}
}