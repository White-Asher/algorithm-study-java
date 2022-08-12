import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10799 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int ans = 0;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				stack.push('(');
				continue;
			} else {
				stack.pop();
				if (input.charAt(i - 1) == '(') ans += stack.size();
				else ans += 1;
			}
		}
		System.out.println(ans);
	}
}
