public class Vigenere {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		
		
		
		
		String en = encode("tobeornottobethatisthequestion", "relations");
		System.out.println(en);
		System.out.println(decode(en, "relations"));
		int a = 'a';
		int b = 'z';
		System.out.println(a + "\n" + b);
	}

	public static String compress(String text) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
				out.append(ch);
			}
		}
		return out.toString();
	}

	public static String fiveLetterWords(String text) {
		StringBuilder out = new StringBuilder();
		int str_length = text.length();
		int count = 0;
		while (count <= str_length) {
			if (count + 5 <= str_length) {
				out.append(text.substring(count, count + 5) + " ");
			} else {
				out.append(text.substring(count, str_length) + " ");
			}
			count += 5;
		}

		return out.toString();
	}

	public static String encode(String message, String key) {

		String en = compress(message);
		String phrase = compress(key);
		String new_key = "";
		int text_length = en.length();
		int key_length = phrase.length();
		int div = text_length / key_length;
		int rem = text_length % key_length;
		for (int i = 0; i < div; i++) {
			new_key += phrase;
		}
		new_key += phrase.substring(0, rem);
		StringBuilder encoded = new StringBuilder();
		for (int i = 0; i < en.length(); i++) {
			int ch = en.charAt(i);
			int shift = new_key.charAt(i) - 97;

			ch += shift;
			if (ch > 122) {
				int ord =   ch - 122;
				ch = 96 + ord;

			}
			encoded.append((char) ch);
		}

		return fiveLetterWords(encoded.toString());
	}

	public static String decode(String codedText, String key) {
		String en = compress(codedText);
		String phrase = compress(key);
		String new_key = "";
		int text_length = en.length();
		int key_length = phrase.length();
		int div = text_length / key_length;
		int rem = text_length % key_length;
		for (int i = 0; i < div; i++) {
			new_key += phrase;
		}
		new_key += phrase.substring(0, rem);

		StringBuilder encoded = new StringBuilder();
		for (int i = 0; i < en.length(); i++) {
			int ch = en.charAt(i);
			int shift = new_key.charAt(i) - 97;
			ch -= shift;
			if (ch < 97) {
				int ord =    97 - ch;
				ch = 123 - ord;
				
			}
			encoded.append((char) ch);
		}

		return fiveLetterWords(encoded.toString());
	}

}
