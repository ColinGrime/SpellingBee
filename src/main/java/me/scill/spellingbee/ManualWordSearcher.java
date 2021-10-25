package me.scill.spellingbee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @deprecated - Recommended to use {@link AutomaticWordSearcher}
 */
@Deprecated
public class ManualWordSearcher implements WordSearcher {

	private char centerLetter;
	private String surroundingLetters;
	private boolean doAdvancedSearch;

	public ManualWordSearcher() {
		promptSearch();
	}

	private void promptSearch() {
		System.out.print("Enter the center letter: ");

		Scanner input = new Scanner(System.in);
		centerLetter = input.next().toLowerCase().charAt(0);
		input.nextLine();

		System.out.print("Enter the surrounding letters (abcdef): ");
		surroundingLetters = input.nextLine().toLowerCase();

		System.out.print("Advanced search? (y/n) ");
		doAdvancedSearch = input.next().toLowerCase().charAt(0) == 'y';
		input.nextLine();

		System.out.println();
	}

	@Override
	public String[] getWordList() {

		List<String> wordList = new ArrayList<>();
		String word;

		// Uses words-long.txt if doAdvancedSearch is true.
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/words" + (doAdvancedSearch ? "-long" : "") + ".txt"))) {
			while ((word = reader.readLine()) != null) {
				// Minimum 4 letters, center letter is required.
				if (word.length() < 4 || word.indexOf(centerLetter) == -1)
					continue;

				boolean validWord = true;

				// Invalidates the word if any of its characters fails to
				// be the center letter or one of the surrounding letters.
				for (char letter : word.toCharArray()) {
					if (letter != centerLetter && surroundingLetters.indexOf(Character.toLowerCase(letter)) == -1) {
						validWord = false;
						break;
					}
				}

				if (validWord)
					wordList.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return wordList.toArray(new String[0]);
	}
}