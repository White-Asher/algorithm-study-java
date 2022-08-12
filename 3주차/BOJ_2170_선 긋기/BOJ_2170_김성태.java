package week_3주차;

/*
 *  메모리: 333792 KB
 *  시간: 2568 ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2170 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] lines = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(lines, (l1, l2) -> {
			if (l1[0] == l2[0]) return Integer.compare(l1[1], l2[1]);	// x가 같으면 y순
			return Integer.compare(l1[0], l2[0]);						// 별일없으면 x순
		});
		
		int px = lines[0][0];
        int py = lines[0][1];
        int totalLength = py - px;
        for(int i = 1; i < n; i++) {
        	// 범위안
            if(px <= lines[i][0] && lines[i][1] <= py) {
                continue;
            // y 확장
            } else if(lines[i][0] < py) {
                totalLength += lines[i][1] - py;
            // 새로운 선
            } else {
                totalLength += lines[i][1] - lines[i][0];
            }
            px = lines[i][0];
            py = lines[i][1];
        }
        System.out.println(totalLength);
	}
}
