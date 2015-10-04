import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CharacterFrequencyTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = readFile("E:\\file.txt");
		System.out.println(text);
		int[] abc = createFrequencyTable(text);
		
		for (int i = 0; i < 26; i++) {
			System.out.println(abc[i]);
		}
	}

	public static String readFile(String path) {
		String ret = new String();
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(path));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    ret = sb.toString();
		    br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return ret;
	}
	public static int[] createFrequencyTable(String text){
		
		int[] freqTable = new int[26];
		for (int i = 0; i < text.length(); i++) {
			int ch = text.charAt(i);
			if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
				ch = ch -97;
				freqTable[ch] +=1;
			}
		}
		return freqTable;
			
	}
}
