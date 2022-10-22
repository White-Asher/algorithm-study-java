package com.joykst96.practice.programmers;

import java.util.StringTokenizer;

public class PGS_12939_최댓값과_최솟값 {
	
	static class Solution {
	    public String solution(String s) {
	        StringTokenizer st = new StringTokenizer(s, " ");
	        int min = Integer.MAX_VALUE;
	        int max = Integer.MIN_VALUE;
	        while (st.hasMoreTokens()) {
	            int next = Integer.parseInt(st.nextToken());
	            min = Math.min(next, min);
	            max = Math.max(next, max);
	        }
	        return String.format("%d %d", min, max);
	    }
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution("1 2 3 4"));
		System.out.println(solution.solution("-1 -2 -3 -4"));
		System.out.println(solution.solution("-1 -1"));
	}
}
