package me.scill.spellingbee;

public class WordPrinter {

	private final int rows;

	public WordPrinter() {
		this(3);
	}

	public WordPrinter(int rows) {
		this.rows = rows;
	}

	public void print(String[] wordList) {
		for (int i=0; i<wordList.length; i++) {
			System.out.printf("%-15s", wordList[i]);

			// Goes into the next column...
			if (i % rows == rows - 1)
				System.out.println();
		}
	}
}
