import java.io.*;
import java.util.*;

/*
메모리 : 12,124 KB
실행 시간 : 84 ms
코드 길이 : 2709 B
 */

class Pair implements Comparable<Pair> {
    int r, c, dist;

    Pair(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }

    @Override
    public int compareTo(Pair f) {
        if (this.dist == f.dist) {
            if (this.r == f.r)
                return this.c - f.c;

            return this.r - f.r;
        }
        return this.dist - f.dist;
    }
}

public class Main {
    static int n, body = 2, sizeUp, ans;
    static int sy, sx;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static PriorityQueue<Pair> q;

    public static void main(String[] args) throws IOException {
        input();
        move();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    sy = i; sx = j;
                    map[i][j] = 0;
                }
            }
        }
    }

    static void move() {
        while(true) {
            q = new PriorityQueue<>();
            q.add(new Pair(sy, sx, 0));
            visited = new boolean[n][n];
            visited[sy][sx] = true;

            boolean isEat = false;

            while(!q.isEmpty()) {
                Pair cur = q.poll();
                int y = cur.r;
                int x = cur.c;
                int dist = cur.dist;

                if (map[y][x] != 0 && map[y][x] < body) {
                    sy = y; sx = x;
                    map[y][x] = 0;
                    sizeUp++;
                    ans += dist;
                    isEat = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                    if (map[ny][nx] > body) continue;
                    if (visited[ny][nx]) continue;

                    visited[ny][nx] = true;
                    q.add(new Pair(ny, nx, dist + 1));

                }
            }

            if (!isEat)
                break;

            if (sizeUp == body) {
                body++;
                sizeUp = 0;
            }
        }
        System.out.println(ans);
    }

}