package SSWtest.SWEA.SWEA_2383;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
메모리: 43,808 kb
실행시간: 211 ms
코드길이: 4,715
*/

public class SWEA_2383_Refactoring_Remark {
	// 계단 좌표, 이동시간
	static class Stair{
		int x;
		int y;
		int moveTime;
		public Stair(int x, int y, int moveTime) {
			this.x = x;
			this.y = y;
			this.moveTime = moveTime;
		}
		
	}
	// 사람 초기 좌표, a계단 이동 + a계단을 이용할 수 있는 시간 , b계단 이동시간 + b계단을 이용할 수 있는 시간, 계단을 이용하고 나가는 시간
	static class Human{
		int x;
		int y;
		int distA;
		int distB;
		int outTime;
		
		public Human(int x, int y) {
			this.x = x;
			this.y = y;
			this.distA = 0;
			this.distB = 0;
		}
		
		public Human(int x, int y, int distA, int distB, int outTime) {
			this.x = x;
			this.y = y;
			this.distA = distA;
			this.distB = distB;
			this.outTime = outTime;
		}
		
	}

	static int[][] map; // 맵 
	static int[] select; // 부분집합
	static StringTokenizer st;
	static List<Stair> stairList; // 계단 리스트
	static List<Human> humanList; // 사람 리스트
	static int ans;
	
	// 거리 측정
	public static int calDist(int sx, int sy, int hx, int hy) {
		int dist = 0;
		dist = Math.abs(sx -hx) + Math.abs(sy - hy);
		return dist;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			stairList = new ArrayList<>();
			humanList = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 2) {
						stairList.add(new Stair(i, j, map[i][j]));						
					} else if(map[i][j] == 1) {
						humanList.add(new Human(i, j));
					}
				}
			}

			ans = Integer.MAX_VALUE;
			select = new int[humanList.size()];
			for (int i = 0; i < humanList.size(); i++) {
				int hx = humanList.get(i).x;
				int hy = humanList.get(i).y;
				// 도착하고나서 1초 뒤에 계단을 이용 할 수 있으므로 +1 을 해줌
				humanList.get(i).distA = (calDist(stairList.get(0).x, stairList.get(0).y, hx, hy) + 1);
				humanList.get(i).distB = (calDist(stairList.get(1).x, stairList.get(1).y, hx, hy) + 1);
			}
			
			DFS(0);
			System.out.printf("#%d %d\n",t,ans);

		}
	}
	
	// 사람 리스트 복사 
	public static List<Human> copyHumanList(){
		List<Human> temp = new ArrayList<>();
		for (int i = 0; i < humanList.size(); i++) {
			Human t = humanList.get(i);
			temp.add(new Human(t.x, t.y, t.distA, t.distB, t.outTime));
		}
		return temp;
	}
	
	public static int simulate() {
		// 부분집합으로 뽑은 사람 리스트에 넣기 (a계단 b계단)
		LinkedList<Human> aHumanList = new LinkedList<Human>(); 
		LinkedList<Human> bHumanList = new LinkedList<Human>();

		// 계단을 이용하고 있는 사람 리스트에 넣기 
		List<Human> aStairList = new ArrayList<>();
		List<Human> bStairList = new ArrayList<>();
		
		List<Human> copyList = copyHumanList();
		
		for (int i = 0; i < select.length; i++) {
			if(select[i] == 1) {
				aHumanList.add(copyList.get(i));
			} else if(select[i] == 2) {
				bHumanList.add(copyList.get(i));
			}
		}
		
		// 해당 계단으로 가장 가까이 올 수 있는 사람부터 정렬
		Collections.sort(aHumanList, (o1, o2) -> (o1.distA - o2.distA)); 
		Collections.sort(bHumanList, (o1, o2) -> (o1.distB - o2.distB));
		
		// a계단
		int aTime = 0;
		while(true) {
			// 해당 시간이 되면 계단 리스트에서 나갈 수 있는 사람 확인하기
			while(!aStairList.isEmpty()) {
				Human aStairQ = aStairList.get(0);
				if(aTime >= aStairQ.outTime) {
					aStairList.remove(0);
				} else {
					break;
				}
			}
			// 계단과 사람 리스트가 없으면 중지
			if(aHumanList.size() == 0 && aStairList.size()==0) {
				break;
			}
			
			// 계단에 사람 넣기 
			while(true) {
				if(aHumanList.size() == 0) break;
				Human q = aHumanList.peek();
				if(q.distA != aTime) break;
				// 계단에 3명 이하라면 계단 리스트에 넣기
				if(aStairList.size() < 3) {
					q.outTime = aTime + stairList.get(0).moveTime;
					aStairList.add(aHumanList.poll());
				} 
				// 계단 3명 이상이면 계단 리스트에 넣지말고 대기하기
				else  {
					for (int i = 0; i < aHumanList.size(); i++) {
						Human temp = aHumanList.get(i);
						// 만약 계단으로 진입하는 시간이 처음에 계단에 들어간사람이 나오는 시간보다 작다면 진입시간 바꿔줌
						// 그게 아니면 -> 처음에 계단에 진입한 사람보다 늦게 들어오는 것과 같음.
						if(temp.distA < aStairList.get(0).outTime) {
							temp.distA = aStairList.get(0).outTime;
							continue;
						}
					}
				}
			}

			aTime++;
		}
		
		//b계단
		int bTime = 0;
		while(! (bHumanList.isEmpty() && bStairList.isEmpty()) ) {
			// 해당 시간이 되면 계단 큐에서 사람 빼기
			while(!bStairList.isEmpty()) {
				Human bStairQ = bStairList.get(0);
				if(bTime >= bStairQ.outTime) {
					bStairList.remove(0);
				} else {
					break;
				}
			}
			
			if(bHumanList.size() == 0 && bStairList.size()==0) {
				break;
			}
			
			// 계단에 사람 넣기 
			while(true) {
				if(bHumanList.size() == 0)break;
				
				Human q = bHumanList.peek();
				if(q.distB != bTime) break;
				// 계단에 3명 이하라면 계단 리스트에 넣기
				if(bStairList.size() < 3) {
					q.outTime = bTime + stairList.get(1).moveTime;
					bStairList.add(bHumanList.poll());
				} 
				// 계단 3명 이상이면 계단 리스트에 넣지말고 대기하기 
				else {
					for (int i = 0; i < bHumanList.size(); i++) {
						Human temp = bHumanList.get(i);
//						temp.distB++;
						if(temp.distB < bStairList.get(0).outTime) {
							temp.distB = bStairList.get(0).outTime;
							continue;
						}
					}
				}
			}

			bTime++;

		}
		// 가장 긴 시간이 전체 걸리는 시간
		return Math.max(aTime, bTime); 
		
	}
	
	// a, b 계단 사용자 나누기 (부분집합)
	public static void DFS(int index) {
		if(index == humanList.size()) {
			ans = Math.min(simulate(), ans);
			return;
		}
		select[index] = 1;
		DFS(index+1);
		select[index] = 2;
		DFS(index+1);
	}
}
