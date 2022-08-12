package BOJ.Implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 메모리: 293936KB, 시간: 584ms, 코드길이 647B 
public class BOJ_1158 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> people = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			people.add(i);
		}
		sb.append("<");
		while(!(people.isEmpty())) {
			for(int j = 0; j < K-1 ; j++) {
				people.add(people.poll());
			}
			sb.append(people.poll());
			if(people.size() != 0) {
				sb.append(", ");
			}
		}
		sb.append(">");
		System.out.println(sb);
	}
}

// 규칙을 발견하여 풀기 메모리:14068KB, 시간: 180ms, 코드길이 820B 
/*
public class BOJ_1158 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Integer> people = new LinkedList<>();
		List<Integer> result = new LinkedList<>();
		for (int i = 1; i <= N; i++) people.add(i);
		
		int popNum = 0;
		while(people.size() > 0) {
			popNum = (popNum + (K-1)) % people.size();
			int popElement = people.remove(popNum);
			result.add(popElement);
		}
		sb.append("<");
		for (int i = 0; i < result.size()-1; i++) {
			sb.append(result.get(i)).append(", ");
		}
		sb.append(result.get(result.size()-1));
		sb.append(">");
		System.out.println(sb);
	}
}
*/