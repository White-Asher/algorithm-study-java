package com.algo.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SWEA_2112_보호필름 {
	
	private static int[][] films, copy;
	private static int depth;
	private static int width;
	private static int k;
	private static int min;

	// 2112. [모의 SW 역량테스트] 보호 필름
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		/* 문제풀이 전략 */
		/*
		 *  보호필름의 유효성 검사는 각각의 열에 대한 행의 DFS 라고 볼 수 있다. 
		 *  각 열에서의 연속된 값을 갖는 행의길이가 유효성 기준인 K를 만족하는지 판별해야 하므로,
		 *  
		 *  본 문제에서는 당연하게도 필름에 대해서 유효성 검사를 해줄 메서드가 필요할 것으로 생각했다.
		 * 
		 *  다음으로 유효성 검사를 한번 하고 마는 것이 아니라, 유효성 검사 통과를 위해 필름을 변형시켜줄(배열의 값을 변경해줄) 방법이 존재하므로,
		 *  이 방법을 곧 메서드로써 구현해야한다고 생각했다.
		 * 
		 *  마지막으로 이 문제를 푸는데 가장 핵심적인 부분이라고 생각한 부분인데,
		 *  약물의 종류는 2가지이고, 주입할 수도 있고 안할 수도 있으나 하나만 주입한다는 제약조건을 어떻게 구현할 것인가가 쟁점이었던 것 같다.
		 *  따라서, 이에 적합한 알고리즘으로 "부분집합"이 적합하다고 생각했고, 
		 *  이 문제의 경우에는 선택하고 안하고인 2가지 조건에 대한 부분집합이 아니라 
		 *  A, B, None 3가지 조건에 대한 부분집합을 사용해야 했다.
		 *  또한, 부분집합에서 변형되는 배열은 다시 초기 상태로 돌려놔야 한다는 점도 이 문제에서 중요한 포인트였던 것 같다.
		 *  아마 다음 재귀에서 안 꼬이게 하기 위함 같은데 정확한 이유는 솔직히 잘 모르겠다.
		 * 
		 */
		

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());		
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1 ; t <= T ; t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			depth = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			min = depth;
			
			films = new int[depth][width];
			copy = new int[depth][width];
			
			for(int i = 0 ; i < depth ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < width ; j ++) {
					films[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(isValid()) {
				sb.append(0+"\n");
				continue;
			}

			subset(0, 0);			
			sb.append(min+"\n");				
		}
		
		System.out.print(sb.toString());
	}

	// 필름의 유효성 검사 메서드
	private static boolean isValid() {
		
		for(int i = 0 ; i < width ; i ++) {
			int index = 1; // 행의 반복 시작을 0이 아닌 1부터 하고, 시작값은 항상 0번째 행의 값이므로 1부터 셈을 시작해야함.
			int current = copy[0][i];	// 초기 값
			
			for(int j = 1 ; j < depth ; j ++) {
				if(copy[j][i]!=current) {	// 같지 않으면 = 서로 다른 셀이면
					index = 1;	// 셀 카운트 계수 초기화 해주고
					current = copy[j][i];	// 현재 필름의 종류로 초기화
					continue;
				}				
				index++;	// 필름의 종류가 같으면 유효성 검사 k 기준과 비교를 위해 연속된 값의 길이를 셈함.
				
				if(index >= k ) break;	// 해당 열이 유효성 검사 기준을 충족하면 해당 열 반복 종료
			}
			if(index < k) return false;	// 모든 행을 다 보았는데도 유효성을 만족하지 못하면 즉시 함수를 종료하고 유효성 검사 실패함을 리턴해줌.
		}		
		return true;	// 그렇지 않으면 유효성 검사 통과했으므로  true를 리턴.
	}
	
	
	
	// 부분집합 메서드, 약물 A와 B를 넣어주는 역할을 하는 메서드
	private static void subset(int cnt, int idx) {
		
		// 필름에 약물을 주입하다보면, 
		// 처음에는 유효성 검사 결과 5개 열을 통과 했던게 3개 통과 하는 등 
		// 안하니만 못한 결과가 생길 수 있음, 그래서 이런 경우는 필름 전체에 약물을 넣어서 통일시키느니만 못하기 때문에
		// 백트래킹 기저 조건으로써 사용하고, 재귀를 종료함.
		if(cnt>min) return;	// min = 보호필름의 depth(깊이=행의 최대길이)
		
		if(idx==depth) {	// 최대 행의 길이만큼 재귀를 탔으므로, 그 때의 필름의 유효성 검사를 시작해야함.
			if(isValid()) { // static으로 선언된 배열(copy <- films 복사)과 isVaild 메서드를 통해 당시의 필름이 유효한지 판정함.
				min = Math.min(min, cnt);	// 약물 주입회수의 최소 비교(모든 필름의 경우의 수에 대해서 비교하게 됨).
			}
			return;
		}
		
		// 약물을 주입하지 않는 경우
		subset(cnt, idx+1);		
		
		// 약물 A를 주입하는 경우 (0을 A로 정함)
		copy[idx]= injection(width, copy[idx], 0);	// 1차원 배열을 하나의 값으로 초기화 시켜주는 메서드 = 약물 주입 메서드
		subset(cnt+1, idx+1);
		
		// 약물 B를 주입하는 경우 (1을 B로 정함)
		copy[idx]=injection(width, copy[idx], 1);		
		subset(cnt+1, idx+1);
		
		// 다음의 부분집합 탐색을 위해 현재 변형된 필름을 초기 상태로 돌려줌.
		for(int i = 0 ; i < width; i ++) {
			copy[idx][i] = films[idx][i];
		}
		
	}
	
	// 약물 주입 메서드
	private static int[] injection(int col, int[] layer, int drug) {		
		for(int i = 0 ; i < col; i ++) {
			layer[i] = drug;
		}		
		return layer;		
	}	

}
