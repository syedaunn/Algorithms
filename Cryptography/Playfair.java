import java.awt.Point;

public class Playfair {

	static String[][] cipherTable = new String[5][5];
	public static int row = 0;
	public static int col = 0;
	public static final String filler = "X";
	public static Point[] pos;
	static boolean removeFiller = true;

	public static void main(String[] args) {

		displayCipherTable(cipherTable("BALLON"));
		String bc = encode("HELLO WORLD");
		// System.out.println(bc);
		String cd = decode(bc);
		// System.out.println(cd);

	}

	public static String parseString(String text) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (Character.isLetter(ch)) {
				out.append(Character.toUpperCase(ch));
			}
		}
		return out.toString().replace("J", "I");
	}

	public static String[][] cipherTable(String key) {

		String[][] tbl = new String[5][5];
		Point[] exists = new Point[26];

		String line = parseString(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		/* Add Filler to consecutive pair */

		int length = (line.length() / 2) + (line.length() % 2);
		for (int i = 0; i < (length - 1); i++) {
			if (line.charAt(2 * i) == line.charAt(2 * i + 1)) {
				line = new StringBuffer(line).insert(2 * i + 1, filler)
						.toString();
				length = (line.length() / 2) + (line.length() % 2);

			}
		}

		/* Create Table */

		int len = line.length();
		for (int i = 0, k = 0; i < len; i++) {
			char ch = line.charAt(i);
			if (exists[ch - 65] == null) {
				tbl[k / 5][k % 5] = Character.toString(ch);
				exists[ch - 65] = new Point(k % 5, k / 5);
				k++;
			}
		}
		cipherTable = tbl;
		pos = exists;
		return tbl;
	}

	public static void displayCipherTable(String[][] table) {
		System.out.println("Cipher Table:");
		for (int i = 0; i < table.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < table[0].length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println("|");
		}
	}

	public static String encode(String message) {
		message = parseString(message);
		int length = message.length() / 2 + message.length() % 2;
		StringBuilder out = new StringBuilder();
		/* Add filler between pairs */

		for (int i = 0; i < (length - 1); i++) {
			if (message.charAt(2 * i) == message.charAt(2 * i + 1)) {
				message = new StringBuffer(message).insert(2 * i + 1, filler)
						.toString();
				length = (int) message.length() / 2 + message.length() % 2;
			}
		}

		/* Form Digraphs of String and Append filler to make it even length */

		String[] pairs = new String[length];
		for (int j = 0; j < length; j++) {
			if (j == (length - 1) && message.length() / 2 == (length - 1))
				message = message + filler;
			pairs[j] = message.charAt(2 * j) + "" + message.charAt(2 * j + 1);
		}
	
		
		for (int i = 0; i < length; i++) {
			char one = pairs[i].charAt(0);
			char two = pairs[i].charAt(1);
			int row_one = pos[one - 65].y;
			int row_two = pos[two - 65].y;
			int col_one = pos[one - 65].x;
			int col_two = pos[two - 65].x;

			if (row_one == row_two) {
				col_one = (col_one + 1) % 5;
				col_two = (col_two + 1) % 5;

			}

			else if (col_one == col_two) {
				row_one = (row_one + 1) % 5;
				row_two = (row_two + 1) % 5;

			} else {
				int temp = col_one;
				col_one = col_two;
				col_two = temp;
			}
		
		out.append(cipherTable[row_one][col_one]);
		out.append(cipherTable[row_two][col_two]);
		}
		
		return out.toString();

	}

	public static String decode(String codedText) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < codedText.length() / 2; i++) {
			char one = codedText.charAt(2 * i);
			char two = codedText.charAt(2 * i + 1);

			int row_one = pos[one - 65].y;
			int row_two = pos[two - 65].y;
			int col_one = pos[one - 65].x;
			int col_two = pos[two - 65].x;

			if (row_one == row_two) {
				col_one = (col_one + 4) % 5;
				col_two = (col_two + 4) % 5;
			}

			else if (col_one == col_two) {
				row_one = (row_one + 4) % 5;
				row_two = (row_two + 4) % 5;
			}

			else {
				int tmp = col_one;
				col_one = col_two;
				col_two = tmp;
			}
			out.append(cipherTable[row_one][col_one]);
			out.append(cipherTable[row_two][col_two]);
	
		}
		
		String ret = out.toString();
		
		
		/*Optional Remove Filler*/
		if(removeFiller){
			ret = ret.replace("X", "");
		}
		return ret;
	}
}
