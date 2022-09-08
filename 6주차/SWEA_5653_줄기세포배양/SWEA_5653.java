package testground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
메모리 : 112,960 kb
실행시간 : 311 ms
코드길이 : 4107KB
 */

public class SWEA_5653 {
    static StringTokenizer st;
    // 세로, 가로, 시간, 결과
    static int N, M, K, result;
    static int[][] map; // 맵
    static boolean[][] visited; // 방문 체크
    // 4방향 탐색
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static final int MAP_SIZE = 1200; // 맵 크기
    static PriorityQueue<Cell> cells;
    static class Cell implements Comparable<Cell>{
        // 좌표, 생명력, 활성화 시간
        int x, y, lifePower, lifeTime;
        public Cell(int x, int y, int lifePower, int lifeTime){
            this.x = x;
            this.y = y;
            this.lifePower = lifePower;
            this.lifeTime = lifeTime;
        }

        // 생명력이 가장 큰 세포부터 내림차순 정렬.
        @Override
        public int compareTo(Cell o) {
            // 비교한 두 셀의 활성화 시간이 다르면 가장 활성화 시간이 빠른 것 부터 정렬.
            if(lifeTime != o.lifeTime)
                return lifeTime - o.lifeTime;
            // 비교한 두 셀의 활성화 시간이 같다면 생명력이 큰 셀부터 정렬.
           return -(this.lifePower - o.lifePower);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 세로
            M = Integer.parseInt(st.nextToken()); // 가로
            K = Integer.parseInt(st.nextToken()); // 시간

            map = new int[MAP_SIZE][MAP_SIZE]; // 맵 선언
            visited = new boolean[MAP_SIZE][MAP_SIZE]; // 방문 배열
            cells = new PriorityQueue<Cell>(); // 셀 BFS를 위한 큐
            result = 0;

            for (int i = MAP_SIZE / 2; i < N + MAP_SIZE / 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = MAP_SIZE / 2; j < M + MAP_SIZE / 2; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    map[i][j] = input;
                    // 해당 좌표가 세포라면
                    if(input != 0){
                        visited[i][j] = true;
                        // 세포의 좌표, 세포의 생명력, 활성화 시간
                        cells.add(new Cell(i,j, input, input+1));
                        // 해당 세포가 K시간 이후에 비활성화라면 카운트.
                        if(input*2 > K) result++;
                    }
                }
            }

            BFS();
            System.out.printf("#%d %d\n", t, result);
        }
    }

    static void BFS(){
        int curLifePower = 0;
        int curLifetime = 0;

        while (curLifetime <= K){
            // 디버그용 테스트
//            for(Cell c : cells){
//                System.out.println("Debug => c.x:"+c.x+" c.y:"+c.y+" c.lifePower:"+c.lifePower+" c.lifeTime:"+c.lifeTime);
//            }
//            System.out.println("===");

            Cell curCell = cells.poll();
            curLifePower = curCell.lifePower;
            curLifetime = curCell.lifeTime;

            // 목표한 시간에 도달했을 때 BFS탈출 (K시간이 지나면..)
            if(curLifetime > K) break;

            // 4방탐색
            for (int d = 0; d < 4; d++) {
                int nx = curCell.x + dx[d];
                int ny = curCell.y + dy[d];

                // 탐색했던 곳이 방문하지 않은곳이라면.
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;

                    // K시간이후 현재시간 + 생명력*2 시간까지 활성상태인 세포면 카운트.
                    if(curLifetime + curLifePower * 2 > K) {
                        result++;
                    }
                    // 현재 셀의 생명력, 현재시간 + 생명력 = 활성화 시간
                    cells.add(new Cell(nx, ny, curLifePower, curLifetime + curLifePower+1));
                }
            }
        }
    }
}
