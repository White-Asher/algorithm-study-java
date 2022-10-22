package com.joykst96.practice.programmers;

import com.joykst96.practice.programmers.PGS_12951_JadenCase_문자열_만들기.Solution;

public class PGS_12951_JadenCase_문자열_만들기 {
	static class Solution {
	    public String solution(String s) {
	    	char[] cArr = s.toCharArray();
	    	boolean flag = true;
	    	for (int i = 0; i < cArr.length; ++i) {
	    		if (cArr[i] == ' ') {
	    			flag = true;
	    		} else if (flag) {
	    			cArr[i] = 'a' <= cArr[i] && cArr[i] <= 'z' ? (char)(cArr[i] - ('a' - 'A')) : cArr[i];
	    			flag = false;
	    		} else {
	    			cArr[i] = 'A' <= cArr[i] && cArr[i] <= 'Z' ? (char)(cArr[i] + ('a' - 'A')) : cArr[i];
	    		}
	    	}
	    	return String.valueOf(cArr);
	    }
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution("3people unFollowed me"));
		System.out.println(solution.solution("for the last week"));
		System.out.println(solution.solution(" "));
		System.out.println(solution.solution(" aa"));
		System.out.println(solution.solution(" a     a bb c   d"));
	}
}
