package huffman;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class HzipTest {
	private InputStream inputOF;
	private InputStream inputDF;
	
	private String filename = "TestD";

	@Test
	public void testCompress() throws IOException {
		Hzip hzip = new Hzip();
		
		hzip.compress("/Users/jeffreyzant/Documents/School/HBO/Eclipse/Thema 2.3/leertaak3FileCompressie/data/" + filename + ".dat");
	}

	@Test
	public void testUncompress() throws Exception {
		String compressedFile = "/Users/jeffreyzant/Documents/School/HBO/Eclipse/Thema 2.3/leertaak3FileCompressie/data/" + filename + ".dat.huf";
		Hzip hzip = new Hzip();
		hzip.uncompress(compressedFile);
		
		
		String originalFile = "/Users/jeffreyzant/Documents/School/HBO/Eclipse/Thema 2.3/leertaak3FileCompressie/data/" + filename + ".dat";
		String decompressedFile = "/Users/jeffreyzant/Documents/School/HBO/Eclipse/Thema 2.3/leertaak3FileCompressie/data/" + filename + ".dat.uc";
		
		inputOF = new BufferedInputStream(
				new FileInputStream( originalFile ) );
		
		inputDF = new BufferedInputStream(
				new FileInputStream( decompressedFile ) );
		
		boolean same;
		int original;
		int decompressed;
		while(true) {
			original = inputOF.read();
			decompressed = inputDF.read();
			
			if (original == -1) {
				same = true;
				break;
			}
			
			if (original != decompressed) {
				same = false;
				break;
			}
		}
		
		if (!same) {
			throw new Exception();
		}
	}
}
