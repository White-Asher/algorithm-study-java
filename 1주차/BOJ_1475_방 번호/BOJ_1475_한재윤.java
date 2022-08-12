package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1475 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] multi = br.readLine().split("");
		int[] room = new int[10];
		for (String str : multi) {
			if(Integer.parseInt(str) == 6 | Integer.parseInt(str)== 9) {
				room[6]++;
			}else {
				room[Integer.parseInt(str)]++;
			}
		}
		
		int ans = 0;
		for (int i = 0; i < room.length; i++) {
			int temp = room[i];
			if(i == 6) {
				temp = (int) Math.ceil((double)room[i] / 2);
			}
			if(temp > ans) {
				ans = temp;
			}
		}
		System.out.println(ans);
	}
}
