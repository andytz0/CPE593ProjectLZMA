package project;

import java.util.*;

public class BST {
	
	class Node {
		
		int key;
		String val;
		
		Node left, right;
		
		public Node(int key, String val){
			this.key = key;
			this.val = val;
			left = null;
			right = null;
		}
		
	}
	
	HashMap<Integer, String> map = new HashMap<Integer, String>();
	Node root;
	private int keyNum = 0;
	private String s = "";
	
	//constructor
	public BST(){
		root = null;
	}
	
	public String getVal(int key) {
		return map.get(key);
	}
	
	public int getKey(String val) {
		
		if(find(root, val) == null) return 0;
		return find(root, val).key;
	}
	
	public boolean contains(String val) {
		return find(root, val) != null;
	}
	
	private Node find(Node n, String val) {
		
		if(n == null) return null;
		if(val.equals(n.val)) return n;
		
		if(strCompare(val, n.val) == -1) n = find(n.left, val);
		else if(strCompare(val, n.val) == 1) n = find(n.right, val);
		
		return n;
	}
	
	public void insert(String val) {	
		root = insertRec(root, val);
	}
	
	private Node insertRec(Node n, String val) {
		
		if(n == null) {
			n = new Node(++keyNum, val);
			map.put(n.key, n.val);
			return n;
		}
		
		if(strCompare(val, n.val) <= 0) n.left = insertRec(n.left, val);
		else n.right = insertRec(n.right, val);
		
		return n;
	}
	
	private int strCompare(String a, String b) {
		
		if(a.equals(b)) return 0;
		
		for(int i=0; i < Math.min(a.length(), b.length()); i++) {
			if(a.charAt(i) < b.charAt(i)) {
				return -1;
			}
			if(a.charAt(i) > b.charAt(i)) {
				return 1;
			}
		}
		
		if(a.length() < b.length()) return -1;
		if(a.length() > b.length()) return 1;
		else return 0;
	}
	
	private void inorder(Node n) {
		
		if(n != null) {
			inorder(n.left);
			s += n.val + " ";
			inorder(n.right);
		}
	}
	
	public String toString() {
		inorder(root);
		return s;
	}
	
}
