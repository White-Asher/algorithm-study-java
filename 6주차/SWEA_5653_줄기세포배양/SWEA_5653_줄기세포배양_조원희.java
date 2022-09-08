package practicePaper2;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_줄기세포배양_조원희{
	
	static boolean[][] isVisited;
	static int[][] maps;
	static int N, M, K, days;
	
	static Queue<Position> temp;
	static PriorityQueue<Position> pq;
	
	static class Position implements Comparable<Position>{
		int x, y, origin, life;
		
		public Position(int x, int y, int origin, int life) {
			super();
			this.x = x;
			this.y = y;
			this.origin = origin;
			this.life = life;
		}

		@Override
		public int compareTo(Position o) {			
			return -Integer.compare(origin, o.origin);
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T ; t++) {
			sb.append("#"+t+" ");
			
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			N = r + K*2 + 4;
			M = c + K*2 + 4;
			
			maps = new int[N][M];
			isVisited = new boolean[N][M];
			
			pq = new PriorityQueue<>();
			temp = new LinkedList<Position>();
			
			for(int i = N/2-1 ; i < N/2-1+r; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = M/2-1 ; j < M/2-1+c ; j ++) {
					int num = Integer.parseInt(st.nextToken());
					if(num!=0) {
						maps[i][j] = num;
						pq.offer(new Position(i, j, num, num*2));
						isVisited[i][j] = true;
					}
				}
			}
			
			bfs();
			
			sb.append(pq.size()).append("\n");									
		}				
		System.out.println(sb.toString());

	}

	private static int dx[] = {-1, 1, 0, 0};
	private static int dy[] = {0, 0, -1, 1};
	private static void bfs() {
		
		for(int t=1 ; t <= K ; t++) {
			while(!pq.isEmpty()) {
				Position cur = pq.poll();				
				cur.life = cur.life-1;

				// 원래 life보다 크기를 2배로 잡아서 origin이 커지면 그때부터 활성화
				if(cur.origin > cur.life) {
					for(int i = 0 ; i < 4 ; i ++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						
						if(nx < 0 || ny < 0 || nx > N || ny > M) continue;						
						if(isVisited[nx][ny]==false) {
							isVisited[nx][ny] = true;
							temp.offer(new Position(nx, ny, cur.origin, cur.origin*2));
						}
					}
				}
				if(cur.life != 0 ) temp.offer(new Position(cur.x, cur.y, cur.origin, cur.life));				
			}
			while(!temp.isEmpty()) {
				pq.offer(temp.poll());
			}
		}
		
		
	}

}