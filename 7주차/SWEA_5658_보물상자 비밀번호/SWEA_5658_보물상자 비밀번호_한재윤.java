package SSWtest.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
메모리 20,580 kb
실행시간 136 ms
코드길이 2,026
*/

public class SWEA_5658 {
	static class Box implements Comparable<Box>{
		String value;
		int decimalVal;
		public Box(String value, int decimalVal) {
			this.value = value;
			this.decimalVal = decimalVal;
		}
		@Override
		public int compareTo(Box o) {
			return -(this.decimalVal-o.decimalVal);
		}
	}
	
	static int N, K;
	static HashSet<String> pwSet;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			String[] input = br.readLine().split("");
			int area = input.length/4;
			pwSet = new HashSet<>();
			LinkedList<Box> queue = new LinkedList<>();
			// 기본상태
			getNum(input, area);
			for (int i = 0; i < area; i++) {
				rotate(input, input.length);
				getNum(input, area);
			}

			for (String str : pwSet) {
				queue.offer(new Box(str, Integer.parseInt(str, 16)));
			}
			Collections.sort(queue);

			int ans = queue.get(K-1).decimalVal;
			
			System.out.printf("#%d %d\n", t, ans);
		}
		
	}
	
	// 해당 면적의 값을 set에 저장
	public static void getNum(String[] input, int area) {
		for (int i = 0; i < 4; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0 + area*i; j < area + area*i; j++) {
				sb.append(input[j]);
			}
			String str = sb.toString();
			pwSet.add(str);
		}
	}
	
	// 회전시키는 메서드
	public static String[] rotate(String[] arr, int arrLength) {
		String temp = arr[arrLength-1];
		for (int i = arrLength-1; i >=1; i--) {
			arr[i] = arr[i-1]; 
		}
		arr[0] = temp;
		return arr;
	}
}
