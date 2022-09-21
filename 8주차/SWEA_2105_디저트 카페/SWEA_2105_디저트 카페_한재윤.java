import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
메모리 31,020 kb
실행시간 173 ms
코드길이 2,707
*/

public class SWEA_2105 {
    static int N, startX, startY;
    static int[][] map;
    static StringTokenizer st;
    // 방향 우하 -> 좌하 -> 좌상 -> 우상
    static int[] dx = {1,1,-1,-1};
    static int[] dy = {1,-1,-1,1};
    static boolean[] isEat;
    static int result;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 1; t <= T; t++){
        	N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            
            // 맵데이터 입력
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 가장 많이 먹은 횟수
            result = 0;
            
            // 전 구간 탐색
            for (int i = 0; i < N-1 ; i++) {
				for (int j = 0; j < N-1; j++) {
					isEat = new boolean[101]; // 먹은 곳 방문 체크 배열 선언
					isEat[map[i][j]] = true;
					startX = i;
					startY = j;
					int prevX = -1;
					int prevY = -1;
					int eatCnt = 0;
					int direction = 0;
					
					DFS(i, j, prevX, prevY, eatCnt, direction);
				}
			}
            if(result == 0) result = -1;
            
            System.out.printf("#%d %d\n", t, result);
            
        }
      }
    
    public static void DFS(int x, int y, int prevX, int prevY, int eatCnt, int direction) {
    	// 우하 -> 좌하 -> 좌상 -> 우상 방향으로 탐색
    	for(int d = direction; d < 4; d++) {
    		int nx = x + dx[d];
    		int ny = y + dy[d];
    		
    		// 맵 범위를 벗어나면 continue
    		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
    			continue;
    		}
    		// 이전 경로를 탐색하면 continue
    		if(nx == prevX && ny == prevY ) {
    			continue;
    		}
    		// 원점으로 돌아옴
    		if(nx == startX && ny == startY) {
    			// 원점돌아왔으므로 카운트 증가하고 종료
    			result = Math.max(result, eatCnt+1);
    			return;
    		}
    		// 이미 먹으러 간 곳이면 continue
    		if(isEat[map[nx][ny]]) continue;
    		
    		
    		// DFS ...
    		// 위 조건들에 해당하지 않는 곳이면 한번도 방문하지 않은 곳임.
    		// 탐색할 곳 방문처리함
    		isEat[map[nx][ny]] = true;
    		DFS(nx,ny, x,y,eatCnt + 1, d);
    		
    		// 다른 좌표에서 탐색해야하니 true해준후 다시 false
    		isEat[map[nx][ny]] = false;
    		
    	}
    	
    }
    
    
}





