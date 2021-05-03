package project;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
	
	public static void main(String[] args) throws IOException{
		Path rawFile = Paths.get("raw.txt");
		Path compressedFile = Paths.get("compressed.lzma");

		LzmaCompressor lzmaCompressor = new LzmaCompressor(rawFile, compressedFile);
		lzmaCompressor.compress();
		
	}
}
