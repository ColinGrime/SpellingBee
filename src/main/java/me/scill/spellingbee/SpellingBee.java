package me.scill.spellingbee;

import java.io.IOException;
import java.util.Scanner;

public class SpellingBee {

	public static void main(String[] args) {

		System.out.print("Do you straight up want all of the answers? (y/n) ");

		Scanner input = new Scanner(System.in);
		boolean getAccurateAnswers = input.next().toLowerCase().charAt(0) == 'y';

		System.out.println();

		WordSearcher wordSearcher = getAccurateAnswers ? new AutomaticWordSearcher() : new ManualWordSearcher();
		WordPrinter wordPrinter = new WordPrinter(4);

		try {
			wordPrinter.print(wordSearcher.getWordList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println();
	}
}