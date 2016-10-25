import java.io.*;
import java.util.*;

public class Dictionary {

	public static int addToDictionary(String dictionaryFile, String wordListFile) throws IOException {
		ArrayList<String> wordArr = Tokenizer.tokenizeFile(wordListFile);
		
		File dictionary = new File (dictionaryFile);
		
		if (dictionary.exists() == false) {
			dictionary.createNewFile();
		}
		
		ArrayList<String> dictionaryArr = Tokenizer.tokenizeFile(dictionaryFile);
		
		int addedWords = addEntries(wordArr, dictionaryArr);	
		
		writeToFile(dictionaryFile, dictionaryArr);
		
		return addedWords;
	}
	
	private static void addWord (ArrayList<String> source, String word) {
		int index = 0;
		
		if (source.size() == 0) {
			source.add(word);
		} else {
			while (index < source.size() && (source.get(index)).compareTo(word) < 0) { 
				index++; 
			}
			
			if (index >= source.size()) {
				source.add(word); 
			} else { 
				source.insert(word, index);
			}
		}
	}
	
	private static int addEntries (ArrayList<String> input, ArrayList<String> source) {
		int added = 0;
		
		for (int i = 0; i < input.size(); i++) {
			if (source.indexOf(input.get(i)) == -1 ) {
				addWord(source, input.get(i));
				added++;
			}
		}
		
		return added;
	}
	
	
	private static void writeWord (OutputStream out, String word) throws IOException {
		char[] arr = word.toCharArray();
		
		for (int i = 0; i < arr.length; i++) {
			char temp = arr[i];
			out.write(temp);
		}
		
	}
	
	private static void writeToFile (String filename, ArrayList<String> ls) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename));
		
		StringBuilder sb = new StringBuilder();
		
	    for (int i = 0; i < ls.size(); i++) {
	    	sb.append(ls.get(i)).append("\n");
	    }
	    
	   writeWord(out, sb.toString());
	    
	    out.flush();
	    out.close();
		
	}
	
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner (System.in);
		
//		System.out.println("Please input the pathname of the desired dictionary: ");
//		String dictionary = in.nextLine();
//		
//		System.out.println("Please input the name of the file to read from: ");
//		String file = in.nextLine();
		
		String dictionary = "/Users/Voozell/Desktop/LAT395/Latin.dic";
		String file = "/Users/Voozell/Desktop/LAT395/Words.txt";
		int added = addToDictionary(dictionary, file);
		
		System.out.println(added + " words added to " + dictionary + " from " + file);
		in.close();
	}
}
