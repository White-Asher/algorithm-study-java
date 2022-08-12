package BOJ.Deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ5430 {
	static StringBuilder sb;
	static ArrayDeque<Integer> deque;
	static boolean reverse;
	public static void main(String[] args) throws NumberFormatException, IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		deque = new ArrayDeque<Integer>();;
		sb = new StringBuilder();
		
		
		outer: while(T-- > 0) {
			String[] func = br.readLine().split("");
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
			
			for(int i = 0; i < n; i++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			
			reverse = true;
			
			for (String string : func) {
				if(string.equals("R")) {
					reverse = !reverse;
					continue;
				}
				
				if(reverse) {
					if(deque.pollFirst() == null) {
						sb.append("error").append("\n");
						continue outer;
					}
				} else {
					if(deque.pollLast() == null) {
						sb.append("error").append("\n");
						continue outer;
					}
				}
			}
			
			outString();
		}
		System.out.println(sb);
	}
	public static void outString() {
		sb.append("[");
		if(deque.size() != 0) {
			if(reverse) {
				sb.append(deque.pollFirst());
				while(!deque.isEmpty()) {
					sb.append(',');
					sb.append(deque.pollFirst());
				}
					
			} else {
				sb.append(deque.pollLast());
				while(!deque.isEmpty()) {
					sb.append(',');
					sb.append(deque.pollLast());
				}
			}
		}
		sb.append("]").append('\n');
	}
}




//public class BOJ5430 {
//	static boolean reverse;
//
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int t = Integer.parseInt(br.readLine());
//
//		for (int tc = 1; tc <= t; tc++) {
//			reverse = true;
//			String strFunc = br.readLine();
//			String[] func = strFunc.split("");
//
//			int funcLength = Integer.parseInt(br.readLine());
//
//			String input = br.readLine();
//			
//			Deque<Integer> deque = new ArrayDeque<>();
//			if (input.length() > 2) {
//				String inputData = input.substring(1, funcLength * 2);
//				for (int i = 0; i < inputData.length(); i++) {
//					int num = Character.getNumericValue(inputData.charAt(i));
//					if (num != -1) {
//						deque.add(num);
//					}
//
//				}
//
//				for (int i = 0; i < func.length; i++) {
//					if (func[i].equals("R")) {
//						reverse = !reverse;
//						continue;
//					}
//
//					else if (func[i].equals("D")) {
//						if (reverse) {
//							if (deque.pollFirst() == null) {
//								System.out.println("error");
//							}
//						} else {
//							if (deque.pollLast() == null) {
//								System.out.println("error");
//							}
//						}
//					}
//
//				}
//				if (reverse) {
//					StringBuilder sb = new StringBuilder();
//					sb.append("[");
//					if (!deque.isEmpty()) {
//						sb.append(deque.pollFirst());
//						while (!deque.isEmpty()) {
//							sb.append(",");
//							sb.append(deque.pollFirst());
//						}
//					}
//					sb.append("]");
//					System.out.println(sb);
//
//				} else {
//					StringBuilder sb = new StringBuilder();
//					sb.append("[");
//					if (!deque.isEmpty()) {
//						sb.append(deque.pollLast());
//						while (!deque.isEmpty()) {
//							sb.append(",");
//							sb.append(deque.pollLast());
//						}
//					}
//					sb.append("]");
//					System.out.println(sb);
//				}
//			} else {
//
//				if (strFunc.contains("D")) {
//					System.out.println("error");
//				} else {
//					System.out.println("[]");
//				}
//
//			}
//		}
//	}
//}
	
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			String[] func = br.readLine().split("");
			int funcLength = Integer.parseInt(br.readLine());

			String input = br.readLine();
			List<Integer> list = new ArrayList<>();
			
			if(input.length() > 2) {
				String inputData = input.substring(1,funcLength*2);		
				
				for (int i = 0; i < inputData.length(); i++) {
					if(i%2 == 0) {
						list.add(Character.getNumericValue(inputData.charAt(i)));
					}
				}
				
				for (int i = 0; i < func.length; i++) {
					if(func[i].equals("R")) {
						Collections.reverse(list);
					}
					else if(func[i].equals("D")) {
						if(list.size() == 0) {
							System.out.println("error");
						} else {
							list.remove(0);
						}
					}
				}
				if(list.size() != 0 ) {
					System.out.println(list);
				}
			} else {
				System.out.println("error");
			}

			
		}
	}*/


