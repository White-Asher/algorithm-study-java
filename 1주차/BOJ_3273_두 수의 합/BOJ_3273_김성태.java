package week_1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class BOJ3273 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		Set<Integer> nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
		final int TARGET = Integer.parseInt(br.readLine());
		System.out.println(nums.stream().filter(n -> nums.contains(TARGET - n)).count() / 2);
	}
}
