package project;

import java.util.*;

public class LZMA_BST {

	private String s;
	private String res = "";
	private String longestMatch = "";
	
	private BST dict = new BST();
	
	private static int minMatchSize = 2;
	private static int maxMatchSize = 6;
	private static int buffSize = 4;
	private static int dictSize = 6;
	
	//empty constructor
	public LZMA_BST(){
		
	}
	
	//constructor with input string
	public LZMA_BST(int minMatchSize, int maxMatchSize, int buffSize, int dictSize){
		LZMA_BST.minMatchSize = minMatchSize;
		LZMA_BST.maxMatchSize = maxMatchSize;
		LZMA_BST.buffSize = buffSize;
		LZMA_BST.dictSize = dictSize;
	}
	
	public void searchString(String s) {
		this.s = s;
		buildDict(s.charAt(0)+"", "", 0);
	}
	
	
	//aacaa c
	private void buildDict(String target, String next, int index) {
		
		if( !dict.contains(target) ) addToDict(target);
		
		if( next.length() >= minMatchSize ) {
			if(dict.contains(next)) longestMatch = next;
		}
		
		
		index++;
		if(index >= s.length()) {
			if( !dict.contains(next) ) addToDict(next);
			convert(target);
			convert(next);
			return;
		}
		
			
		if(next.isEmpty() || (	next.length() < target.length() 
							 && target.substring(0, next.length()).equals(next)) ) {
			
			next = next.concat(s.charAt(index)+"");
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
				index --;
				target = s.charAt(index)+"";
				next = "";
			}
			
		}
		
		buildDict(target, next, index);
	}
	
	private void addToDict(String str) {
		//System.out.println("add: "+str);
		dict.insert(str);
	}
	
	public void convert(String str) {	
		//System.out.println("convert: "+str+" : "+dict.getKey(str));
		res += dict.getKey(str) + " ";
	}
	
	public String getRes() {
		return res;
	}
	
	public void printDict() {
		System.out.println(dict.toString());
	}
	
	public String getString(int key) {
		return dict.getVal(key);
	}
	
	public static void main(String[] args) {
		
		String s = "What do you think about when I tell a word \"compression\"? If you currently study computer science, you probably think about some details of algorithms like RLE, Huffman coding or Burrows-Wheeler transform. If not, then you surely associate compression with archive file formats such as ZIP and RAR. But there is something in between - a kind of libraries that let you compress some data - implement a compression algorithm but do not provide ready file format to pack multiple files and directories into one archive. Such library is useful in gamedev for creating VFS (Virtual File System). Probably the most popular one is zlib - a free C library that implements Deflate algorithm. I've recently discovered another one - LZMA. Its SDK is also free (public domain) and the basic version is a small C library (C++, C# and Java API-s are also available, as well as some additional tools). The library uses LZMA algorithm (Lempel–Ziv–Markov chain algorithm, same as in 7z archive format), which has better compression ratio than Deflate AFAIK. So I've decided to start using it. Here is what I've learned:";
		
		
		LZMA_BST bst = new LZMA_BST();
		bst.searchString(s);
		System.out.println("res: "+bst.getRes());
		System.out.println(s);
		
		
	}
}
