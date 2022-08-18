import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리: 13008 KB
 * 시간: 88ms
 * 코드길이: 931 B
 */
public class BOJ_1074 {
	static int cnt, r, c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		solution((int) Math.pow(2, N), 0, 0);
	}
	
	public static void solution(int size, int y, int x) {
		if(y == r && x == c) {
			System.out.println(cnt);
			System.exit(0);
		}
		
		if (y <= r && r < y + size && x <= c && c < x + size) {
			size = size / 2;
			solution(size, y, x); // 1
			solution(size, y, x + size); // 2
			solution(size, y + size, x); // 3
			solution(size, y + size, x + size); // 4
			
		} else cnt += size*size;
	}
}
