package BOJ.Stack;

import java.util.Scanner;
import java.util.Stack;

public class BOJ1874 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    StringBuilder sb = new StringBuilder();

	    Stack<Integer> stack = new Stack<>();
	    
	    int N = sc.nextInt();
	    int start = 0;

	    while(N-- > 0){
	    	int target = sc.nextInt();
	    	if(target > start) {
	    		for (int i = start + 1; i <= target; i++) {
					stack.push(i);
					sb.append('+').append('\n');
				}
	    		start = target;
	    	} else if (stack.peek() != target){
	    		System.out.println("NO");
	    		return;
	    	}
	    	
	    	stack.pop();
	    	sb.append('-').append('\n');
	    }
	    System.out.println(sb);
	}
}
