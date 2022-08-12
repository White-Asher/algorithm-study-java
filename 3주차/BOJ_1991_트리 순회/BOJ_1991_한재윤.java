package BOJ.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	char data;
	Node left;
	Node right;
	
	Node(char data){
		this.data = data;
	}
}

class Tree{
	public Node root;
	
	public void initAddNode(char data, char leftData, char rightData) {
		if(root == null) {
			if(data != '.') root = new Node(data);
			if(leftData != '.') root.left = new Node(leftData);
			if(rightData != '.') root.right = new Node(rightData);
		} else {
			searchInput(root, data, leftData, rightData);
		}	
	}
	
	public void searchInput(Node root, char data, char leftData, char rightData) {
		if(root == null) {
			return;
		} else if(root.data == data) {
			if(leftData != '.') root.left = new Node(leftData);
			if(rightData != '.') root.right = new Node(rightData);
		} else {
			searchInput(root.left, data, leftData, rightData);
			searchInput(root.right, data, leftData, rightData);
		}
	}
	
	public void preOrder(Node root) {
		System.out.print(root.data);
		if(root.left != null) preOrder(root.left);
		if(root.right != null) preOrder(root.right);
	}
	
	public void inOrder(Node root) {
		if(root.left != null) inOrder(root.left);
		System.out.print(root.data);
		if(root.right != null) inOrder(root.right);
	}
	
	public void postOrder(Node root) {
		if(root.left != null) postOrder(root.left);
		if(root.right != null) postOrder(root.right);
		System.out.print(root.data);
	}
}

public class BOJ_1991 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Tree tree = new Tree();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char data = st.nextToken().charAt(0);
			char leftData = st.nextToken().charAt(0);
			char rightData = st.nextToken().charAt(0);
			tree.initAddNode(data, leftData, rightData);
		}
		
		tree.preOrder(tree.root);
		System.out.println();
		
		tree.inOrder(tree.root);
		System.out.println();
		
		tree.postOrder(tree.root);
		System.out.println();
	}
}
