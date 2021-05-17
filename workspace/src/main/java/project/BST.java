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
	
	//use hashmap to get val and size of node.
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	private Node root;
	private int keyNum = 0;
	//the size of the left and right side of tree.
	private int lh = 0;
	private int rh = 0;
	private String s = "";
	
	//constructor
	public BST(){
		root = null;
	}
	
	public String getVal(int key) {
		return map.get(key);
	}
	
	public int getKey(String val) {
		//if val doesn't exist in the tree, return 0
		if(find(root, val) == null) return 0;
		return find(root, val).key;
	}
	
	public int size() {
		return map.size();
	}
	
	public boolean contains(String val) {
		return find(root, val) != null;
	}
	
	//find the node matches val, and return that node
	//start from root and recursively call the function.
	private Node find(Node n, String val) {
		
		if(n == null) return null;
		if(val.equals(n.val)) return n;
		
		if(strCompare(val, n.val) == -1) n = find(n.left, val);
		else if(strCompare(val, n.val) == 1) n = find(n.right, val);
		
		return n;
	}
	
	//insert a new node into tree.
	public void insert(String val) {	
		root = insertRec(root, val, 0, 0);
	}
	
	/* 
	 * recursively call the function.
	 * When a new node is added, update the left/right size of the tree and hashmap.
	 * call rotate when tree is not balanced.
	 */
	private Node insertRec(Node n, String val, int l, int r) {
		
		if(n == null) {
			//System.out.println(val);
			if(root != null) {
				if(strCompare(val, root.val) <= 0) lh++;
				else rh++;
			}
			n = new Node(++keyNum, val);
			map.put(n.key, n.val);
			return n;
		}
		
		//System.out.print(n.val);
		
		//compare the val with curr node
		if(strCompare(val, n.val) <= 0) {
			n.left = insertRec(n.left, val, ++l, r);
			//System.out.println(n.val+" l: "+l + " lh: "+ lh);
			if(l == 1 && !isBalance()) {
				n = rotate(n, true);
				l = 0;
			}
		}else {
			n.right = insertRec(n.right, val, l, ++r);
			//System.out.println(n.val+" r: "+r + " rh: "+ rh);
			if(r == 1 && !isBalance()) {
				n = rotate(n, false);
				r = 0;
			}
		}
		
		return n;
	}
	
	//rotate is called when tree is not balanced
	//rotate right if dir==true, rotate left if dir==false.
	private Node rotate(Node A, boolean dir) {
		Node B;
		if(dir) {
			B = A.left;
			A.left = B.right;
			B.right = A;
			if(lh > 0) lh--;
			rh++;
		}else {
			B = A.right;
			A.right = B.left;
			B.left = A;
			lh++;
			if(rh > 0) rh--;
		}	
		//System.out.println(A.val+":"+B.val+" lh: "+lh+" rh: "+rh+" rotate dir:"+ dir);
		return B;
	}
	
	//check the level of left/right side by using log function.
	private boolean isBalance(){
		return (int)Math.log(lh+1)/Math.log(2) == (int)Math.log(rh+1)/Math.log(2);
	}
	
	//compare string value.
	private int strCompare(String a, String b) {
		
		if(a.equals(b)) return 0;
		
		for(int i=0; i < Math.min(a.length(), b.length()); i++) {
			if(a.charAt(i) < b.charAt(i)) return -1;
			if(a.charAt(i) > b.charAt(i)) return 1;
		}
		
		if(a.length() < b.length()) return -1;
		if(a.length() > b.length()) return 1;
		else return 0;
	}
	
	//get node val inorder
	private void inorder(Node n) {
		if(n != null) {
			inorder(n.left);
			s += n.val + " ";
			inorder(n.right);
		}
	}
	
	public String toString() {
		s = "";
		inorder(root);
		return s;
	}
	
/* Credits: GeeksforGeeks.org, 
 * https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
 * Use to print out the Tree.
 */
	private void print2DUtil(Node n, int space) 
	{ 
	    // Base case 
	    if (n == null) 
	        return; 
	  
	    // Increase distance between levels 
	    space += 6; 
	  
	    // Process right child first 
	    print2DUtil(n.right, space); 
	  
	    // Print current node after space 
	    // count 
	    System.out.print("\n"); 
	    for (int i = 6; i < space; i++) 
	        System.out.print(" "); 
	    System.out.print(n.val + "\n"); 
	  
	    // Process left child 
	    print2DUtil(n.left, space); 
	} 
	
	public void print2D() { 
	    // Pass initial space count as 0 
	    print2DUtil(root, 0); 
	} 
	
}
