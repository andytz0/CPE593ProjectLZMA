package project;

public class LZMA_BST {

	private String s;
	private String res = "";
	private String longestMatch = "";
	
	private BST dict = new BST();
	
	private static int minMatchSize = 2;
	private static int dictSize = 6;
	
	//empty constructor
	public LZMA_BST(){
		
	}
	
	//constructor with input string
	public LZMA_BST(int minMatchSize, int dictSize){
		LZMA_BST.minMatchSize = minMatchSize;
		LZMA_BST.dictSize = dictSize;
	}
	
	//give a input string and convert it.
	public void convertString(String s) {
		this.s = s;
		buildDict(s.charAt(0)+"", "", 0);
	}
	
	
	/*
	 * Recursively check input string values, starting from first char
	 * if a new string value(target) is found, add it to dictionary
	 * target size is limited by dictSize
	 * if the following string has same content in dictionary, convert it.
	 * next string has a minMatchSize limit
	 */
	private void buildDict(String target, String next, int index) {
		
		if( !dict.contains(target) ) addToDict(target);
		
		if( next.length() >= minMatchSize ) {
			if(dict.contains(next)) longestMatch = next;
		}
		
		
		index++;
		//if index increment to the length of input string s
		//convert the last segment of string and exit function.
		if(index >= s.length()) {
			if( dict.contains(target+next) ) {
				convert(target+next);
				return;
			}
			if( !dict.contains(next) ) addToDict(next);
			convert(target);
			convert(next);
			return;
		}
		
			
		//next get new char from input string s when
		//next is empty or next is identical to the first few chars of target.
		if(next.isEmpty() || (	next.length() < target.length() 
							 && target.substring(0, next.length()).equals(next)) ) {
			
			next = next.concat(s.charAt(index)+"");
			
		//update target until dictSize, then update next
		}else {
			if(target.length() < dictSize) {
				target = target.concat(next.charAt(0)+"");
				
				if(next.length() == 1) {
					next = s.charAt(index)+"";
				}else {
					index -= next.length() - 1;
					next = next.charAt(1) + "";
				}
				
			//if target size is larger than the limited dictSize, reset target
			}else {
				convert(target);
				if(next.equals(target)) {
					convert(next);
				}else {
					convert(next.substring(0, next.length()-1));
					index --;
				}
				
				target = s.charAt(index)+"";
				next = "";
			}
			
		}
		
		//System.out.println(target +" : " + next+" : "+index+" "+s.charAt(index));
		buildDict(target, next, index);
	}
	
	//insert a new node to BST
	private void addToDict(String str) {
		dict.insert(str);
	}
	
	//get the key corresponding to the value in BST, here key is a integer, similar to encode/encrypt
	public void convert(String str) {	
		res += dict.getKey(str) + " ";
	}
	
	//get the String result of conversion
	public String getRes() {
		return res;
	}
	
	//get String result of dict (BST inorder, not in key order)
	public void printDict() {
		//dict.print2D();
		System.out.println(dict.size()+" dict: "+dict.toString());
	}
	
	//get String value by given key
	public String getString(int key) {
		return dict.getVal(key);
	}
	
	public String getLongestMatch() {
		return longestMatch;
	}
	
	
	//For test
	/*
	public static void main(String[] args) {
		
		String s = "What do you think about when I tell a word \"compression\"?";
		
		LZMA_BST bst = new LZMA_BST();
		bst.convertString(s);
		
		bst.printDict();
		System.out.println("res: "+bst.getRes());
		//System.out.println(bst.getString(6) +" "+ bst.getString(5)+" "+ bst.getString(12)+" "+bst.getString(13));
		
		
	}
	*/
}
