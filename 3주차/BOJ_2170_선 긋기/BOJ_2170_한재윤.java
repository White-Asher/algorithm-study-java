package BOJ.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 메모리: 367752KB / 실행시간: 2924 ms / 코드길이 1079 B
public class BOJ_2170 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] line = new int[N][2];
		for(int n = 0; n < N ; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			line[n][0] = Integer.parseInt(st.nextToken());
			line[n][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(line, Comparator.comparing(o1 -> o1[0]));
		int minPoint = line[0][0];
		int maxPoint = line[0][1];
		int ans = maxPoint - minPoint;
		
		for(int i = 1; i < N; i++) {
			if(minPoint <= line[i][0] && line[i][1] <= maxPoint) continue;
			else if(line[i][0] < maxPoint) ans += (line[i][1] - maxPoint);
			else ans += line[i][1] - line[i][0];
			minPoint = line[i][0];
			maxPoint = line[i][1];
		}
		System.out.println(ans);
	}
}

// 다른 사람 풀이 메모리:301024 KB / 실행시간: 956 ms / 코드길이 1639 B
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Line{
		int start;
		int end;
		
		public Line() {
			
		}
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = 0, start = 0, end = 0, res = 0;
		Line line1, line2, temp;
		Queue<Line> que = new LinkedList<>(), nque = new LinkedList<>();
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		que.offer(new Line(start, end));
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			line1 = new Line(start, end);
			
			while(!que.isEmpty()) {
				line2 = que.poll();
				
				if(line1.start > line2.start) {
					temp = line1;
					line1 = line2;
					line2 = temp;
				}
				
				if(line1.end > line2.start) {
					if(line1.end < line2.end)
						line1.end = line2.end;
				}else {
					nque.offer(line2);
				}
			}
			nque.offer(line1);
			while(!nque.isEmpty())
				que.offer(nque.poll());
		}
		
		while(!que.isEmpty()) {
			temp = que.poll();
			res += temp.end - temp.start;
		}
		
		sb.append(res);
		System.out.println(sb);
	}
}
*/