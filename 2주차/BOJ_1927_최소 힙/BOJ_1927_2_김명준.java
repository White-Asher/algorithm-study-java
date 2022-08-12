import java.io.*;
import java.util.*;

/*
- 왼쪽 자식의 인덱스 = (부모의 인덱스) * 2
- 오른쪽 자식의 인덱스 = (부모의 인덱스) * 2 + 1
- 부모의 인덱스 = (자식의 인덱스) / 2
- 왼 자식 < 오른 자식
실패
 */
public class Main {
    static class minHeap {
        static int[] arr = new int[100001];
        static int idx = 0;

        static void add(int num) {
            arr[++idx] = num;
            int numIdx = idx;
            while(arr[numIdx / 2] > arr[numIdx]) {
                swap(numIdx / 2, numIdx);
                numIdx /= 2;
            }

            // 왼 자식 < 오른 자식 과정
            numIdx = idx;
            if (numIdx % 2 == 1)
                if (arr[numIdx - 1] > arr[numIdx])
                    swap(numIdx - 1, numIdx);
        }

        static int poll() {
            int num;
            if (idx > 0)
                num = arr[1];
            else
                return 0;
            swap(1, idx--);

            int numIdx = 1;
            while(true) {
                if (numIdx >= idx)
                    break;

                if (arr[numIdx] > arr[2 * numIdx]) {
                    swap(numIdx, 2 * numIdx);
                    numIdx *= 2;
                } else if (arr[numIdx] > arr[2 * numIdx + 1]) {
                    swap(numIdx, 2 * numIdx + 1);
                    numIdx = 2 * numIdx + 1;
                } else {
                    break;
                }
            }
            return num;
        }

        static void swap(int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        minHeap h = new minHeap();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                sb.append(h.poll()).append("\n");
            }
            else
                h.add(num);
        }
        System.out.print(sb);
    }
}