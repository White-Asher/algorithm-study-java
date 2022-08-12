package BOJ.NandM;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ_15666 {
	static int N, R, inputLenght;
	static int[] input;
	static int[] nums;

	static HashSet<Integer> temp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		nums = new int[R];
		temp = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			temp.add(sc.nextInt());
		}

		input = new int[temp.size()];

		int i = 0;
		for (int n : temp) {
			input[i++] = n;
		}
		Arrays.sort(input);
		solution(0, 0);
	}
	
	public static void solution(int cnt, int start) {
		if(cnt == R) {
			for (int i : nums) {
				System.out.print(i+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i < temp.size(); i++) {
			nums[cnt] = input[i];
			solution(cnt+1, i);
		}
	}
}
