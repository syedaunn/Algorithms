public class Caesar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println((int) 'a');
	}

	public static String encode(String message, int key) {
		String ret = new String();
		for (int i = 0; i < message.length(); i++) {
			int ch = message.charAt(i);
			if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
				ch = (ch + key);
				if (ch > 122) {
					int diff = ch - 122;
					ch = 96 + diff;
				}
			}

			ret = ret + (char) ch;
		}
		return ret;

	}

	public static String decode(String message, int key) {
		String ret = new String();
		for (int i = 0; i < message.length(); i++) {
			int ch = message.charAt(i);
			if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
				ch = (ch - key);
				if (ch < 97) {
					int diff = 96 - ch;
					ch = 122 - diff;
				}

			}

			ret = ret + (char) ch;
		}
		return ret;

	}

}
