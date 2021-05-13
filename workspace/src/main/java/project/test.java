package project;

import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
	
	private static String p = "D:\\My Folder\\Course\\CPE 593\\project\\CPE593ProjectLZMA\\demo\\";
	
	public static void main(String[] args) throws Exception{
		
		Path rawFile = Paths.get(p + "new Real-Time Lossless Compression.txt");
		Path compressedFile = Paths.get(p + "compressed");

//		LZMACompressor lzmaCompressor = new LZMACompressor(rawFile, compressedFile);
//		lzmaCompressor.lzmaCompress(Mode.Default);
		
		LZMACompressor lz = new LZMACompressor(Paths.get(compressedFile+".lzma"), rawFile);
		lz.lzmaDecompress();
		
//		LZMACompressor xzCompressor = new LZMACompressor(rawFile, compressedFile);
//		xzCompressor.xzCompress(Mode.Default);
		
//		LZMACompressor xz = new LZMACompressor(Paths.get(compressedFile+".xz"), rawFile);
//		xz.xzDecompress();
		
	}
}
