
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {

		return null;
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
		if (word1.charAt(a - 1) == word2.charAt(b - 1)){
			return levenshtein(word1.substring(0, a - 1), word2.substring(0, b - 1));

		}
		int first = levenshtein(word1, word2.substring(0, b - 1));
		int second = levenshtein(word1.substring(0, a - 1), word2);
		int third = levenshtein(word1.substring(0, a - 1), word2.substring(0, b - 1));

		return 1 + Math.min(Math.min(first, second), third);
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
		String targetWord = word;
		int minDis = Integer.MAX_VALUE;
		String closestWord = "";

		for (int i = 0; i < dictionary.length; i++) {
			int dis = levenshtein(targetWord, dictionary[i]);
			minDis = Math.min(minDis, dis);
			
			if (dis <= minDis) {
				closestWord = dictionary[i];
			}

		}

		if (minDis <= threshold) {
			return closestWord;

		} else {
			return word;
		}
	}

}
