package huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Support {
	// These are the 3 files we will use for testing. The "enc" and "dec" files will be created by the
	// program. We just need to define them here so our test knows what they are called.
	String testFilePlain = "/Users/jeffreyzant/Documents/School/HBO/Eclipse/Thema 2.3/leertaak3FileCompressie/testHuffman.txt";
	String testFileEnc = "/Users/jeffreyzant/Documents/School/HBO/Eclipse/Thema 2.3/leertaak3FileCompressie/testHuffman.txt.huf";
	String testFileDec = "/Users/jeffreyzant/Documents/School/HBO/Eclipse/Thema 2.3/leertaak3FileCompressie/testHuffman.txt.uc";
	
	
	/* Below are our 2 testing texts. All these texts are int arrays rather than just strings to make it
	 * easy to compare them to the results. The results have to be int arrays (*) (or possibly bytes), because
	 * the result can contain *any* binary combination of 8 bits. If we get for example the value 0x8F, and 
	 * we try to put that in a string, or in a char, the system will try to obtain the character that goes 
	 * with that, and in the ANSI set, a few are "undefined", thus causing java to put a question mark or 
	 * similar character there. (And thus breaking our program, since we care for the value, not for the 
	 * character it represents.)
	 * (*): we can use ints because leading zero's are ignored by the writer.
	 * Note: All these int arrays can be easily obtained by using a hexeditor. Some even have an option to
	 * copy/export text as a Java array. Or you could just write a program to do it for you, it should be
	 * doable in roughly 10 lines if you note how "readFile" works and just add a print statement to it at
	 * the right place. So don't be intimidated by these large arrays.
	 * Keep in mind though, that to write a class like this on any "normal" project would require you to 
	 * do quite a bit of manual calculations, so keep it simple. (Having something like this ready before
	 * you start coding, can be a really great help.
	 */
	
	/* 
	 * text: (C)2010 Omnia -- This line is not part of the text
	 * 
	 * Storm winds blow on the golden barley
	 * Birds lie dead on the fields of Athenry
	 * I'll stand strong 'cause you stand beside me
	 * Take my hand and we'll dance until we die!
	 */
	int[] text1 = {
			0x53, 0x74, 0x6F, 0x72, 0x6D, 0x20, 0x77, 0x69, 0x6E, 0x64, 0x73, 0x20, 0x62, 0x6C, 0x6F, 0x77,
			0x20, 0x6F, 0x6E, 0x20, 0x74, 0x68, 0x65, 0x20, 0x67, 0x6F, 0x6C, 0x64, 0x65, 0x6E, 0x20, 0x62,
			0x61, 0x72, 0x6C, 0x65, 0x79, 0x0D, 0x0A, 0x42, 0x69, 0x72, 0x64, 0x73, 0x20, 0x6C, 0x69, 0x65,
			0x20, 0x64, 0x65, 0x61, 0x64, 0x20, 0x6F, 0x6E, 0x20, 0x74, 0x68, 0x65, 0x20, 0x66, 0x69, 0x65,
			0x6C, 0x64, 0x73, 0x20, 0x6F, 0x66, 0x20, 0x41, 0x74, 0x68, 0x65, 0x6E, 0x72, 0x79, 0x0D, 0x0A,
			0x49, 0x27, 0x6C, 0x6C, 0x20, 0x73, 0x74, 0x61, 0x6E, 0x64, 0x20, 0x73, 0x74, 0x72, 0x6F, 0x6E,
			0x67, 0x20, 0x27, 0x63, 0x61, 0x75, 0x73, 0x65, 0x20, 0x79, 0x6F, 0x75, 0x20, 0x73, 0x74, 0x61,
			0x6E, 0x64, 0x20, 0x62, 0x65, 0x73, 0x69, 0x64, 0x65, 0x20, 0x6D, 0x65, 0x0D, 0x0A, 0x54, 0x61,
			0x6B, 0x65, 0x20, 0x6D, 0x79, 0x20, 0x68, 0x61, 0x6E, 0x64, 0x20, 0x61, 0x6E, 0x64, 0x20, 0x77,
			0x65, 0x27, 0x6C, 0x6C, 0x20, 0x64, 0x61, 0x6E, 0x63, 0x65, 0x20, 0x75, 0x6E, 0x74, 0x69, 0x6C,
			0x20, 0x77, 0x65, 0x20, 0x64, 0x69, 0x65, 0x21
		}; 
	
	// text: -- excluding this line
	// This is a really good t�st.
	int[] text2 = {
			0x54, 0x68, 0x69, 0x73, 0x20, 0x69, 0x73, 0x20, 0x61, 0x20, 0x72, 0x65, 0x61, 0x6C, 0x6C, 0x79,
			0x20, 0x67, 0x6F, 0x6F, 0x64, 0x20, 0x74, 0xE8, 0x73, 0x74, 0x2E
		};
	
	// This is the content of the encoded files (corresponding to the texts).
	int[] rawEncoding1 = {
			0x00, 0x00, 0x00, 0x00, 0x01, 0x0A, 0x00, 0x00, 0x00, 0x03, 0x0D, 0x00, 0x00, 0x00, 0x03, 0x20,
			0x00, 0x00, 0x00, 0x1C, 0x21, 0x00, 0x00, 0x00, 0x01, 0x27, 0x00, 0x00, 0x00, 0x03, 0x41, 0x00,
			0x00, 0x00, 0x01, 0x42, 0x00, 0x00, 0x00, 0x01, 0x49, 0x00, 0x00, 0x00, 0x01, 0x53, 0x00, 0x00,
			0x00, 0x01, 0x54, 0x00, 0x00, 0x00, 0x01, 0x61, 0x00, 0x00, 0x00, 0x09, 0x62, 0x00, 0x00, 0x00,
			0x03, 0x63, 0x00, 0x00, 0x00, 0x02, 0x64, 0x00, 0x00, 0x00, 0x0D, 0x65, 0x00, 0x00, 0x00, 0x11,
			0x66, 0x00, 0x00, 0x00, 0x02, 0x67, 0x00, 0x00, 0x00, 0x02, 0x68, 0x00, 0x00, 0x00, 0x04, 0x69,
			0x00, 0x00, 0x00, 0x07, 0x6B, 0x00, 0x00, 0x00, 0x01, 0x6C, 0x00, 0x00, 0x00, 0x0A, 0x6D, 0x00,
			0x00, 0x00, 0x03, 0x6E, 0x00, 0x00, 0x00, 0x0C, 0x6F, 0x00, 0x00, 0x00, 0x08, 0x72, 0x00, 0x00,
			0x00, 0x05, 0x73, 0x00, 0x00, 0x00, 0x08, 0x74, 0x00, 0x00, 0x00, 0x08, 0x75, 0x00, 0x00, 0x00,
			0x03, 0x77, 0x00, 0x00, 0x00, 0x04, 0x79, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x01,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x4E, 0xFC, 0xFE, 0x18, 0xB9, 0xD9, 0xB0, 0x3A, 0x3E, 0xD9, 0xCF,
			0xE1, 0xAB, 0xB1, 0x3F, 0x5A, 0xB9, 0xDA, 0x7C, 0x88, 0x96, 0x05, 0xFF, 0xFA, 0x86, 0xC5, 0xD5,
			0x5A, 0xD6, 0xFB, 0x39, 0x7C, 0x35, 0xE6, 0x2A, 0x1A, 0xF6, 0x99, 0xF1, 0xF0, 0x55, 0x7A, 0x5A,
			0x16, 0x8E, 0x2A, 0x62, 0x80, 0x96, 0x3D, 0x40, 0xFF, 0x93, 0x5D, 0xF5, 0xB4, 0x13, 0x34, 0xFA,
			0xCF, 0x01, 0x5A, 0xF6, 0x6A, 0xC1, 0x6D, 0xED, 0x50, 0x59, 0xB8, 0xCC, 0xAD, 0x1D, 0x74, 0xAF,
			0x65, 0xCF, 0xB2, 0x47, 0x54, 0x45, 0xAC, 0x2D, 0x9F, 0xDA, 0x99, 0x78, 0x63, 0x44, 0xEB, 0x2B,
			0x2E, 0x07
		};
	
	int[] rawEncoding2 = {
			0x00, 0x00, 0x00, 0x00, 0x01, 0x20, 0x00, 0x00, 0x00, 0x05, 0x2E, 0x00, 0x00, 0x00, 0x01, 0x54,
			0x00, 0x00, 0x00, 0x01, 0x61, 0x00, 0x00, 0x00, 0x02, 0x64, 0x00, 0x00, 0x00, 0x01, 0x65, 0x00,
			0x00, 0x00, 0x01, 0x67, 0x00, 0x00, 0x00, 0x01, 0x68, 0x00, 0x00, 0x00, 0x01, 0x69, 0x00, 0x00,
			0x00, 0x02, 0x6C, 0x00, 0x00, 0x00, 0x02, 0x6F, 0x00, 0x00, 0x00, 0x02, 0x72, 0x00, 0x00, 0x00,
			0x01, 0x73, 0x00, 0x00, 0x00, 0x03, 0x74, 0x00, 0x00, 0x00, 0x02, 0x79, 0x00, 0x00, 0x00, 0x01,
			0xE8, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x6D, 
			0x19, 0x6F, 0xBC, 0x7E, 0x0F, 0x75, 0x87, 0xCF, 0x88, 0xFE, 0x44, 0x66, 0x2D
	};
	

	// These are the contents of the "charCounter" corresponding to those 2 test texts.
	int[] text1Encoding = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 28, 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 9, 3, 2, 13, 17, 2, 2, 4, 7, 0, 1, 10, 3, 12, 8, 0, 0, 5, 8, 8, 3, 0, 4, 0, 4, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 1};
	int[] text2Encoding = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 0, 1, 1, 2, 0, 0, 2, 0, 0, 2, 0, 0, 1, 3, 2, 0, 0, 0, 0, 1, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 1};
	
	// This is a set of codes corresponding to the values below (and should match all entries of the tree 
	// that belongs to text1.
	String[] codes = {"0111001", "0001", "11111", "01111", "111000", "00100", "11101", "1001", "1011", 
			"0000", "101011", "1000", "111101", "010", "001101", "1000", "101011", "0110", "00101", 
			"0011111", "001100", "0111000", "101010", "1111001", "111001", "0111010"};
	
	// This is a set of values corresponding to the codes above (and should match all entries of the tree 
	// that belongs to text1.
	int[] values = {'S', 't', 'o', 'r', 'm', 'w', 'i', 'n', 'd', 's', 'b', 'l', 'h', 'e', 'g', 'l', 'b', 
			'a', 'y', 'B', 'f', 'I', '\'', 'c', 'u', 'T'};
	
	// make the test file ready for use.
	public void prepareTest(int[] text, String file) {
		//System.out.println(text);
		try {
			FileOutputStream fout = new FileOutputStream(file);
			for (int i = 0; i < text.length; i++) {
				fout.write(text[i]);
			}
			fout.close();
		}
		catch (Exception e) {
			System.err.println("Fatal error: could not write testfiles to disk.");
		}
	}
	
	// support method for reading files
	public int[] readFile(String file) {
		int[] result = new int[0];
		try { 
			/* a general note, we use a stream because it can read bytes. A regular FileReader 
			 * will read ints and may very well return wrong results on certain specific positions.
			 */
			FileInputStream reader = new FileInputStream(file);
			reader.mark(4000000); // this will break on files larger than 4 million characters.
			int counter = reader.available();
			int character = 0;
			result = new int[counter];
			for (int i = 0; i < counter; ++i) {
				character = reader.read();
				if (character == -1) {
					break;
				}
				result[i] = character;
			}
		}
		catch (Exception e) {
			System.err.println("Fatal error: could not read testfiles from disk.");
		}
		return result;
	}
}
