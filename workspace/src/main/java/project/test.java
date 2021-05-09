package project;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
	
	public static void main(String[] args) throws IOException{
		Path rawFile = Paths.get("D:\\My Folder\\Course\\CPE 593\\project\\CPE593ProjectLZMA\\raw.txt");
		Path compressedFile = Paths.get("D:\\My Folder\\Course\\CPE 593\\project\\CPE593ProjectLZMA\\compressed.lzma");

		lzmaExample lzmaCompressor = new lzmaExample(rawFile, compressedFile);
		lzmaCompressor.compress();
		lzmaCompressor.decompress();
	}
}
