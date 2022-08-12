package week_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1927 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int next = Integer.parseInt(br.readLine());
			if (next > 0) {
				pq.add(next);
			} else {
				Integer ret = pq.poll();
				if(ret == null) ret = 0;
				sb.append(ret + "\n");
			}
		}
		System.out.println(sb);
	}
}
