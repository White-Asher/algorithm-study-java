import java.util.Scanner;

public class BOJ_1074_Z {
	static int N,i,j,cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		i = sc.nextInt();
		j = sc.nextInt();
		
		sol((int) Math.pow(2, N),0,0);
	}

	private static void sol(int n, int k, int l) {
		if(k == i && l == j) {
			System.out.print(cnt);
			//System.exit(0);
			return;
		}
		if(k<=i && i<(k+n) && l<=j&&j<(l+n)) {
			int nn = n/2;
			sol(nn,k,l);
			sol(nn,k,l+nn);
			sol(nn,k+nn,l);
			sol(nn,k+nn,l+nn);
			
		}
		else
			cnt += n*n;
		
	}
	

}
