package SSWtest.SWEA.SWEA_4014;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
메모리: 34,068 kb
실행시간: 136 ms
코드길이: 5,306
*/

public class SWEA_4014 {
    static StringTokenizer st;
    static int N, X;
    static int[][] map;
    
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int count = 0;
            
            // 가로 -> 세로 탐색
            for (int i = 0; i < N; i++) {
                boolean[] check = new boolean[N];
                outer: for (int j = 1; j < N; j++) {
                    
                    // 탐색값이 이전값보다 2이상 크면, 또는 탐색값이 이전값보다 2이상 작으면.
                    
//                    System.out.println("i: "+i +" j: "+j);
                    if (map[i][j] - map[i][j - 1] >= 2 || map[i][j - 1] - map[i][j] >= 2) {
                        break outer;
                    }

                    // 탐색값이 이전값보다 크다면 (우상) -> 이전값부터 경사가되는지 확인
                    if (map[i][j] == map[i][j - 1] + 1) {
//                        System.out.println("chigh");
                        for (int j2 = 1; j2 <= X; j2++) {
                            int nj = j - j2;
                            if (nj < 0 || check[nj]) {
                                break outer;
                            }
                            if (map[i][nj] != map[i][j - 1]) {
                                break outer;
                            }
                            check[nj] = true;
                        }
                    }

                    // 탐색값이 이전값보다 작다면 (우하) -> 이후 값부터 경사가 되는지 확인
                    else if (map[i][j] == map[i][j - 1] - 1) {
//                        System.out.println("clow");
                        for (int j2 = 1; j2 <= X; j2++) {
                            int nj = j - 1 + j2;
                            if (nj >= N || check[nj]) {
                                break outer;
                            }
                            if (map[i][nj] != map[i][j]) {
                                break outer;
                            }
                            check[nj] = true;
                        }
                    }
                    if (j == N - 1) {
//                        System.out.println("check");
                        count++;
                    }
                }
            }
            
//            System.out.println("===============");
            
            // 세로 -> 가로 탐색 (temp)
            for (int j = 0; j < N; j++) {
                boolean[] check = new boolean[N];
                outer: for (int i = 1; i < N; i++) {
//                    System.out.println("i: "+i +" j: "+j);
                    
                    if (map[i][j] - map[i - 1][j] >= 2 || map[i - 1][j] - map[i][j] >= 2) {
                        break outer;
                    }

                    // 탐색값이 이전값보다 크다면 -> 이전값부터 경사가되는지 확인
                    if (map[i][j] == map[i - 1][j] + 1) {
//                        System.out.println("rhigh");
                        for (int i2 = 1; i2 <= X; i2++) {
                            int ni = i - i2;
                            if (ni < 0 || check[ni]) {
                                break outer;
                            }
                            if (map[ni][j] != map[i - 1][j]) {
                                break outer;
                            }
                            check[ni] = true;
                        }
                    }

                    // 탐색값이 이전값보다 작다면 -> 이후 값부터 경사가 되는지 확인 (여기가 문제인듯)
                    else if (map[i][j] == map[i - 1][j] - 1) {
//                        System.out.println("rlow");
                        
                        for (int i2 = 1; i2 <= X; i2++) {
                            int ni = i - 1 + i2;
//                            System.out.println("ni:" + ni +" check[ni]" + check[ni]);
                            if (ni >= N || check[ni]) {
                                break outer;
                            }
                            if (map[ni][j] != map[i][j]) {
                                break outer;
                            }
                            check[ni] = true;
                        }
                    }
                    if (i == N - 1) {
                        count++;
//                        System.out.println("check");                        
                    }
                }
            }
            
            
            System.out.printf("#%d %d\n", t, count);
        }
    }

}