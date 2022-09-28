package algos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	
	private static int size;
	private static int x;
	private static int[][] maps;

	// 4014. [모의 SW 역량테스트] 활주로 건설
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t <= T ; t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			maps = new int[size][size];
			
			
			for(int i = 0 ; i < size ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < size; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = countValid(maps, x);
			sb.append(cnt).append("\n");			
		}
		System.out.print(sb.toString());
		
		
		

	}
	
	private static int countValid(int[][] maps, int x) {
		
		// 활주로 건설용 슬라이더의 높이는 1이고 길이는 가변적이다.
		// 2차원 배열에서 슬라이더를 설치 가능한 경우의 수를 중복으로 모두 셈해야 하므로,
		// 행을 고정시키고 열을 탐색하는 것과 열을 고정시키고 행을 탐색하는 것이 필요하다.
		
		// 2차원 배열을 탐색할 때, 탐색의 종료 조건은 다음의 세가지이다.
		// 1. 단차가 1 이상 나는 경우
		// 2. 슬라이더의 길이가 확보되지 않는 경우 (배열을 벗어나거나, 길이가 짧거나)
		// 3. 슬라이더가 이미 설치된 경우(탐색할 때 값이 1,2,3  이런식으로 공차가 1인 등차수열이 되버리면, 슬라이더가 겹쳐서 배치될 수 있음)
		
		// 따라서, 다음의 세 가지를 반복문의 탐색 종료 조건을 잘 주어서 판정하면 된다.
		// 단, 위의 세가지 조건 중에서 2번 조건의 경우 DFS 라고도 볼 수 있는데, 그렇기에 boolean 배열이 필요해지는 것 같고
		// 지금 알고리즘에서도 사용 했다.
		
		// 사실 정사각형 배열이라서 길이 하나로 다 해도 상관 없지만, 그냥 코드에서 변수명으로 의미상 구분을 위해,
		// 배열의 행과 열의 길이의 값을 담은 변수를 만듦.
		int row = maps.length;
		int col = maps[0].length;
		
		// 총 경우의 수를 카운팅할 변수
		int res = 0;		
		
		// 행 검사
		for (int i = 0; i < row; i++) {
			boolean isValid = true;
			boolean[] slide = new boolean[col];	// 2번 조건을 위해 사용될 방문체크 배열.
			
			L:for(int j = 1; j < col; j++) {
				if(maps[i][j] == maps[i][j-1]) continue;	// 값이 같다 = 평평하다 이므로, 슬라이더 설치 필요 없으니까 반복문 진행.				
				
				if(Math.abs(maps[i][j]-maps[i][j-1])>=2) {	// 단차가 1을 초과하면 절대 활주로를 설치할 수 없으므로 반복문 종료.
					isValid = false;
					break L;
				}
				
				// 단차가 1이고, 오르막 활주로가 필요한 경우.
				if(maps[i][j]-maps[i][j-1]==1) {
					
					// 단차가 1인 경우 활주로 설치를 위한 길이가 확보되었는지 탐색하는 반복문(= DFS?)
					for(int k=2;k<=x;k++) {						
						// 종료 조건 : 범위 / 값의 일치 여부 / 활주로 설치 여부
						if(j-k<0||maps[i][j-k+1]!=maps[i][j-k]||slide[j-k]) {
							isValid = false;
							break L;
						}
					}
					// 위의 배열을 무사 통과 했다 = 활주로 설치 가능하다.
					if(isValid) {
						// 활주로를 설치한다 = 방문체크 배열에 방문체크를 해준다.
						for(int k = 1; k <= x; k++) {
							slide[j-k] = true;
						}
					}
				}else {
					// 내리막 활주로가 필요한 경우, 오르막과 로직은 동일하다.
					for(int k=1;k<x;k++) {
						if(j+k>=col || maps[i][j+k-1] != maps[i][j+k] || slide[j+k]) {
							isValid = false;
							break L;
						}
					}
					// 활주로 설치.
					if(isValid) { 
						for(int k = 0; k < x; k++) {
							slide[j+k] = true;
						}
					}
				}
			}
			// 오르막 또는 내리막 설치가 됐다면 isValid의 값은 초기값 true에서 변하지 않았으므로,
			// 가능한 경우의 수를 증가시킴.
			if(isValid) {
				res++;
			}
		}
		
		// 열 검사
		// 행을 탐색했던 것을 열을 탐색하는 것으로만 바뀌고, 로직이 동일함.
		for (int j = 0; j < col; j++) {
			boolean isValid = true;
			boolean[] slide = new boolean[row];
			L:for(int i = 1; i < row; i++) {
				if(maps[i][j] == maps[i-1][j]) continue;
				
				if(Math.abs(maps[i][j]-maps[i-1][j])>=2) {
					isValid = false;
					break L;
				}
				
				if(maps[i][j]-maps[i-1][j]==1) {
					//오르막길
					for(int k=2;k<=x;k++) {
						if(i-k<0||maps[i-k+1][j]!=maps[i-k][j]||slide[i-k]) {
							isValid = false;
							break L;
						}
					}
					if(isValid) {
						for(int k = 1; k <= x; k++) {
							slide[i-k] = true;
						}
					}
				}else {
					for(int k=1;k<x;k++) {
						if(i+k >= row||maps[i+k-1][j]!=maps[i+k][j]||slide[i+k]) {
							isValid = false;
							break L;
						}
					}
					if(isValid) {
						for(int k = 0; k < x; k++) {
							slide[i+k] = true;
						}
					}
				}
			}
			if(isValid) {
				res++;
			}
		}
		
		
		return res;
			
		
		
	
	}
	
	

}
