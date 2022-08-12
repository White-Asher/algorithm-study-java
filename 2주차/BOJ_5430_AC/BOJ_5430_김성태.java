package week_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class FlexableLL {
	LinkedList<Integer> list;
	boolean isReverse;
	
	public FlexableLL() {
		list = new LinkedList<>();
		isReverse = false;
	}
	
	boolean isEmpty() {
		return list.size() == 0;
	}
	
	void flip() {
		isReverse = !isReverse;
	}
	
	void add(int i) {
		list.add(i);
	}
	
	int pop() {
		if (isEmpty()) return -1;
		if (isReverse) {
			return popReverse();
		} else {
			return popNormal();
		}
	}
	
	@Override
	public String toString() {
		String ret;
		if (isReverse) {
			ret = toStringReverse();
		} else {
			ret = toStringNormal();
		}
		return ret.replace(" ", "");
	}
	
	private int popNormal() {
		return list.removeFirst();
	}
	
	private int popReverse() {
		return list.removeLast();
	}
	
	private String toStringNormal() {
		return list.toString();
	}
	
	private String toStringReverse() {
		LinkedList<Integer> rList = new LinkedList<>(list);
		Collections.reverse(rList);
		return rList.toString();
	}
	
}

public class BOJ5430 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			char[] p = br.readLine().toCharArray();
			br.readLine();
			FlexableLL list = new FlexableLL();
			String[] nums = br.readLine().replaceAll("[\\[|\\]]", "").split(",");
			if (nums[0].length() > 0) Arrays.asList(nums).stream().mapToInt(Integer::parseInt).forEach(list::add);
			boolean flag = true;
			outer: for(char action: p) {
				switch (action) {
				case 'R':
					list.flip();
					break;
				case 'D':
					if (list.pop() < 0) {
						sb.append("error\n");
						flag = false;
						break outer;
					}
					break;
				}
			}
			if (flag) sb.append(list + "\n");
		}
		System.out.print(sb.toString());
	}
}
