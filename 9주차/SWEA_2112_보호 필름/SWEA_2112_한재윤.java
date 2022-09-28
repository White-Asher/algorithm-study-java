
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
메모리: 102,180 kb
실행시간: 803 ms
코드길이: 2,178
 */

/* 
생각해 봐야 할 것.
1. K = 1일 경우 입력이 정답 -> 탐색할 필요 없음
2. 약품 투입은 K값을 넘지 않음
3. 최소 약품 투입 수 보다 큰 경우면 pass
4. 한 줄 검사시 통과 조건을 만족하지 않으면 탐색 중지 -> 약품 투입
5. 성능 검사시 연속 K개 수를 만족하면(즉, 성능검사를 만족하면) 해당 행 이후는 탐색하지 않음
6. D-K+1줄 까지 연속 K개를 만족하지 않으면(성능검사를 만족하지 않으면 ) 탐색 중지 -> 약품 투입
7. A 약품을 투여하기 전, 행렬 복사본 저장 -> A약품 투여 -> B약품 투여 -> 원래값으로 복원
8. 검사하면서 해당 행이 같은 특정이면 경우의 수를 계산할 때 해당 행의 약품 특정을 부여하지 않음
 */

public class SWEA_2112 {
	static StringTokenizer st;
	static int D, W, K;
	static int[][] cell;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cell = new int[D][W];
			
			ans = Integer.MAX_VALUE;
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					cell[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			
			// BT 1
			// 통과 기준이 2 이상이면 탐색
			if(K > 1) {
				// 통과기준을 만족 못하면 탐색
				if(!check()) {
					DFS(0, 0);
				} 
				// 통과기준이 2이상인데 체크했더니 만족하면 탐색하지 않고 종료
				else {
					ans = 0;
				}
			} 
			// 통과기준이 1일 때는 탐색할 필요가 없음
			else {
				ans = 0;
			}
			
			System.out.printf("#%d %d\n", t, ans);
			
		}
		
	}

	// 각 행마다 같은 열에 원본 복사 A->B->복구 -> 다음 행 탐색.. 반복하여 약품 최소값 구하기 BT7
	public static void DFS(int totalCnt, int y) {
		if(check()) {
			ans = Math.min(ans, totalCnt);
			return;
		}
		
		if(totalCnt > ans || totalCnt > K) return; // BT2 , BT3

		if(y == D) return; // 모든 행 탐색완료
		
		// 탐색 전 맵 복사
		int[] copyMap = copyMap(y);
		
		// 특성을 바꾸지 않고 다음행 탐색
		DFS(totalCnt, y+1);
		
		// 해당 행 A특성으로 교체
		for (int x = 0; x < W; x++) cell[y][x] = 0;
		DFS(totalCnt+1, y+1);
		
		// 해당 행 B특성으로 교체
		for (int x = 0; x < W; x++) cell[y][x] = 1;
		DFS(totalCnt+1, y+1);
		
		// 맵 원복
		for (int x = 0; x < W; x++) cell[y][x] = copyMap[x];
		
	}
	
	// 만족하는지 체크.
	public static boolean check() {
		for (int x = 0; x < W; x++) {
			int count = 1;
			int maxCnt = 0;
			outer:for (int y = 0; y < D - 1; y++) {
				if (cell[y][x] == cell[y+1][x]) { // 현재 검사 하는 곳과 다음 행과 같은지 비교
					count++; // 같다면 카운트 증가
				} else {					
					// 같은 셀이 아니므로 카운트 1로 초기화 
					count = 1;
				}
				
				maxCnt = Math.max(maxCnt, count);

				if (count == K) { // 통과. BT5
					continue outer;
				}
				// ex. D = 6, K= 3 011010에서 3번째 인덱스에서 연속된 숫자가 
				// K보다 작고 3번째 인덱스와 4번째 인덱스 비교시 일치하지 않으면 
				// 가능성이 없는 해
				if (y == D - K && maxCnt < K) { // BT6
					if(cell[y][x] != cell[y+1][x]) {
						return false;
					}
				}
				
			}
			if(maxCnt < K) return false;
		}
		return true;
	}
	
	public static int[] copyMap(int y){
		int[] temp = new int[W];
		for (int x = 0; x < W; x++) {
			temp[x] = cell[y][x];
		}
		return temp;
	}
}
