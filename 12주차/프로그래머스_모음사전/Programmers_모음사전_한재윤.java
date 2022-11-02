package programers.ProL2_aDictionaryofVowels;

public class Programmers_모음사전 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution("AAAAE"));
		System.out.println(solution.solution("AAAE"));
		System.out.println(solution.solution("I"));
		System.out.println(solution.solution("EIO"));
	}
}

class Solution {
	public int solution(String word) {
        int answer = word.length();
        char[] ch = {'A', 'E', 'I', 'O', 'U'};
        int []num = {781, 156, 31, 6, 1};
        for(int i = 0; i < word.length(); i++){
            for(int j = 0; j < 5; j++){
                if(word.charAt(i) == ch[j])
                    answer += num[i] * j;                            
            }
        }
        return answer;
    }
}

// n[i] = (n[i+1] * 5) + 1
/*
1. 5번째 자리의 알파벳이 6번을 진행하여 4번째 자리의 알파벳을 바꿈
2. 4번째 자리의 알파벳이 3번째 자리의 알파벳을 바꾸기 위해서는 (1번과정 * 5) + 1 번 진행
3. 3번째 자리의 알파벳이 2번째 자리의 알파벳을 바꾸기 위해서는 (2번과정 * 5) + 1 번 진행
*/

/*
채점을 시작합니다.
정확성  테스트
테스트 1 〉	통과 (0.19ms, 65.7MB)
테스트 2 〉	통과 (0.24ms, 71.9MB)
테스트 3 〉	통과 (0.16ms, 70.4MB)
테스트 4 〉	통과 (0.19ms, 76.2MB)
테스트 5 〉	통과 (0.16ms, 69.7MB)
테스트 6 〉	통과 (0.15ms, 73.4MB)
테스트 7 〉	통과 (0.14ms, 76.4MB)
테스트 8 〉	통과 (0.20ms, 76.1MB)
테스트 9 〉	통과 (0.16ms, 76.1MB)
테스트 10 〉	통과 (0.16ms, 70.5MB)
테스트 11 〉	통과 (0.21ms, 79.4MB)
테스트 12 〉	통과 (0.15ms, 75.7MB)
테스트 13 〉	통과 (0.16ms, 74.9MB)
테스트 14 〉	통과 (0.25ms, 76.9MB)
테스트 15 〉	통과 (0.24ms, 75.3MB)
테스트 16 〉	통과 (0.18ms, 74MB)
테스트 17 〉	통과 (0.16ms, 75.1MB)
테스트 18 〉	통과 (0.22ms, 85.3MB)
테스트 19 〉	통과 (0.22ms, 83.1MB)
테스트 20 〉	통과 (0.14ms, 78MB)
테스트 21 〉	통과 (0.20ms, 67.3MB)
테스트 22 〉	통과 (0.16ms, 75.7MB)
테스트 23 〉	통과 (0.15ms, 71.5MB)
테스트 24 〉	통과 (0.21ms, 76.1MB)
테스트 25 〉	통과 (0.15ms, 74.9MB)
테스트 26 〉	통과 (0.15ms, 73.1MB)
테스트 27 〉	통과 (0.16ms, 73.9MB)
테스트 28 〉	통과 (0.21ms, 78.3MB)
테스트 29 〉	통과 (0.14ms, 78.2MB)
테스트 30 〉	통과 (0.17ms, 65.7MB)
테스트 31 〉	통과 (0.22ms, 79MB)
테스트 32 〉	통과 (0.17ms, 72.6MB)
테스트 33 〉	통과 (0.21ms, 77.6MB)
테스트 34 〉	통과 (0.15ms, 79.7MB)
테스트 35 〉	통과 (0.15ms, 74.9MB)
테스트 36 〉	통과 (0.21ms, 71.6MB)
테스트 37 〉	통과 (0.24ms, 75.6MB)
테스트 38 〉	통과 (0.16ms, 77.4MB)
테스트 39 〉	통과 (0.20ms, 81.6MB)
테스트 40 〉	통과 (0.15ms, 72.9MB)
채점 결과
정확성: 100.0
합계: 100.0 / 100.0
 */
