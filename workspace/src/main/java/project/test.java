package project;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class test {
	
	private static String p = "D:\\My Folder\\Course\\CPE 593\\project\\CPE593ProjectLZMA\\demo\\";
	
	public static void main(String[] args) throws Exception{
		
/*
 * Test for LZMACompressor.java.
		
		Path rawFile = Paths.get(p + "new Real-Time Lossless Compression.txt");
		Path compressedFile = Paths.get(p + "compressed");

//		LZMACompressor lzmaCompressor = new LZMACompressor(rawFile, compressedFile);
//		lzmaCompressor.lzmaCompress(Mode.Default);
		
//		LZMACompressor lz = new LZMACompressor(Paths.get(compressedFile+".lzma"), rawFile);
//		lz.lzmaDecompress();
		
//		LZMACompressor xzCompressor = new LZMACompressor(rawFile, compressedFile);
//		xzCompressor.xzCompress(Mode.Default);
		
//		LZMACompressor xz = new LZMACompressor(Paths.get(compressedFile+".xz"), rawFile);
//		xz.xzDecompress();
 * 
 */
		
		
		
/*
 * Test for LZMA_BST.java
 */
		FileReader fr = new FileReader(p + "raw.txt");
		FileWriter fw = new FileWriter(p + "converted.txt");
		
		String s = "";
		int i;
		
		while( (i = fr.read()) != -1) {
			s += (char)i;
		}
		System.out.println(s);
		LZMA_BST bst = new LZMA_BST();
		bst.convertString(s);
		bst.printDict();
		
		fw.write(bst.getRes());
		
		fr.close();
		fw.close();
		
		

		
/*
 * Test for BST
		
		
		BST bst = new BST();
		
		bst.insert("a");
		bst.insert("b");
		bst.insert("c");
		bst.insert("d");
		bst.insert("e");
		
		System.out.println(bst.toString());
		bst.print2D();
		
 */		
		
		
	}
}
