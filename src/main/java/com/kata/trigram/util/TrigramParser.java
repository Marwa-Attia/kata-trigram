package com.kata.trigram.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kata.trigram.exceptions.InvalidFileNameException;
import com.kata.trigram.exceptions.UnparsableFileException;

public class TrigramParser implements Parser {
	final static Logger LOGGER = Logger.getLogger(TrigramParser.class);

	private Map<String, Set<String>> seedMap = new HashMap<String, Set<String>>();

	public Map<String, Set<String>> parseFile(String fileName) throws InvalidFileNameException, UnparsableFileException {
		if (Objects.nonNull(fileName)) {
			File file = new File(Constants.BASE_URL.getPath() + fileName);
			try (Scanner sc = new Scanner(file)) {
				String first = sc.next();
				String second = sc.next();
				String third = sc.next();
				while (sc.hasNext()) {
					createTrigram(first, second, third);
					first = second;
					second = third;
					third = sc.next();
				}
				// TO create the last item we need to call createTrigram one more time with the
				// new values
				createTrigram(first, second, third);
			} catch (NoSuchElementException e) {
				throw new UnparsableFileException("File must have at least 3 words");
			} catch (FileNotFoundException e1) {
				LOGGER.error(e1.getLocalizedMessage());
			}
		} else {
			throw new InvalidFileNameException("File Name can't be null");
		}
		return seedMap;
	}

	private void createTrigram(String first, String second, String third) {
		StringBuilder keyBuilder = new StringBuilder(first);
		keyBuilder.append("_").append(second);
		Set<String> values;
		if (Objects.isNull(seedMap.get(keyBuilder.toString()))) {
			values = new HashSet<String>();
		} else {
			values = seedMap.get(keyBuilder.toString());
		}
		values.add(third);
		seedMap.put(keyBuilder.toString(), values);

	}
}
