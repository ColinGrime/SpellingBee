package me.scill.spellingbee;

import java.io.*;
import java.net.URL;

public class AutomaticWordSearcher implements WordSearcher {

	@Override
	public String[] getWordList() throws IOException {

		URL url = new URL("https://www.nytimes.com/puzzles/spelling-bee");
		InputStream inputStream = url.openStream();
		String word;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			while ((word = reader.readLine()) != null) {
				if (word.contains("answers")) {
					String answers = word.split("\"answers\":")[1];
					return answers.substring(1, answers.indexOf("]")).replaceAll("\"", "").split(",");
				}
			}
		}

		return null;
	}
}