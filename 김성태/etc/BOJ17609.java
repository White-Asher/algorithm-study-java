package etc;

import java.util.*;
import java.lang.*;
public class BOJ17609
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int T = 1; T <= TC; T++) {
			String s = sc.next();
			int N = s.length(), pv = -1;
			for (int i = 0; i < N; ++i)
			{
				if (s.charAt(i) != s.charAt(N-i-1))
				{
					pv = i;
					break;
				}
			}
			if (pv == -1) {
				System.out.println("0");
				continue;
			}
			char[] arr1 = new char[N - 1];
			char[] arr2 = new char[N - 1];
			int p1 = 0, p2 = 0, ck1 = 0, ck2 = 0;
			for (int i = 0; i < N; i++) {
				if (i != pv)arr1[p1++] = s.charAt(i);
				if (i != N-pv-1)arr2[p2++] = s.charAt(i);
			}
			for (int i = 0; i < N - 1; i++) {
				if (arr1[i] != arr1[N - 2 - i])ck1 = 1;
				if (arr2[i] != arr2[N - 2 - i])ck2 = 1;
			}
			if (ck1==1 && ck2==1) {
				System.out.println("2");
			}
			else {
				System.out.println("1");
			}
		}
	}
}