package SSWtest.SWEA.SWEA_2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
메모리: 55,616 kb
실행시간: 244 ms
코드길이: 6,274
*/

public class SWEA_2477 {

	static class Customer {
		int no;
		int inputTime;
		int outputTime;
		int desk;

		public Customer(int no, int inputTime, int outputTime) {
			this.no = no;
			this.inputTime = inputTime;
			this.outputTime = outputTime;
		}

		public Customer(int no, int inputTime, int outputTime, int desk) {
			this.no = no;
			this.inputTime = inputTime;
			this.outputTime = outputTime;
			this.desk = desk;
		}
	}

	static StringTokenizer st;
	static int N, M, K, A, B;
	static int[] aPer, bPer, visit;
	static PriorityQueue<Customer> registerQueue, repairQueue;
	static Customer[] registerCounter, repairCounter;
	static List<Integer> recordRegister, recordRepair;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
			M = Integer.parseInt(st.nextToken()); // 정비 창구 개수
			K = Integer.parseInt(st.nextToken()); // 방문한 고객 수
			A = Integer.parseInt(st.nextToken()); // 타겟 고객 접수 창구번호
			B = Integer.parseInt(st.nextToken()); // 타겟 고객 정비 창구 번호

			aPer = new int[N];
			bPer = new int[M];
			visit = new int[K];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				aPer[i] = Integer.parseInt(st.nextToken()); // 각 창구당 접수 처리시간
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				bPer[i] = Integer.parseInt(st.nextToken()); // 각 창구당 정비 처리시간
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				visit[i] = Integer.parseInt(st.nextToken()); // 고객이 방문한 시간
			}

			registerQueue = new PriorityQueue<>((o1, o2) -> o1.no - o2.no);
			repairQueue = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					if (o1.inputTime == o2.inputTime)
						return o1.desk - o2.desk;
					return o1.inputTime - o2.inputTime;
				}
			});

			registerCounter = new Customer[N];
			repairCounter = new Customer[M];

			recordRegister = new ArrayList<>();
			recordRepair = new ArrayList<>();

			int index = 1;
			for (int i = 0; i < K; i++) {
				// 시간되면 방문자 넣기
				registerQueue.add(new Customer(index++, visit[i], 0));
			}
			simulate();

			int ans = 0;
			for (int a : recordRegister) {
				for (int b : recordRepair) {
					if (a == b) {
						ans += a;
					}
				}
			}
			if (ans == 0)
				ans = -1;
			System.out.printf("#%d %d\n", t, ans);
		}

	}

	// 먼저 창구를 이용하는 유저가 나오고 이후 유저가 해당 행위를 한다.
	public static void simulate() {

		int time = 0;
		while (true) {

			// 전부 비어있다면 즉, 모든 로직 전부 수행.
			if (checkNull()) {
				break;
			}

			// 수리창구에서 나올 사람 제거하기
			for (int i = 0; i < repairCounter.length; i++) {
				if (repairCounter[i] != null) {
					Customer curCustomer = repairCounter[i];
					if (curCustomer.outputTime == time) {
						repairCounter[i] = null;
					}

				}
			}

			// 접수창구에서 나온사람 수리 창구 대기 큐에 넣기
			for (int i = 0; i < registerCounter.length; i++) {
				if (registerCounter[i] != null) {
					Customer curCustomer = registerCounter[i];
					if (curCustomer.outputTime == time) {
						repairQueue.add(new Customer(curCustomer.no, curCustomer.outputTime, 0, i));
						registerCounter[i] = null;
					}
				}
			}

			// 수리 창구에 사람넣기
			while (true) {

				// 대기하는 고객 창구에 넣기
				if (repairQueue.size() == 0)
					break;
				Customer q = repairQueue.peek();

				// 해당 시간에 고객이 오면
				if (time >= q.inputTime) {

					boolean flag = true;

					// 해당 고객이 이용할 접수 창구 찾기
					for (int i = 0; i < repairCounter.length; i++) {

						// 해당 접수 창구가 비어있다면 해당 창구를 이용하는 고객을 넣기
						if (repairCounter[i] == null) {
							repairQueue.poll(); // 해당 고객을 대기 큐에서 제거
							q.outputTime = time + bPer[i]; // 창구를 이용하는 고객이 나오는 시간 조정
							repairCounter[i] = q; // 해당 접수 창구에 고객을 할당.

							// 찾으려는 창구에 사람이 들어오면 기록하기.
							if (i == B - 1) {
								recordRepair.add(q.no);
							}
							flag = false;
							break;
						}

					}
					// 창구가 비어있지 않다면 -> 기다려야 함.
					if (flag)
						break;
				}
				// 해당 시간에 고객이 오지 않으면 시간을 증가시키기 위해 넣는 과정 중지해야함
				else {
					break;
				}

			}

			// 접수 창구에 사람넣기
			while (true) {

				// 대기하는 고객 창구에 넣기
				if (registerQueue.size() == 0)
					break;
				Customer q = registerQueue.peek();

				// 해당 시간에 고객이 오면
				if (time >= q.inputTime) {

					boolean flag = true;

					// 해당 고객이 이용할 접수 창구 찾기
					for (int i = 0; i < registerCounter.length; i++) {
						// 해당 접수 창구가 비어있다면 해당 창구를 이용하는 고객을 넣기
						if (registerCounter[i] == null) {
							registerQueue.poll(); // 해당 고객을 대기 큐에서 제거
							q.outputTime = time + aPer[i]; // 창구를 이용하는 고객이 나오는 시간 조정
							registerCounter[i] = q; // 해당 접수 창구에 고객을 할당.

							// 찾으려는 창구에 사람이 들어오면 기록하기.
							if (i == A - 1) {
								recordRegister.add(q.no);
							}
							flag = false;
							break;

						}

					}
					// 창구가 비어있지 않다면 -> 기다려야 함.
					// break;
					if (flag)
						break;
				}
				// 해당 시간에 고객이 오지 않으면 시간을 증가시키기 위해 넣는 과정 중지해야함
				else {

					break;
				}

			}

			time++;

		}

	}

	public static boolean checkNull() {
		if (registerQueue.size() == 0 && repairQueue.size() == 0) {
			for (int i = 0; i < registerCounter.length; i++) {
				if (registerCounter[i] != null) {
					return false;
				}

			}
			for (int i = 0; i < repairCounter.length; i++) {
				if (repairCounter[i] != null) {
					return false;
				}
			}

			return true;
		}
		return false;
	}
}
