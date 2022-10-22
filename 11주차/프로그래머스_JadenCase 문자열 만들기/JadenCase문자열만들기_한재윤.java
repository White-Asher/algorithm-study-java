package programers.ProL2_JadenCase;

public class Programmers_JadenCase문자열만들기 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution("3people unFollowed me"));
	}
}

class Solution {
    public String solution(String s) {
    	StringBuilder sb = new StringBuilder();
    	s = s.toLowerCase();
    	sb.append(Character.toUpperCase(s.charAt(0)));
    	
        for (int i = 1; i < s.length(); i++) {
        	if(s.charAt(i) == ' ') {
        		sb.append(' ');
        	} else if(s.charAt(i-1) == ' ') {
        		sb.append(Character.toUpperCase(s.charAt(i)));
        	} else {
        		sb.append(s.charAt(i));
        	}
		}
        
        return sb.toString();
    }
}