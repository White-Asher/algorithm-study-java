package week_3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ_1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> people = IntStream.range(1, N + 1).boxed().collect(Collectors.toCollection(LinkedList::new));
		int target = K - 1;
		sb.append("<");
		while (people.size() > 1) {
			sb.append(people.remove(target) + ", ");
			target = (target + K - 1) % people.size();
		}
		sb.append(people.remove(0) + ">");
		System.out.println(sb);
	}
}
