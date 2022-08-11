package week_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2164 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 맨 앞 원소를 아주 빈번하게 삭제시킬것이기 때문에 LL사용
		Queue<Integer> nums = new LinkedList<>();
		int n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		while (nums.size() > 1) {
			// 첫번째 뽑은건 버리고
			nums.poll();
			// 두번째 뽑은건 맨뒤로 보내기
			nums.offer(nums.poll());
		}
		System.out.println(nums.poll());
	}
}
