
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {

		if (str.length() == 1) {
			return "";
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int a = word1.length();
		int b = word2.length();


		if (b == 0) {
			return a;

		}
		if (a == 0) {
			return b;

		}
		if (word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));

		}

		return 1 + Math.min(Math.min(levenshtein(tail(word1), word2), (levenshtein(tail(word1), word2))), levenshtein(tail(word1), tail(word2)));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			String line = in.readLine();
			if (line != null){
				if (!line.isEmpty()) {
					dictionary[i] = in.readString();
				}
			}
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = levenshtein(word, dictionary[0]);
		String target = dictionary[0];

		for (int i = 1; i < dictionary.length; i++) {
			int edit = levenshtein(word, dictionary[i]);
			
			if (edit < min) {
				min = edit;
				target = dictionary[i];
			}

		}

		if (min > threshold) {
			return word;

		}
		return target;
	}

}
