import java.io.*;

public class Tokenizer {

	public static String readWord (InputStream in) throws IOException {
		boolean peek = false;
		String ret = "";
		
		for (int c = 0; (c = in.read()) != -1; ) {
			char temp = (char) c;
			if ((temp >= 'a' && temp <= 'z') || (temp >= 'A' && temp <= 'Z')) {
	            peek = true;
	            ret = ret + temp;
	        } else if (peek) { break; }		
		}
		
		return ret.toLowerCase();
	}
	
//	private static int readInt (InputStream in) throws IOException {
//	    int ret = 0;
//	    boolean peek = false;
//
//	    for (int c = 0; (c = in.read()) != -1; ) {
//	        if (c >= '0' && c <= '9') {
//	            peek = true;
//	            ret = ret * 10 + c - '0';
//	        } else if (peek) break;
//	    }
//
//	    return ret;
//	}
	
	public static ArrayList<String> tokenizeStream (InputStream in) throws IOException {
		ArrayList<String> ret = new ArrayList<String>();
		String temp = readWord(in);
		
		for (int i = 0; !(temp.equals("")); i++) {
			ret.add(temp);
			temp = readWord(in);
		}
		
		return ret;
	}
	
	public static ArrayList<String> tokenizeFile (String filename) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
		
		ArrayList<String> ret = tokenizeStream(in);
		
		in.close();
		
		return ret;
	}
	
}
