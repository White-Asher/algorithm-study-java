package BOJ.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int num = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int ans = 0;
		int lt = 0;
		int rt = n-1;
		
		while(lt < rt) {
			int sum = arr[lt] + arr[rt];
			if (sum == num) {
				lt++;
				rt--;
				ans++;
			} else if(sum > num) {
				rt--;
			} else lt++;
		}
		System.out.println(ans);
	}
}

// 시간 초과
/*
public class BOJ3273 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int num = Integer.parseInt(br.readLine());
		int ans = 0;
		for (int j = 0; j < n; j++) {
			for (int k = j+1; k < n; k++) {
				int temp = arr[j] + arr[k];
				if(temp == num) ans++;
			}
		}
		System.out.println(ans);
	}
}
*/