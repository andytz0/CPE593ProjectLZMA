package project;

import java.io.*;
import java.nio.file.*;

import org.tukaani.xz.*;

enum Mode{
	Fast(LZMA2Options.MODE_FAST),
	Normal(LZMA2Options.MODE_NORMAL),
	Default(LZMA2Options.PRESET_DEFAULT);
	
	public int m;
	private Mode(int m) {
		this.m = m;
	}
}

public class LZMACompressor {

	private Path in;
	private Path out;
	
	//Constructor
	public LZMACompressor(Path in, Path out){
		this.in = in;
		this.out = out;
	}
	
	public void compress(Mode mode) throws IOException{
		
		try(XZOutputStream xzOut = new XZOutputStream(new FileOutputStream(out.toFile()), new LZMA2Options(mode.m))){
			Files.copy(in, xzOut);
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		Path rawFile = Paths.get("D:\\My Folder\\Course\\CPE 593\\project\\CPE593ProjectLZMA\\raw.txt");
		Path compressedFile = Paths.get("D:\\My Folder\\Course\\CPE 593\\project\\CPE593ProjectLZMA\\compressed_2.lzma");

		LZMACompressor lzmaCompressor = new LZMACompressor(rawFile, compressedFile);
		lzmaCompressor.compress(Mode.Default);
	}
	
}
