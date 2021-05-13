package project;

import lzma.sdk.lzma.Decoder;
import lzma.streams.LzmaInputStream;
import lzma.streams.LzmaOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Path;


//Example from StackOverflow for comparison purpose
//https://stackoverflow.com/questions/5481487/how-to-use-lzma-sdk-to-compress-decompress-in-java/49454898#49454898
//
public class lzmaExample
{
    private Path rawFilePath;
    private Path compressedFilePath;

    public lzmaExample(Path rawFilePath, Path compressedFilePath)
    {
        this.rawFilePath = rawFilePath;
        this.compressedFilePath = compressedFilePath;
    }

    public void compress() throws IOException
    {
        try (LzmaOutputStream outputStream = new LzmaOutputStream.Builder(
                new BufferedOutputStream(new FileOutputStream(compressedFilePath.toFile())))
                .useMaximalDictionarySize()
                .useMaximalFastBytes()
                .build();
             InputStream inputStream = new BufferedInputStream(new FileInputStream(rawFilePath.toFile())))
        {
            IOUtils.copy(inputStream, outputStream);
        }
    }

    public void decompress() throws IOException
    {
        try (LzmaInputStream inputStream = new LzmaInputStream(
                new BufferedInputStream(new FileInputStream(compressedFilePath.toFile())),
                new Decoder());
             OutputStream outputStream = new BufferedOutputStream(
                     new FileOutputStream(rawFilePath.toFile())))
        {
            IOUtils.copy(inputStream, outputStream);
        }
    }
}
