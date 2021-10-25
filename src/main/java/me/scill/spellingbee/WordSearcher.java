package me.scill.spellingbee;

import java.io.IOException;

public interface WordSearcher {

	String[] getWordList() throws IOException;
}