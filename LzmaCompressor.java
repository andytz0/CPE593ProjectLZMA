package project;

import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Path;

public class LzmaCompressor
{
    private Path rawFilePath;
    private Path compressedFilePath;

    public LzmaCompressor(Path rawFilePath, Path compressedFilePath)
    {
        this.rawFilePath = rawFilePath;
        this.compressedFilePath = compressedFilePath;
    }

    public void compress() throws IOException
    {
        try (LZMACompressorOutputStream outputStream = new LZMACompressorOutputStream(
        		new BufferedOutputStream(new FileOutputStream(compressedFilePath.toFile())));
             InputStream inputStream = new BufferedInputStream(new FileInputStream(rawFilePath.toFile())))
        {
            IOUtils.copy(inputStream, outputStream);
        }
    }

}
