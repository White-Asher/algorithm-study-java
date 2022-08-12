package week_1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BOJ1475 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = Arrays.stream(br.readLine().replace("9","6").split("")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		Map<Integer, Integer> counter = new HashMap<>();
		for (Integer i: list) counter.put(i, counter.get(i) == null ? 1 : counter.get(i) + 1);
		if (counter.get(6) != null) counter.put(6, (int)Math.round(counter.get(6) / 2.0));
		System.out.println(counter.values().stream().max((o1, o2) -> Integer.compare(o1, o2)).get());
	}
}
