import java.io.*;
import java.util.*;

/*
*   메모리 : 19,908 KB
*   실행 시간 : 292 ms
*   코드 길이 : 2134 B
*/

public class Main {
    static int[][] map;
    static int ans = 26;
    static int[] cp = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        map = new int[11][11];

        for (int i = 1; i <= 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        ans = ans == 26 ? -1 : ans;
        sb.append(ans);
        System.out.println(sb);
    }

    static void dfs(int cnt) {
        if (ans < cnt) return;

        if (isPaste(1, 1, 10, 1)) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (map[i][j] == 1) {
                    for (int size = 5; size > 0; size--) {
                        if (cp[size] > 0 && checkRange(i + size - 1, j + size - 1) && isPaste(i, j, size, 0)) {
                            cp[size]--;
                            pastePaper(i, j, size, 0);

                            dfs(cnt + 1);

                            cp[size]++;
                            pastePaper(i, j, size, 1);
                        }
                    }
                    return;
                }
            }
        }
    }

    static boolean isPaste(int r, int c, int size, int fill) {
        for (int i = r; i < r + size; i++)
            for (int j = c; j < c + size; j++)
                if (map[i][j] == fill)
                    return false;
        return true;
    }

    static void pastePaper(int r, int c, int size, int paper) {
        for (int i = r; i < r + size; i++)
            for (int j = c; j < c + size; j++)
                map[i][j] = paper;
    }

    static boolean checkRange(int r, int c) {
        if (r > 10 || c > 10) return false;
        return true;
    }
}