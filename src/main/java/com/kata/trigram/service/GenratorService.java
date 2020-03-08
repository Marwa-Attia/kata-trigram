package com.kata.trigram.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kata.trigram.exceptions.InvalidSeedMapException;
import com.kata.trigram.util.Constants;

public class GenratorService {
	final static Logger LOGGER = Logger.getLogger(GenratorService.class);

	public String generateText(Map<String, Set<String>> seedMap) throws InvalidSeedMapException {
		Calendar now = Calendar.getInstance();
		String fileName=null;
		if (Objects.nonNull(seedMap)&&!seedMap.isEmpty()) {
			fileName="Output_"+now.getTimeInMillis()+".txt";

			Random randomGenrator = new Random();
			Object[] keys = seedMap.keySet().toArray();
			String randomKey = (String) keys[randomGenrator.nextInt(keys.length)];

			try (FileWriter writer = new FileWriter(Constants.BASE_URL.getPath() + fileName, true)) {
				writer.append(randomKey.replace("_", " "));
				int counter = 0;
				while (seedMap.get(randomKey) != null && counter < 500) {
					Set<String> values = seedMap.get(randomKey);
					Object[] valSet = values.toArray();

					String randomVal = (String) valSet[randomGenrator.nextInt(valSet.length)];
					writer.append(" " + randomVal);
					String nextKey = randomKey.substring(randomKey.indexOf("_") + 1, randomKey.length()) + "_"
							+ randomVal;
					randomKey = nextKey;
					counter++;
				}
			} catch (IOException e) {
				LOGGER.error(e.getLocalizedMessage());
			}
		} else {
			throw new InvalidSeedMapException("Seed Map can't be null");

		}
		return fileName;
	}
}
