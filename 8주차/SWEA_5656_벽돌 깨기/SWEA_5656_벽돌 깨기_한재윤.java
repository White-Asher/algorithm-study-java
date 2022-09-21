import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
메모리 119,536 kb
실행시간 878 ms
코드길이 3,757
*/
public class SWEA_5656 {
	static int N, W, H;
	static int answer;
	static int[] nums;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			nums = new int[N]; // 볼 어디에 넣을지 위치를 저장하는 배열
			
			answer = Integer.MAX_VALUE;
			
			// 값 넣기
			for(int i = 0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			permutation(0);
			System.out.printf("#%d %d\n", t, answer);
		}
	}
	
	// 중복순열로 어디에 공을 넣을지 선택함. 
	public static void permutation(int cnt) {
		if(cnt == N) {
			// 공 위치 뽑았으면 시뮬레이션 돌리기
			game();
			return;
		}
		for(int i = 0; i < W; i++) {
			nums[cnt] = i;
			permutation(cnt+1);
		}
	}
	
	public static int[][] copyMap(){
		int[][] tempMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tempMap[i][j] = map[i][j];
			}
		}
		return tempMap;
	}
	
	public static void game() {
		int[][] copyMap = copyMap();
		
		// 구슬을 넣으면 벽돌 깨고 내리는 행동 -> N번만큼 반복함.   
		for (int i = 0; i < N; i++) {
			int x = -1; // 행
			int y = nums[i];
			
			// 깰 수 있는 벽 찾기
			for (int j = 0; j < H; j++) {
				// 깰 수 있는 벽 찾았으면 해당 행 좌표 저장
				if(copyMap[j][y] != 0) {
					x = j;
					break;
				}
			}
			
			// 깰 벽이 없다면 continue;
			if(x == -1) {
				continue;
			}
			
			// 벽 깨기
			breakBrick(x,y, copyMap);
			// 벽 깨고나서 벽돌 아래로 내리기
			downBrick(copyMap);
		}
		
		// 벽돌이 몇개 남았는지 체크하기
		int countBrick = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(copyMap[i][j] != 0) {
					countBrick++;
				}
					
			}
		}
		answer = Math.min(countBrick, answer);
	}

	// 벽돌 깨기
	public static void breakBrick(int x, int y, int[][] copyMap) {
		int n = copyMap[x][y];
		int[] dx = {0,0,-1,1};
		int[] dy = {-1,1,0,0};
		
		// 구슬이 해당 좌표의 블럭을 부수기 
		copyMap[x][y] = 0;
		// 블럭값이 1이면 자기 자신만 없어지므로 메서드 종료
		if(n==1) {
			return;
		}
		
		// 블럭이 2이상이면 주변 블럭 없애야 하므로 4방탐색 하면서 BFS
		for (int i = 0; i < 4; i++) {
			int nx = x;
			int ny = y;
			
			// 한쪽 방향으로 벽돌의 값만큼 반복하여 뽀개기  
			for (int j = 0; j < n-1; j++) {
				nx += dx[i];
				ny += dy[i];
				
				if(nx < 0 || ny < 0 || nx >= H || ny >= W) break;
				if(copyMap[nx][ny] == 0) continue;
				if(copyMap[nx][ny] == 1) copyMap[nx][ny] = 0;
				else {
					breakBrick(nx, ny, copyMap);
				}
				
			}
		}
		
	}
	
	public static void downBrick(int[][] copyMap) {
		Queue<Integer> q;
		// 전체 블럭을 탐색하면서 
		for (int i = 0; i < W; i++) {
			q = new LinkedList<>();
			//해당 열에 맨 아래 행부터 맨 위의 행까지 블럭이 몇개있는지 큐에 넣기
			for (int j = H - 1; j >= 0; j--) {
				if (copyMap[j][i] != 0) {
					q.add(copyMap[j][i]);
					copyMap[j][i] = 0; // 큐에 넣었으면 해당 행 0으로 만들어버리기 
				}
			}
			int x = H - 1; // 맨 마지막 행부터 탐색하면서 
			while (!q.isEmpty()) { // 큐가 빌때까지 아래부터 큐를 poll하면서 해당 값으로 채워넣기
				copyMap[x--][i] = q.poll();
			}
		}

	}

}
