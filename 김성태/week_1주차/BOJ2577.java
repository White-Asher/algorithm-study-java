package week_1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ2577 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = Arrays.stream(String.valueOf(Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine())).split("")).mapToInt(Integer::parseInt).toArray();
		Map<Integer, Integer> counter = new HashMap<>();
		for (Integer i: arr) counter.put(i, counter.get(i) == null ? 1 : counter.get(i) + 1);
		for (int i = 0; i < 10; i++) System.out.println(counter.get(i) == null ? 0 : counter.get(i));
	}
}
