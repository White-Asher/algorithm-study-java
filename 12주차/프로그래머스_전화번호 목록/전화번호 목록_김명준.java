import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] p = {"119", "97674223", "1195524421"};

        System.out.println(solution(p));
    }

    public static boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < phone_book.length; i++) {
            set.add(phone_book[i]);
        }

        for (int i = 0; i < phone_book.length; i++) {
            String str = "";
            for (int j = 0; j < phone_book[i].length(); j++) {
                str += phone_book[i].charAt(j);
                if (set.contains(str) && !str.equals(phone_book[i]))
                    return false;
            }
        }
        return true;
    }
}

/*
정확성  테스트
테스트 1 〉	통과 (11.97ms, 69.9MB)
테스트 2 〉	통과 (12.49ms, 79.9MB)
테스트 3 〉	통과 (9.16ms, 77.7MB)
테스트 4 〉	통과 (9.60ms, 78.4MB)
테스트 5 〉	통과 (14.99ms, 81.2MB)
테스트 6 〉	통과 (13.98ms, 81.5MB)
테스트 7 〉	통과 (12.17ms, 82.9MB)
테스트 8 〉	통과 (11.27ms, 84.3MB)
테스트 9 〉	통과 (13.65ms, 78.5MB)
테스트 10 〉	통과 (17.32ms, 81.2MB)
테스트 11 〉	통과 (9.33ms, 74.9MB)
테스트 12 〉	통과 (9.91ms, 86.2MB)
테스트 13 〉	통과 (9.82ms, 78.4MB)
테스트 14 〉	통과 (17.43ms, 70.2MB)
테스트 15 〉	통과 (21.96ms, 78MB)
테스트 16 〉	통과 (31.61ms, 86.4MB)
테스트 17 〉	통과 (26.80ms, 85.8MB)
테스트 18 〉	통과 (31.19ms, 79.9MB)
테스트 19 〉	통과 (28.01ms, 80.7MB)
테스트 20 〉	통과 (29.66ms, 81.4MB)
효율성  테스트
테스트 1 〉	통과 (33.86ms, 57.6MB)
테스트 2 〉	통과 (18.44ms, 57.1MB)
테스트 3 〉	통과 (386.74ms, 226MB)
테스트 4 〉	통과 (274.12ms, 137MB)
*/