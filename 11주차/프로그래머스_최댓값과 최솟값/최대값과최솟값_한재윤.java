package programers.ProL2_MaxValandMinVal;
/* 프로그래머스
 * L2
 * 최댓값과 최솟값
 */
public class Programmers_최대값과최솟값 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution("1 2 3 4"));
	}
	
}

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] splitStr = s.split(" ");
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for(int i = 0; i< splitStr.length; i++){
            maxVal = Math.max(maxVal, Integer.parseInt(splitStr[i]));
            minVal = Math.min(minVal, Integer.parseInt(splitStr[i]));
        }
        sb.append(minVal).append(" ").append(maxVal);
        return sb.toString();
    }
}