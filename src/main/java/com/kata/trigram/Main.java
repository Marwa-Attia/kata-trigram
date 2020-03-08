package com.kata.trigram;

import org.apache.log4j.Logger;

import com.kata.trigram.exceptions.InvalidFileNameException;
import com.kata.trigram.exceptions.InvalidSeedMapException;
import com.kata.trigram.exceptions.UnparsableFileException;
import com.kata.trigram.service.GenratorService;
import com.kata.trigram.util.Parser;
import com.kata.trigram.util.TrigramParser;

public class Main {
	final static Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Parser parser = new TrigramParser();
		try {
			GenratorService genratorService = new GenratorService();
			genratorService.generateText(parser.parseFile(args[0]));
		} catch (InvalidFileNameException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (InvalidSeedMapException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (UnparsableFileException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

}
