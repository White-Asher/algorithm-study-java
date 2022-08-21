package com.joykst96.practice.swea;

import java.io.*;

/*
 *  메모리: 24,032 kb
 *  시간: 126 ms
 *  코드길이: 1578 B
 * 
 */

public class SWEA_1227_미로2 {
    static int[] goal;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean isClear;
    static StringBuilder sb = new StringBuilder();
     
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            br.readLine();
            int[][] map = new int[100][100];
            boolean[][] visited = new boolean[100][100];
            goal = new int[2];
            isClear = false;
            int[] start = new int[2];
            for (int i = 0; i < 100; i++) {
                char[] temp = br.readLine().toCharArray();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = temp[j] - '0';
                    if (map[i][j] == 2) start = new int[] {i, j};
                    if (map[i][j] == 3) goal = new int[] {i, j};
                }
            }
            sb.append("#" + t + " ");
            dfs(start, map, visited);
            if (!isClear) sb.append("0\n");
        }
        System.out.print(sb);
    }
     
    static void dfs(int[] pos, int[][] map, boolean[][] visited) {
        if (isClear) return;
        if(pos[0] == goal[0] && pos[1] == goal[1]) {
            sb.append("1\n");
            isClear = true;
            return;
        }
        if (visited[pos[0]][pos[1]]) return;
        visited[pos[0]][pos[1]] = true;
        for (int d = 0; d < 4; d++) {
            int nx = pos[0] + dx[d];
            int ny = pos[1] + dy[d];
            if (nx < 0 || ny < 0 || nx >= 100 || ny >= 100) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] == 1) continue;
            dfs(new int[] {nx, ny}, map, visited);
        }
        visited[pos[0]][pos[1]] = false;
    }
}