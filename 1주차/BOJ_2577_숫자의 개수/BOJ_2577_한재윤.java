package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2577 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());

		//		String[] multi = String.valueOf(a*b*c).split("");
		
		String temp = String.valueOf(a*b*c);
		int n = temp.length();
		String[] multi = new String[n];
		for(int i = 0; i < n ; i++) {
			multi[i] = String.valueOf(temp.charAt(i));
    	}
		
		int[] ans = new int[10];
		for (String str : multi) {
			ans[Integer.parseInt(str)]++;
		}
		
		for (int i : ans) {
			System.out.println(i);
		}
	}
}
