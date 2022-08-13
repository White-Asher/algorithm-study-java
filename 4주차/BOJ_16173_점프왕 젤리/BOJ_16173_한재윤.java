package BOJ.BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 11556KB / 실행시간: 96ms / 코드길이: 1729B
public class BOJ_16173 {
    static int[] dy = {1, 0};
    static int[] dx = {0,1};
    static boolean[][] visited;
    static int[][] map;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][N];
        if(BFS(0,0)) System.out.println("HaruHaru");
        else System.out.println("Hing");

    }
    public static boolean BFS(int y, int x){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {y,x});

        while(!queue.isEmpty()){

            int[] q = queue.poll();
            int qy = q[0];
            int qx = q[1];
            visited[qy][qx] = true;

            for(int d = 0; d < 2; d++) {
                int ny = qy + map[qy][qx] * dy[d];
                int nx = qx + map[qy][qx] * dx[d];

                if (ny >= 0 && ny < N && nx >= 0 && nx < N && visited[ny][nx] == false) {
                    if(ny == N-1 && nx == N-1){
                        return true;
                    }
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
        return false;
    }
}
