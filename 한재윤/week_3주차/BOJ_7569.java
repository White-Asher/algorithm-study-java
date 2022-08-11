import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리: 128596KB 실행시간: 628ms 코드길이: 1903B
public class BOJ_7569 {
	static StringTokenizer st;
	static int[] dx = {0,0,-1,1,0,0};
	static int[] dy = {-1,1,0,0,0,0};
	static int[] dh = {0,0,0,0,1,-1};
	static int[][][] map;
	static int N, M, H;
	static Queue<int[]> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N][M];
		queue = new LinkedList<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if(map[i][j][k] == 1) {
						int[] input = {i,j,k};
						queue.add(input);
					}
				}
			}
		}
		BFS();
		System.out.println(solution());
	}
	public static void BFS() {

		while(!queue.isEmpty()) {
			int[] t = queue.remove();
			int h = t[0];
			int y = t[1];
			int x = t[2];
			
			for(int d = 0; d < 6; d++) {
				int nh = h + dh[d];
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(nx>=0 && ny>=0 && nh>=0 && nh<H && ny<N && nx<M) {
					if(map[nh][ny][nx] == 0) {
						int[] addIn = {nh, ny, nx};
						queue.add(addIn);
						map[nh][ny][nx] = map[h][y][x] + 1;
					}
				}
			}
		}
	}
	
	public static int solution() {
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(map[i][j][k] == 0) return -1;
					result = Math.max(result, map[i][j][k]);
				}
			}
		}
		if(result == 1) return 0;
		else return result-1;
	}
}
