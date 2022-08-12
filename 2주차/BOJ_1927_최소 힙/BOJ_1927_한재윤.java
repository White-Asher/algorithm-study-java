import java.io.*;
import java.util.PriorityQueue;

public class BOJ1927 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input == 0) {
				if(queue.size() == 0) {
					sb.append("0\n");
				} else {
					sb.append(queue.poll()).append("\n");
				}
			} else {
				queue.add(input);
			}
		}
		bw.write(String.valueOf(sb));
		bw.flush();
		bw.close();
	}
}
