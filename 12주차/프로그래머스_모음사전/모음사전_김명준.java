import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String word = "AAAE";

        System.out.println(solution(word));

    }

    /*
    1 * 5 + 1 = 6
    6 * 5 + 1 = 31
    31 * 5 + 1 = 156
    156 * 5 + 1 = 781
     */

    public static int solution(String word) {
        int answer = word.length();
        int[] nums = {781, 156, 31, 6, 1};
        Map<Character, Integer> m = new HashMap<>();
        m.put('A', 0);
        m.put('E', 1);
        m.put('I', 2);
        m.put('O', 3);
        m.put('U', 4);

        for (int i = 0; i < word.length(); i++) {
            answer += m.get(word.charAt(i)) * nums[i];
        }

        return answer;
    }
}

/*
테스트 1 〉	통과 (0.14ms, 78MB)
테스트 2 〉	통과 (0.13ms, 79.1MB)
테스트 3 〉	통과 (0.22ms, 77.6MB)
테스트 4 〉	통과 (0.10ms, 67.2MB)
테스트 5 〉	통과 (0.16ms, 70MB)
테스트 6 〉	통과 (0.15ms, 73.9MB)
테스트 7 〉	통과 (0.12ms, 74.6MB)
테스트 8 〉	통과 (0.16ms, 73.2MB)
테스트 9 〉	통과 (0.14ms, 73.8MB)
테스트 10 〉	통과 (0.19ms, 74.8MB)
테스트 11 〉	통과 (0.14ms, 78.3MB)
테스트 12 〉	통과 (0.12ms, 73.5MB)
테스트 13 〉	통과 (0.13ms, 90.1MB)
테스트 14 〉	통과 (0.14ms, 70.8MB)
테스트 15 〉	통과 (0.16ms, 75.5MB)
테스트 16 〉	통과 (0.68ms, 74.3MB)
테스트 17 〉	통과 (0.19ms, 75.1MB)
테스트 18 〉	통과 (0.11ms, 76.2MB)
테스트 19 〉	통과 (0.15ms, 66.3MB)
테스트 20 〉	통과 (0.16ms, 74MB)
테스트 21 〉	통과 (0.12ms, 77.3MB)
테스트 22 〉	통과 (0.13ms, 71.9MB)
테스트 23 〉	통과 (0.14ms, 77.1MB)
테스트 24 〉	통과 (0.12ms, 71.7MB)
테스트 25 〉	통과 (0.15ms, 76MB)
테스트 26 〉	통과 (0.15ms, 71.3MB)
테스트 27 〉	통과 (0.15ms, 78.6MB)
테스트 28 〉	통과 (0.18ms, 76.2MB)
테스트 29 〉	통과 (0.13ms, 65.3MB)
테스트 30 〉	통과 (0.11ms, 74.6MB)
테스트 31 〉	통과 (0.11ms, 75.9MB)
테스트 32 〉	통과 (0.14ms, 81.7MB)
테스트 33 〉	통과 (0.16ms, 80.6MB)
테스트 34 〉	통과 (0.10ms, 77.2MB)
테스트 35 〉	통과 (0.12ms, 80.9MB)
테스트 36 〉	통과 (0.15ms, 75.5MB)
테스트 37 〉	통과 (0.10ms, 69.2MB)
테스트 38 〉	통과 (0.19ms, 76.7MB)
테스트 39 〉	통과 (0.15ms, 75.8MB)
테스트 40 〉	통과 (0.11ms, 72.7MB)
 */