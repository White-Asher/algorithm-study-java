package programers.ProL2_ListOfPhoneNumbers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_전화번호목록 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution(new String[] { "119", "97674223", "1195524421" }));
		System.out.println(solution.solution(new String[] {"123", "456", "789"}));
		System.out.println(solution.solution(new String[] { "12", "123", "1235", "567", "88" }));
	}
}

class Solution {
	public boolean solution(String[] phone_book) {
		Map<String, String> map = new HashMap<>();

		// 전화번호를 key value에 넣기
		for (int i = 0; i < phone_book.length; i++) {
			map.put(phone_book[i], phone_book[i]);
		}

		// substring으로 문자 하나씩 떼면서 키에 동일한 값이 있는지 확인하기
		for (int i = 0; i < phone_book.length; i++) {
			for (int j = 1; j < phone_book[i].length(); j++) {
				String subString = phone_book[i].substring(0, j);

				if (map.containsKey(subString)) {
					return false;
				}
			}
		}
		return true;
	}
}

/*
정확성  테스트
테스트 1 〉	통과 (0.04ms, 73.4MB)
테스트 2 〉	통과 (0.03ms, 76.3MB)
테스트 3 〉	통과 (0.02ms, 75MB)
테스트 4 〉	통과 (0.04ms, 73.6MB)
테스트 5 〉	통과 (0.05ms, 75.2MB)
테스트 6 〉	통과 (0.02ms, 72.4MB)
테스트 7 〉	통과 (0.03ms, 75.3MB)
테스트 8 〉	통과 (0.03ms, 72MB)
테스트 9 〉	통과 (0.03ms, 74.8MB)
테스트 10 〉	통과 (0.04ms, 77.8MB)
테스트 11 〉	통과 (0.03ms, 76.3MB)
테스트 12 〉	통과 (0.02ms, 74.5MB)
테스트 13 〉	통과 (0.04ms, 77MB)
테스트 14 〉	통과 (2.78ms, 81.6MB)
테스트 15 〉	통과 (2.90ms, 82.3MB)
테스트 16 〉	통과 (4.03ms, 79.7MB)
테스트 17 〉	통과 (6.59ms, 81.2MB)
테스트 18 〉	통과 (9.41ms, 87MB)
테스트 19 〉	통과 (2.74ms, 77MB)
테스트 20 〉	통과 (4.21ms, 78MB)
효율성  테스트
테스트 1 〉	통과 (3.10ms, 75.3MB)
테스트 2 〉	통과 (3.92ms, 57.6MB)
테스트 3 〉	통과 (291.80ms, 194MB)
테스트 4 〉	통과 (186.49ms, 133MB)
*/
