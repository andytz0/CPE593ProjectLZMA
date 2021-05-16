package project;

/*
 * This file uses xz library for java
 * https://tukaani.org/xz/xz-javadoc/org/tukaani/xz/package-summary.html
 */

import java.io.*;
import java.nio.file.*;
import org.tukaani.xz.*;

//Set 3 mode enum values for compression
enum Mode{
	
	Fast(LZMA2Options.MODE_FAST),
	Normal(LZMA2Options.MODE_NORMAL),
	Default(LZMA2Options.PRESET_DEFAULT);
	
	public int m;
	
	Mode(int m)
	{
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
	
	//Use lZMA and fileOutputStream to get compressed content
	public void lzmaCompress(Mode mode) throws Exception
	{
		out = Paths.get(out+".lzma");
		try(LZMAOutputStream lzmaOut = new LZMAOutputStream(new FileOutputStream(out.toFile()), 
															new LZMA2Options(mode.m), 
															in.toFile().length())){
			Files.copy(in, lzmaOut);
		}
	}
	
	//Use lZMA and fileInputStream to Decompress
	public void lzmaDecompress() throws Exception
	{
		try(LZMAInputStream lzmaIn = new LZMAInputStream(new FileInputStream(in.toFile()), 100000)){
			Files.copy(lzmaIn, out);
		}
	}
	
	//Use XZ and fileOutputStream to get compressed content
	public void xzCompress(Mode mode) throws Exception
	{
		out = Paths.get(out+".xz");
		try(XZOutputStream xzOut = new XZOutputStream(	new FileOutputStream(out.toFile()), 
														new LZMA2Options(mode.m))){
			Files.copy(in, xzOut);
		}
	}
	
	//Use XZ and fileInputStream to Decompress
	public void xzDecompress() throws Exception
	{	
		try(XZInputStream xzIn = new XZInputStream(	new FileInputStream(in.toFile()), 100000)){
			Files.copy(xzIn, out);
		}
	}
	
}
