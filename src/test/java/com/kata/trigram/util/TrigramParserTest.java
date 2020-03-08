package com.kata.trigram.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.kata.trigram.exceptions.InvalidFileNameException;
import com.kata.trigram.exceptions.UnparsableFileException;
import com.kata.trigram.service.GenratorService;

public class TrigramParserTest {
	final static Logger LOGGER = Logger.getLogger(GenratorService.class);

	@Test
	public void parsingAnEmptyFileShouldThrowsUnparsableFileException() {
		Parser parser = new TrigramParser();

		Exception exception = assertThrows(UnparsableFileException.class, () -> {
			parser.parseFile("empty.txt");
		});

	}

	@Test
	public void parsing2wordsFileShouldThrowsUnparsableFileException() {
		Parser parser = new TrigramParser();

		Exception exception = assertThrows(UnparsableFileException.class, () -> {
			parser.parseFile("tooshort.txt");
		});

	}

	@Test
	public void parsingA3WordsTextShouldReturn1Item() {
		Parser parser = new TrigramParser();
		try {
			assertEquals(1, parser.parseFile("simpletext.txt").size());
		} catch (InvalidFileNameException | UnparsableFileException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void parsingSentencesWithKeyPairCollision() {
		Parser parser = new TrigramParser();
		try {
			assertEquals(4, parser.parseFile("textwithcollision.txt").size());
		} catch (InvalidFileNameException | UnparsableFileException e) {
			LOGGER.error(e.getLocalizedMessage());
		}

	}

	@Test
	public void shouldParseLongSentencesWithMulipleLinesSuccefully() {
		Parser parser = new TrigramParser();
		try {
			assertNotNull(parser.parseFile("long.txt"));
		} catch (InvalidFileNameException | UnparsableFileException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
}
