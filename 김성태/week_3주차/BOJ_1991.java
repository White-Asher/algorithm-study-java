package week_3주차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	char data;
	Node left;
	Node right;
	
	Node (char data){
		this.data = data;
	}
}

public class BOJ_1991 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/BOJ_1991.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Node[] tree = new Node[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new Node((char)('A' + i));
		}
		Node root = tree[0];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int target = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0) - 'A';
			int right = st.nextToken().charAt(0) - 'A';
			if (left >= 0) tree[target].left = tree[left];
			if (right >= 0) tree[target].right = tree[right];
		}
		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);
		sb.append("\n");
		System.out.println(sb);
	}
	
	static void preOrder(Node root) {
		if (root == null) return;
		sb.append(root.data);
		preOrder(root.left);
		preOrder(root.right);
	}
	
	static void inOrder(Node root) {
		if (root == null) return;
		inOrder(root.left);
		sb.append(root.data);
		inOrder(root.right);
	}
	
	static void postOrder(Node root) {
		if (root == null) return;
		postOrder(root.left);
		postOrder(root.right);
		sb.append(root.data);
	}
}
